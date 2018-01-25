package com.elixir.workshop.dao;

import com.elixir.workshop.beans.Item;

import java.util.List;

public interface ItemDAO {

    void saveAll(String voucherNo, List<Item> items);

    void deleteAllByVoucherNo(String voucherNo);

    List<Item> findByVoucherNo(String voucherNo);
}
