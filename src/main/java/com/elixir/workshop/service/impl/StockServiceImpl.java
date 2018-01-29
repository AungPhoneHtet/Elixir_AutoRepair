package com.elixir.workshop.service.impl;

import com.elixir.workshop.Messages;
import com.elixir.workshop.beans.Stock;
import com.elixir.workshop.dao.StockDAO;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    private StockDAO stockDAO;
    private Messages messages;

    @Autowired
    public StockServiceImpl(StockDAO stockDAO, Messages messages) {
        this.stockDAO = stockDAO;
        this.messages = messages;
    }

    @Override
    public void save(Stock stock) throws CoreException {
        if (stock.getId() == 0) {
            validate(stock);
            stockDAO.save(stock);
        } else {
            stockDAO.update(stock);
        }
    }

    private void validate(Stock stock) throws CoreException {
        validateStockCode(stock.getCode());
    }

    private void validateStockCode(String stockCode) throws CoreException {
        if (stockDAO.findByStockCode(stockCode) != null) {
            throw new CoreException(messages.get("stock.code.already.exist"));
        }
    }

    @Override
    public void saveAll(List<Stock> stocks) throws CoreException {
        //TODO implement stock save all module
    }

    @Override
    public void delete(long id) throws CoreException {
        stockDAO.delete(id);
    }

    @Override
    public List<Stock> findAll() throws CoreException {
        return stockDAO.findAll();
    }

    @Override
    public Stock findByStockCode(String stockCode) throws CoreException {
        return stockDAO.findByStockCode(stockCode);
    }
}
