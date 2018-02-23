package com.elixir.workshop.dao.impl;


import com.elixir.workshop.beans.Voucher;
import com.elixir.workshop.dao.VoucherDAO;
import com.elixir.workshop.utils.DBUtils;
import com.elixir.workshop.utils.DateUtils;
import com.elixir.workshop.utils.UserInfoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Repository
public class VoucherDAOImpl implements VoucherDAO {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public VoucherDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Voucher voucher) {
        final String sql = "INSERT INTO voucher(voucher_no,customer_name,car_no,date,status," +
                "created_by,created_date,updated_by,updated_date)" +
                " VALUES(?,?,?,?,?,?,?,?,?)";
        DBUtils.initRootData(voucher);
        jdbcTemplate.update(sql, ps -> {
                    int i = 0;
                    ps.setString(++i, voucher.getVoucherNo());
                    ps.setString(++i, voucher.getCustomerName());
                    ps.setString(++i, voucher.getCarNo());
                    ps.setDate(++i, DateUtils.changeUIDateToSQLDate(voucher.getDate()));
                    ps.setString(++i, voucher.getStatus());
                    ps.setString(++i, voucher.getCreatedBy());
                    ps.setDate(++i, new Date(voucher.getCreatedDate().getTime()));
                    ps.setString(++i, voucher.getUpdatedBy());
                    ps.setDate(++i, new Date(voucher.getUpdatedDate().getTime()));
                }
        );
    }

    @Override
    public void update(Voucher voucher) {
        final String sql = "UPDATE voucher SET customer_name=?,car_no=?,date=?,status=?,updated_by=?,updated_date=? WHERE voucher_no=?";
        DBUtils.initRootData(voucher);
        jdbcTemplate.update(sql, ps -> {
            int i = 0;
            ps.setString(++i, voucher.getCustomerName());
            ps.setString(++i, voucher.getCarNo());
            ps.setDate(++i, DateUtils.changeUIDateToSQLDate(voucher.getDate()));
            ps.setString(++i, voucher.getStatus());
            ps.setString(++i, voucher.getUpdatedBy());
            ps.setDate(++i, new Date(voucher.getUpdatedDate().getTime()));
            ps.setString(++i, voucher.getVoucherNo());
        });
    }

    @Override
    public void delete(String voucherNo) {
        final String sql = "DELETE FROM voucher WHERE voucher_no LIKE ?";
        jdbcTemplate.update(sql, ps -> {
            int i = 0;
            ps.setString(++i, voucherNo);
        });
    }

    @Override
    public void updateStatus(String voucherNo, String status) {
        final String sql = "UPDATE voucher SET status = ?, updated_by = ?, updated_date = ? WHERE voucher_no = ?";
        jdbcTemplate.update(sql, ps -> {
            int i = 0;
            ps.setString(++i, status);
            ps.setString(++i, UserInfoUtil.getCurrentUser().getUserId());
            ps.setDate(++i, DateUtils.getCurrentDate());
            ps.setString(++i, voucherNo);
        });
    }

    @Override
    public List<Voucher> findAll() {
        final String sql = "SELECT * FROM voucher";
        return jdbcTemplate.query(sql, new VoucherRowMapper());
    }

    @Override
    public Voucher findByVoucherNo(String voucherNo) {
        final String sql = "SELECT * FROM voucher WHERE voucher_no LIKE ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{voucherNo}, new VoucherRowMapper());
    }

    @Override
    public String getVoucherSerialNo() {
        final String sql = "select ifnull(lpad(max(id)+1,4,'0'), '00001') serial from voucher where created_date = curdate()";
        return jdbcTemplate.queryForObject(sql, String.class);
    }

    @Override
    public List<Voucher> findByDate(java.util.Date date) {
        final String sql = "SELECT * FROM voucher WHERE date = ?";
        return jdbcTemplate.query(sql, new Object[]{date}, new VoucherRowMapper());
    }

    class VoucherRowMapper implements RowMapper<Voucher> {

        @Override
        public Voucher mapRow(ResultSet rs, int rowNum) throws SQLException {
            Voucher v = new Voucher();
            v.setVoucherNo(rs.getString("voucher_no"));
            v.setCustomerName(rs.getString("customer_name"));
            v.setCarNo(rs.getString("car_no"));
            v.setDate(DateUtils.changeSQLDateToUIDate(rs.getDate("date")));
            v.setStatus(rs.getString("status"));

            v.setId(rs.getLong("id"));
            v.setCreatedBy(rs.getString("created_by"));
            v.setCreatedDate(rs.getDate("created_date"));
            v.setUpdatedBy(rs.getString("updated_by"));
            v.setUpdatedDate(rs.getDate("updated_date"));
            return v;
        }
    }


}
