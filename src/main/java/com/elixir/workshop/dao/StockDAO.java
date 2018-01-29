package com.elixir.workshop.dao;

import com.elixir.workshop.beans.Stock;

import java.util.List;

public interface StockDAO {

    void save(Stock stock);

    void saveAll(List<Stock> stocks);

    void update(Stock stock);

    void delete(long id);

    List<Stock> findAll();

    Stock findByStockCode(String stockCode);

    Stock findById(long id);
}
