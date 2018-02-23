package com.elixir.workshop.service;

import com.elixir.workshop.beans.Voucher;
import com.elixir.workshop.exceptions.CoreException;

import java.util.Date;
import java.util.List;

public interface VoucherService {

    void save(Voucher voucher) throws CoreException;

    void update(Voucher voucher) throws CoreException;

    void delete(String voucherNo) throws CoreException;

    void paidVoucher(String voucherNo) throws CoreException;

    List<Voucher> findAll() throws CoreException;

    Voucher findByVoucherNo(String voucherNo) throws CoreException;

    List<Voucher> findByDate(Date date) throws CoreException;
}
