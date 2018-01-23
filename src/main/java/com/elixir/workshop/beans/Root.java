package com.elixir.workshop.beans;

import java.util.Date;

import com.elixir.workshop.utils.UserInfoUtil;
import lombok.Data;

@Data
public class Root {

    private long id;
    private String createdBy;
    private Date createdDate;
    private String updatedBy;
    private Date updatedDate;

    public Root() {
        createdBy = UserInfoUtil.getCurrentUser() == null ? "admin" : UserInfoUtil.getCurrentUser().getUserId();
        createdDate = new Date();
        updatedBy = UserInfoUtil.getCurrentUser() == null ? "admin" : UserInfoUtil.getCurrentUser().getUserId();
        updatedDate = new Date();
    }


}
