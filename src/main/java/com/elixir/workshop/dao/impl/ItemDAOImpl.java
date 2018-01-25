package com.elixir.workshop.dao.impl;

import com.elixir.workshop.beans.Item;
import com.elixir.workshop.dao.ItemDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ItemDAOImpl implements ItemDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ItemDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void saveAll(String voucherNo, List<Item> items) {
        final String sql = "INSERT INTO items(voucher_no,item,price,qty,amount) VALUES(?,?,?,?,?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Item item = items.get(i);
                int j = 0;
                ps.setString(++j, voucherNo);
                ps.setString(++j, item.getItem());
                ps.setDouble(++j, item.getPrice());
                ps.setLong(++j, item.getQty());
                ps.setDouble(++j, item.getAmount());
            }

            @Override
            public int getBatchSize() {
                return items.size();
            }
        });
    }

    @Override
    public void deleteAllByVoucherNo(String voucherNo) {
        final String sql = "DELETE FROM items WHERE voucher_no LIKE ?";
        jdbcTemplate.update(sql, new Object[]{voucherNo});
    }

    @Override
    public List<Item> findByVoucherNo(String voucherNo) {
        final String sql = "SELECT * FROM items WHERE voucher_no LIKE ?";
        return jdbcTemplate.query(sql, new Object[]{voucherNo}, new ItemsRowMapper());
    }

    class ItemsRowMapper implements RowMapper<Item> {

        @Override
        public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
            Item i = new Item();
            i.setVoucherNo(rs.getString("voucher_no"));
            i.setItem(rs.getString("item"));
            i.setPrice(rs.getDouble("price"));
            i.setQty(rs.getLong("qty"));
            i.setAmount(rs.getDouble("amount"));
            return i;
        }
    }
}
