package com.elixir.workshop.controller;

import com.elixir.workshop.beans.report.CommonReport;
import com.elixir.workshop.constants.Constants;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.ReportService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Controller
public class ReportController extends MainController {

    private static final Logger logger = Logger.getLogger(ReportController.class);

    @Autowired
    private ReportService reportService;

    @GetMapping("/reports")
    public String reports(Model model, HttpServletRequest request) {
        if (!model.containsAttribute("report")) {
            model.addAttribute("report", new CommonReport());
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.REPORTS);
    }

    @PostMapping("/generateReport")
    public String generateReport(Model model, @ModelAttribute CommonReport report, HttpServletRequest request, HttpServletResponse response) {
        try {
            logger.debug("Generate report ::: " + report.toString());
            ByteArrayOutputStream byteArrayOutputStream = reportService
                    .fillReportDetailsToStream(report);
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + reportService.getFileName(report));
            byteArrayOutputStream.writeTo(response.getOutputStream());
            byteArrayOutputStream.flush();
            byteArrayOutputStream.close();
        } catch (IllegalArgumentException e) {
            model.addAttribute(Constants.MessageType.ERROR, e.getMessage());
            model.addAttribute("report", new CommonReport());
            return super.replaceContentPage(model, request, Constants.ContentPages.REPORTS);
        } catch (IOException e) {
            model.addAttribute(Constants.MessageType.ERROR, e.getMessage());
            model.addAttribute("report", new CommonReport());
            return super.replaceContentPage(model, request, Constants.ContentPages.REPORTS);
        } catch (CoreException e) {
            model.addAttribute(Constants.MessageType.ERROR, e.getMessage());
            model.addAttribute("report", new CommonReport());
            return super.replaceContentPage(model, request, Constants.ContentPages.REPORTS);
        }
        return null;
    }
}
