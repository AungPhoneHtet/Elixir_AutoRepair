package com.elixir.workshop.controller;

import javax.servlet.http.HttpServletRequest;

import com.elixir.workshop.constants.Constants;
import org.apache.log4j.Logger;
import org.springframework.ui.Model;

public class MainController {

    private static final Logger logger = Logger.getLogger(MainController.class);

    protected String redirectPage(Model model, HttpServletRequest request, String redirectURL) {
        try {
            logger.info("Redirecting to " + redirectURL);
        } catch (Exception e) {
            logger.error("Exception thrown : ", e);
            return Constants.URL.HOME;
        }
        return "redirect:" + redirectURL;
    }

    protected String replaceContentPage(Model model, HttpServletRequest request, String contentPage) {
        try {
            logger.info("Replacing page " + contentPage);
            model.addAttribute("template", contentPage);
        } catch (Exception e) {
            System.out.println(e);
            logger.error("Exception thrown : ", e);
            return Constants.URL.ERROR;
        }
        return Constants.URL.HOME;
    }
}
