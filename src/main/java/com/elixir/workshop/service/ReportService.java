package com.elixir.workshop.service;

import com.elixir.workshop.beans.report.CommonReport;
import com.elixir.workshop.exceptions.CoreException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.ByteArrayOutputStream;

public interface ReportService {

    JRBeanCollectionDataSource fillJRBeanCollectionDataSource(CommonReport commonReport) throws CoreException;

    ByteArrayOutputStream fillReportDetailsToStream(CommonReport commonReport) throws CoreException;

    String getFileName(CommonReport commonReport);
}
