package com.elixir.workshop.constants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author aphtet
 */
public interface Constants {

    interface MessageType {
        String SUCCESS = "successMessage";
        String ERROR = "errorMessage";
    }

    interface ContentPages {
        String BLANK_PAGE = "blank_page";
        String NEW_USER = "new_user";
        String EDIT_USER = "edit_user";
        String EXISTING_USERS = "existing_users";
    }

    interface URL {
        String HOME = "/home";
        String LOGIN = "/login";
        String LOGIN_ERROR = "/login_error";
        String LOGOUT = "/logout";
        String ERROR = "/error";
    }

    interface Role {
        String ADMIN = "admin";
        String USER = "user";
    }
}
