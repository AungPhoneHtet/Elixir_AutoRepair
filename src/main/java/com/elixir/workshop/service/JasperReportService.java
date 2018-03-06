/**
 *
 */
package com.elixir.workshop.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.elixir.workshop.constants.Constants;
import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsAbstractExporterParameter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * @author aphtet
 */

@Service
public class JasperReportService {
    private static final Logger logger = Logger.getLogger(JasperReportService.class);
    private static final String JASPER_DIRECTORY = "reports/jasper/";

    public void fillReportToStream(String reportFormat, Map<String, Object> parameters,
                                   JRDataSource dataSource, String reportJasperFile, String subReportDir, OutputStream os) {
        logger.debug("Report:LOG :::: for reportClass:" + reportJasperFile);

        JasperReport jasperReport;

        try {
            ClassPathResource cpr = new ClassPathResource(JASPER_DIRECTORY + reportJasperFile);
            InputStream jasperStream = cpr.getInputStream();
            jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            logger.debug("JRLoader loaded jasperReport: " + jasperReport.getName());
            if (dataSource == null) {
                dataSource = new JREmptyDataSource();
            }
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            @SuppressWarnings("rawtypes")
            JRAbstractExporter exporter = null;
            if (reportFormat.equals(Constants.ReportOutputType.PDF)) {
                exporter = new JRPdfExporter();
            } else if (reportFormat.equals(Constants.ReportOutputType.CSV)) {
                exporter = new JRCsvExporter();
            } else if (reportFormat.equals(Constants.ReportOutputType.XLS)) {
                exporter = new JRXlsExporter();
                exporter.setParameter(JRExporterParameter.IGNORE_PAGE_MARGINS, Boolean.valueOf(false));
                exporter.setParameter(JRXlsAbstractExporterParameter.IS_COLLAPSE_ROW_SPAN, Boolean.valueOf(false));
                exporter.setParameter(JRXlsAbstractExporterParameter.IS_WHITE_PAGE_BACKGROUND, Boolean.valueOf(false));
                exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_COLUMNS, Boolean.valueOf(true));
                exporter.setParameter(JRXlsAbstractExporterParameter.IS_REMOVE_EMPTY_SPACE_BETWEEN_ROWS, Boolean.valueOf(true));
                exporter.setParameter(JRXlsAbstractExporterParameter.IS_FONT_SIZE_FIX_ENABLED, Boolean.valueOf(true));
                exporter.setParameter(JRXlsAbstractExporterParameter.CREATE_CUSTOM_PALETTE, Boolean.valueOf(true));
            } else {
                throw new UnsupportedOperationException("Requested report format not known");
            }
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, os);
            exporter.exportReport();
            exporter.reset();
        } catch (JRException e) {
            logger.error("JasperReports exception ", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("IOException compiling JasperReport ", e);
            throw new RuntimeException(e);
        }
        logger.debug("Report:LOG :::: fillReportToStream().[END]");
    }
}
