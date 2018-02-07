package com.elixir.workshop.constants;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author aphtet
 */
public interface Constants {

    public static final SimpleDateFormat UI_DATE = new SimpleDateFormat("dd/MM/yyyy");

    interface MessageType {
        String SUCCESS = "successMessage";
        String ERROR = "errorMessage";
    }

    interface ContentPages {
        String NEW_VOUCHER = "new_voucher";
        String EXISTING_VOUCHERS = "existing_vouchers";

        String STOCK = "stock";
        String EXISTING_STOCKS = "existing_stocks";

        String EXPENSE = "expense";

        String NEW_USER = "new_user";
        String EDIT_USER = "edit_user";
        String EXISTING_USERS = "existing_users";
    }

    interface URL {
        String HOME = "/home";
        String LOGIN = "/login";
        String LOGIN_ERROR = "/login-error";
        String LOGOUT = "/logout";
        String ERROR = "/error";
    }

    interface Role {
        String ADMIN = "admin";
        String USER = "user";
    }

    interface Status {
        String SAVE = "save";
        String PAID = "paid";
    }
}
