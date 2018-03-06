package com.elixir.workshop.service.impl;

import com.elixir.workshop.Messages;
import com.elixir.workshop.beans.report.CommonReport;
import com.elixir.workshop.beans.report.ExpenseReport;
import com.elixir.workshop.beans.report.VoucherSummaryReport;
import com.elixir.workshop.dao.ReportDAO;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.JasperReportService;
import com.elixir.workshop.service.ReportService;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    JasperReportService jasperReportService;

    @Autowired
    private ReportDAO reportDAO;

    @Autowired
    private Messages messages;

    private static final String VOUCHER_SUMMARY_JASPER = "Voucher_Summary_Report.jasper";
    private static final String EXPENSE_JASPER = "Expense_Report.jasper";

    @Override
    public JRBeanCollectionDataSource fillJRBeanCollectionDataSource(CommonReport commonReport) throws CoreException {
        JRBeanCollectionDataSource dataSource;
        if (commonReport.getReportName().equals(messages.get("voucher.summary.report"))) {
            List<VoucherSummaryReport> voucherSummaries = reportDAO.getVoucherSummaries(commonReport);
            dataSource = new JRBeanCollectionDataSource(voucherSummaries);
        } else {
            List<ExpenseReport> expenses = reportDAO.getExpenses(commonReport);
            dataSource = new JRBeanCollectionDataSource(expenses);
        }
        return dataSource;
    }

    @Override
    public ByteArrayOutputStream fillReportDetailsToStream(CommonReport commonReport) throws CoreException {
        JRBeanCollectionDataSource dataSource = fillJRBeanCollectionDataSource(commonReport);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Map<String, Object> reportParameters = new HashMap<String, Object>();
        String jasperName;
        if (commonReport.getReportName().equals(messages.get("voucher.summary.report"))) {
            jasperName = VOUCHER_SUMMARY_JASPER;
        } else {
            jasperName = EXPENSE_JASPER;
        }
        reportParameters.put("fromPeriod", commonReport.getFromPeriod());
        reportParameters.put("toPeriod", commonReport.getToPeriod());
        reportParameters.put("status", commonReport.getStatus());
        jasperReportService.fillReportToStream(commonReport.getOutputType(), reportParameters, dataSource, jasperName, null,
                byteArrayOutputStream);
        return byteArrayOutputStream;
    }

    @Override
    public String getFileName(CommonReport commonReport) {
        Date currentDate = new Date();
        String yyyyMMdd = new SimpleDateFormat("yyyyMMdd").format(currentDate);
        return commonReport.getReportName() + "_" + yyyyMMdd + "." + commonReport.getOutputType().toLowerCase();
    }

}
