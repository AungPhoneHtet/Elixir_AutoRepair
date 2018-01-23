package com.elixir.workshop.controller;

import javax.servlet.http.HttpServletRequest;

import com.elixir.workshop.beans.UserAccount;
import com.elixir.workshop.constants.Constants;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elixir.workshop.Messages;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.UserAccountService;

@Controller
public class UserAccountController extends MainController {

    private static final Logger logger = Logger.getLogger(UserAccountController.class);

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    Messages messages;

    @RequestMapping("/newUserAccount")
    public String newUserAccount(Model model, HttpServletRequest request) {
        model.addAttribute("user", new UserAccount());
        return super.replaceContentPage(model, request, Constants.ContentPages.NEW_USER);
    }

    @RequestMapping("/saveUserAccount")
    public String saveUserAccount(@ModelAttribute UserAccount user, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("user", user);
            userAccountService.saveUser(user);
            model.addAttribute(Constants.MessageType.SUCCESS, messages.get("save.success"));
        } catch (CoreException e) {
            logger.error(e);
            model.addAttribute(Constants.MessageType.ERROR, e.getMessage());
            return super.replaceContentPage(model, request, Constants.ContentPages.NEW_USER);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.NEW_USER);
    }

    @RequestMapping("/existingUsers")
    public String existingUsers(Model model, HttpServletRequest request) {
        try {
            model.addAttribute("userAccounts", userAccountService.findAll());
        } catch (CoreException e) {
            logger.error(e);
            return super.replaceContentPage(model, request, Constants.ContentPages.EXISTING_USERS);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.EXISTING_USERS);
    }

    @RequestMapping(value = "/editUserAccount", method = RequestMethod.GET)
    public String editUserAccount(@RequestParam(name = "userId") String userId, Model model,
                                  HttpServletRequest request) {
        try {
            UserAccount userAcc = new UserAccount();
            userAcc = userAccountService.getUserAccountByUserId(userId);
            userAcc.setPassword("");
            model.addAttribute("user", userAcc);
        } catch (CoreException e) {
            logger.error(e);
            model.addAttribute(Constants.MessageType.ERROR, e.getMessage());
            return super.replaceContentPage(model, request, Constants.ContentPages.EDIT_USER);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.EDIT_USER);
    }

    @RequestMapping(value = "/updateUserAccount")
    public String updateUserAccount(@ModelAttribute UserAccount user, Model model, HttpServletRequest request) {
        try {
            model.addAttribute("user", user);
            userAccountService.updateUserAccount(user);
            model.addAttribute(Constants.MessageType.SUCCESS, messages.get("update.success"));
        } catch (CoreException e) {
            logger.error(e);
            model.addAttribute(Constants.MessageType.ERROR, e.getMessage());
            return super.replaceContentPage(model, request, Constants.ContentPages.EDIT_USER);
        }
        return super.replaceContentPage(model, request, Constants.ContentPages.EDIT_USER);
    }

    @RequestMapping(value = "/deleteUserAccount", method = RequestMethod.GET)
    public String deleteUserAccount(@RequestParam(name = "userId") String userId, RedirectAttributes redirectAttributes,
                                    HttpServletRequest request) {
        logger.debug("Delete User Account : " + userId);
        try {
            userAccountService.deleteUserAccount(userId);
            redirectAttributes.addFlashAttribute(Constants.MessageType.SUCCESS, messages.get("delete.success"));
            logger.debug("Delete User Account Success : " + userId);
        } catch (CoreException e) {
            logger.error(e);
            redirectAttributes.addFlashAttribute(Constants.MessageType.ERROR, e.getMessage());
            return "redirect:/existingUsers";
        }
        return "redirect:/existingUsers";
    }

}
