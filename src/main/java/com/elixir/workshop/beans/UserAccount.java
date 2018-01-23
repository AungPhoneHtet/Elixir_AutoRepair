package com.elixir.workshop.beans;

import lombok.Data;

@Data
public class UserAccount extends Root {

    private String userName;
    private String password;
    private String userId;
    private String role;

    public UserAccount() {
        super();
    }

}
