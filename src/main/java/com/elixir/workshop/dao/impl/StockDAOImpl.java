package com.elixir.workshop.dao.impl;


import com.elixir.workshop.beans.Stock;
import com.elixir.workshop.dao.StockDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StockDAOImpl implements StockDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public StockDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Stock stock) {
        final String sql = "INSERT INTO stock(code, description, brand, buy_price, sell_price,  qty) VALUES(?,?,?,?,?,?)";
        jdbcTemplate.update(sql, ps -> {
            int i = 0;
            ps.setString(++i, stock.getCode());
            ps.setString(++i, stock.getDescription());
            ps.setString(++i, stock.getBrand());
            ps.setDouble(++i, stock.getBuyPrice());
            ps.setDouble(++i, stock.getSellPrice());
            ps.setLong(++i, stock.getQty());
        });
    }

    @Override
    public void saveAll(List<Stock> stocks) {
        //TODO implement stock save all module
    }

    @Override
    public void update(Stock stock) {
        final String sql = "UPDATE stock SET code=?, description=?, brand=?, buy_price=?, sell_price=?, qty=? WHERE id=?";
        jdbcTemplate.update(sql, ps -> {
            int i = 0;
            ps.setString(++i, stock.getCode());
            ps.setString(++i, stock.getDescription());
            ps.setString(++i, stock.getBrand());
            ps.setDouble(++i, stock.getBuyPrice());
            ps.setDouble(++i, stock.getSellPrice());
            ps.setLong(++i, stock.getQty());
            ps.setLong(++i, stock.getId());
        });
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM stock WHERE id=?";
        jdbcTemplate.update(sql, ps -> {
            int i = 0;
            ps.setLong(++i, id);
        });
    }

    @Override
    public List<Stock> findAll() {
        final String sql = "SELECT * FROM Stock";
        return jdbcTemplate.query(sql, new StockRowMapper());
    }

    @Override
    public Stock findByStockCode(String stockCode) {
        final String sql = "SELECT * FROM Stock WHERE code=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{stockCode}, new StockRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Stock findById(long id) {
        final String sql = "SELECT * FROM Stock WHERE id=?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new StockRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    class StockRowMapper implements RowMapper<Stock> {

        @Override
        public Stock mapRow(ResultSet rs, int rowNum) throws SQLException {
            Stock s = new Stock();
            s.setId(rs.getLong("id"));
            s.setCode(rs.getString("code"));
            s.setDescription(rs.getString("description"));
            s.setBrand(rs.getString("brand"));
            s.setBuyPrice(rs.getDouble("buy_price"));
            s.setSellPrice(rs.getDouble("sell_price"));
            s.setQty(rs.getLong("qty"));
            return s;
        }
    }
}
