package com.elixir.workshop.dao.impl;

import com.elixir.workshop.beans.ExpenseType;
import com.elixir.workshop.dao.ExpenseTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ExpenseTypeDAOImpl implements ExpenseTypeDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<ExpenseType> findAll() {
        final String sql = "Select * From expense_type";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(ExpenseType.class));
    }
}
