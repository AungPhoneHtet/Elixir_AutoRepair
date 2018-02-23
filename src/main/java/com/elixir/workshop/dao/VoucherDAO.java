package com.elixir.workshop.dao;

import com.elixir.workshop.beans.Voucher;

import java.util.Date;
import java.util.List;

public interface VoucherDAO {

    void save(Voucher voucher);

    void update(Voucher voucher);

    void delete(String voucherNo);

    void updateStatus(String voucherNo, String status);

    List<Voucher> findAll();

    Voucher findByVoucherNo(String voucherNo);

    String getVoucherSerialNo();

    List<Voucher> findByDate(Date date);
}
