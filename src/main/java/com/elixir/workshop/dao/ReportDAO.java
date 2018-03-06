package com.elixir.workshop.dao;

import com.elixir.workshop.beans.report.CommonReport;
import com.elixir.workshop.beans.report.ExpenseReport;
import com.elixir.workshop.beans.report.VoucherSummaryReport;

import java.util.List;

public interface ReportDAO {

    List<ExpenseReport> getExpenses(CommonReport commonReport);

    List<VoucherSummaryReport> getVoucherSummaries(CommonReport commonReport);

}
