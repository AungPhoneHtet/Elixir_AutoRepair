package com.elixir.workshop.service;

import com.elixir.workshop.beans.Stock;
import com.elixir.workshop.exceptions.CoreException;

import java.util.List;

public interface StockService {

    void save(Stock stock) throws CoreException;

    void saveAll(List<Stock> stocks) throws CoreException;

    void delete(long id) throws CoreException;

    List<Stock> findAll() throws CoreException;

    Stock findByStockCode(String stockCode) throws CoreException;


}
