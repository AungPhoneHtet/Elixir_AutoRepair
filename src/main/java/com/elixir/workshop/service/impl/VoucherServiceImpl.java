package com.elixir.workshop.service.impl;


import com.elixir.workshop.Messages;
import com.elixir.workshop.beans.Voucher;
import com.elixir.workshop.constants.Constants;
import com.elixir.workshop.dao.ItemDAO;
import com.elixir.workshop.dao.VoucherDAO;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.VoucherService;
import com.elixir.workshop.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VoucherServiceImpl implements VoucherService {

    private VoucherDAO voucherDAO;
    private ItemDAO itemDAO;
    private Messages messages;

    @Autowired
    public VoucherServiceImpl(VoucherDAO voucherDAO, ItemDAO itemDAO, Messages messages) {
        this.voucherDAO = voucherDAO;
        this.itemDAO = itemDAO;
        this.messages = messages;
    }

    @Override
    public void save(Voucher voucher) throws CoreException {
        initVoucherNo(voucher);
        voucherDAO.save(voucher);
        itemDAO.saveAll(voucher.getVoucherNo(), voucher.getItems());
    }

    private void initVoucherNo(Voucher voucher) {
        voucher.setVoucherNo(DateUtils.getCurrentDateInYYYYMMDD() + "-" + voucherDAO.getVoucherSerialNo());
    }

    @Override
    public void update(Voucher voucher) throws CoreException {
        voucherDAO.update(voucher);
        itemDAO.deleteAllByVoucherNo(voucher.getVoucherNo());
        itemDAO.saveAll(voucher.getVoucherNo(), voucher.getItems());
    }

    @Override
    public void delete(String voucherNo) throws CoreException {
        voucherDAO.delete(voucherNo);
        itemDAO.deleteAllByVoucherNo(voucherNo);
    }

    @Override
    public void paidVoucher(String voucherNo) throws CoreException {
        validateBeforePaid(this.findByVoucherNo(voucherNo));
        voucherDAO.updateStatus(voucherNo, Constants.Status.PAID);
    }

    private void validateBeforePaid(Voucher voucher) throws CoreException {
        if (voucher == null)
            throw new CoreException(messages.get("record.not.found"));
        if (voucher.getStatus().equals(Constants.Status.PAID))
            throw new CoreException(messages.get("record.already.paid"));
    }

    @Override
    public List<Voucher> findAll() throws CoreException {
        List<Voucher> vouchers = voucherDAO.findAll();
        vouchers.forEach(v -> {
            v.setItems(itemDAO.findByVoucherNo(v.getVoucherNo()));
        });
        return vouchers;
    }

    @Override
    public Voucher findByVoucherNo(String voucherNo) throws CoreException {
        Voucher voucher = voucherDAO.findByVoucherNo(voucherNo);
        voucher.setItems(itemDAO.findByVoucherNo(voucherNo));
        return voucher;
    }
}
