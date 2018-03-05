package com.elixir.workshop.dao.impl;

import com.elixir.workshop.beans.Transaction;
import com.elixir.workshop.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TransactionDAOImpl implements TransactionDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(Transaction transaction) {
        final String sql = "insert into transaction(trans_ref,trans_desc,amount) values(?,?,?)";
        jdbcTemplate.update(sql, ps -> {
            int i = 0;
            ps.setString(++i, transaction.getTransRef());
            ps.setString(++i, transaction.getTransDesc());
            ps.setDouble(++i, transaction.getAmount());
        });
    }

    @Override
    public String getTransactionSerial() {
        final String sql = "select ifnull(lpad(max(id)+1,4,'0'), '00001') serial from transaction where Date(trans_date) = curdate()";
        return jdbcTemplate.queryForObject(sql, String.class);
    }
}
