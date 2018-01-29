package com.elixir.workshop.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.elixir.workshop.constants.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController extends MainController {

    private final static Logger logger = Logger.getLogger(LoginController.class);

    private VoucherController voucherController;

    @Autowired
    public LoginController(VoucherController voucherController) {
        this.voucherController = voucherController;
    }

    @RequestMapping(value = {"/", "/login"})
    public String login() {
        return Constants.URL.LOGIN;
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return Constants.URL.LOGIN;
    }

    @RequestMapping(value = {"/home"})
    public String home(Model model, HttpServletRequest request) {
        logger.debug("Home page get method ...");
        if (request.getSession().getAttribute("userName") != null) {
            return voucherController.getNewVoucherForm(model, request);
        } else {
            return super.redirectPage(model, request, Constants.URL.LOGIN);
        }
    }

    @RequestMapping(value = {"/logout"})
    public String logout(Model model, HttpServletRequest request) {
        SecurityContextHolder.clearContext();
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        for (Cookie cookie : request.getCookies()) {
            cookie.setMaxAge(0);
        }
        return super.redirectPage(model, request, Constants.URL.LOGIN);
    }
}
