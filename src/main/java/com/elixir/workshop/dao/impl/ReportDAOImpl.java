package com.elixir.workshop.dao.impl;


import com.elixir.workshop.beans.report.CommonReport;
import com.elixir.workshop.beans.report.ExpenseReport;
import com.elixir.workshop.beans.report.VoucherSummaryReport;
import com.elixir.workshop.dao.ReportDAO;
import com.elixir.workshop.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReportDAOImpl implements ReportDAO {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;


    @Override
    public List<ExpenseReport> getExpenses(CommonReport commonReport) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT e.description,e.related_voucher_no as relatedVoucherNo,DATE_FORMAT(e.expense_date,'%d/%m/%Y') as expenseDate, e.amount, e.status, et.description as expenseType ");
        sql.append(" FROM expense e INNER JOIN expense_type et ON e.expense_type_id = et.id ");
        sql.append(" WHERE e.expense_date >= :fromPeriod AND e.expense_date <= :toPeriod ");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("fromPeriod", DateUtils.changeUIDateToSQLDate(commonReport.getFromPeriod()));
        namedParameters.addValue("toPeriod", DateUtils.changeUIDateToSQLDate(commonReport.getToPeriod()));
        if (!commonReport.getStatus().equalsIgnoreCase("all")) {
            sql.append(" AND e.status = :status");
            namedParameters.addValue("status", commonReport.getStatus());
        }
        return jdbcTemplate.query(sql.toString(), namedParameters, new BeanPropertyRowMapper<>(ExpenseReport.class));
    }

    @Override
    public List<VoucherSummaryReport> getVoucherSummaries(CommonReport commonReport) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT v.voucher_no as voucherNo, v.customer_name as customerName, v.car_no as carNo, ");
        sql.append(" DATE_FORMAT(v.date,'%d/%m/%Y') as date, v.status, SUM(i.amount) as amount ");
        sql.append(" FROM voucher v INNER JOIN items i ON v.voucher_no = i.voucher_no ");
        sql.append(" WHERE v.date >= :fromPeriod AND v.date <= :toPeriod ");
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();
        namedParameters.addValue("fromPeriod", DateUtils.changeUIDateToSQLDate(commonReport.getFromPeriod()));
        namedParameters.addValue("toPeriod", DateUtils.changeUIDateToSQLDate(commonReport.getToPeriod()));
        if (!commonReport.getStatus().equalsIgnoreCase("all")) {
            sql.append(" AND v.status = :status");
            namedParameters.addValue("status", commonReport.getStatus());
        }
        sql.append(" GROUP BY v.voucher_no ORDER BY v.voucher_no ");
        System.out.println(sql.toString());
        return jdbcTemplate.query(sql.toString(), namedParameters, new BeanPropertyRowMapper<>(VoucherSummaryReport.class));
    }
}
