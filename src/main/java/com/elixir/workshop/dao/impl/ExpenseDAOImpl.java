package com.elixir.workshop.dao.impl;


import com.elixir.workshop.beans.Expense;
import com.elixir.workshop.dao.ExpenseDAO;
import com.elixir.workshop.utils.DBUtils;
import com.elixir.workshop.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.Date;
import java.util.List;

@Repository
public class ExpenseDAOImpl implements ExpenseDAO {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Expense save(Expense expense) {
        final String sql = "INSERT INTO expense(description, amount, related_voucher_no, expense_date, expense_type_id,status, created_by," +
                " created_date, updated_by, updated_date) VALUES(?,?,?,?,?,?,?,?,?,?)";
        KeyHolder holder = new GeneratedKeyHolder();
        DBUtils.initRootData(expense);
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                int i = 0;
                ps.setString(++i, expense.getDescription());
                ps.setDouble(++i, expense.getAmount());
                ps.setString(++i, expense.getRelatedVoucherNo());
                ps.setDate(++i, DateUtils.changeUIDateToSQLDate(expense.getExpenseDate()));
                ps.setLong(++i, expense.getExpenseTypeId());
                ps.setString(++i, expense.getStatus());
                ps.setString(++i, expense.getCreatedBy());
                ps.setTimestamp(++i, new Timestamp(expense.getCreatedDate().getTime()));
                ps.setString(++i, expense.getUpdatedBy());
                ps.setTimestamp(++i, new Timestamp(expense.getUpdatedDate().getTime()));
                return ps;
            }
        }, holder);

        expense.setId(holder.getKey().longValue());
        return expense;
    }

    @Override
    public void delete(long id) {
        final String sql = "DELETE FROM expense WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }


    @Override
    public List<Expense> findByDate(Date date) {
        final String sql = "SELECT * FROM Expense WHERE expense_date = ?";
        return jdbcTemplate.query(sql, new Object[]{date}, new ExpenseRowMapper());
    }

    @Override
    public Expense findById(long id) {
        final String sql = "SELECT * FROM expense WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new ExpenseRowMapper());
    }

    @Override
    public void updateStatus(long id, String status) {
        final String sql = "UPDATE expense SET Status = ? WHERE id = ?";
        jdbcTemplate.update(sql, status, id);
    }

    class ExpenseRowMapper implements RowMapper<Expense> {

        @Override
        public Expense mapRow(ResultSet rs, int rowNum) throws SQLException {
            Expense expense = new Expense();
            expense.setDescription(rs.getString("description"));
            expense.setAmount(rs.getDouble("amount"));
            expense.setRelatedVoucherNo(rs.getString("related_voucher_no"));
            expense.setExpenseDate(DateUtils.changeSQLDateToUIDate(rs.getDate("expense_date")));
            expense.setExpenseTypeId(rs.getLong("expense_type_id"));
            expense.setStatus(rs.getString("status"));

            expense.setId(rs.getLong("id"));
            expense.setCreatedBy(rs.getString("created_by"));
            expense.setCreatedDate(rs.getDate("created_date"));
            expense.setUpdatedBy(rs.getString("updated_by"));
            expense.setUpdatedDate(rs.getDate("updated_date"));
            return expense;
        }
    }
}
