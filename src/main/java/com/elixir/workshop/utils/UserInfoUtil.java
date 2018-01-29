package com.elixir.workshop.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.elixir.workshop.beans.UserAccount;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserInfoUtil {

    public static void initUser(HttpServletRequest request, final UserDetails userDetails) {
        HttpSession session = request.getSession();
        session.setAttribute("userName", userDetails.getUsername());
    }

    public static UserDetails getCurrentUserDetails() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        if (authentication != null) {
            Object principal = authentication.getPrincipal();
            return principal instanceof UserDetails ? (UserDetails) principal : null;
        }
        return null;
    }

    public static UserAccount getCurrentUser() {
        if (SecurityContextHolder.getContext().getAuthentication() != null) {
            return (UserAccount) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } else
            return null;
    }
}
