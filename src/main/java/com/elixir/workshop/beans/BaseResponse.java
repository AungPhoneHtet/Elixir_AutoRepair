/**
 *
 */
package com.elixir.workshop.beans;

import java.io.Serializable;

/**
 * @author aphtet
 */
public class BaseResponse implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2153854324288146252L;
    public static final String MESSAGE_TITLE_SUCCESS = "Success";
    public static final String MESSAGE_TITLE_FAIL = "Fail";
    public static final String MESSAGE_TYPE_SUCCESS = "success";
    public static final String MESSAGE_TYPE_ERROR = "error";

    private String messageTitle;
    private String messageType;
    private String messageDesc;


    public BaseResponse(String messageTitle, String messageType, String messageDesc) {
        super();
        this.messageTitle = messageTitle;
        this.messageType = messageType;
        this.messageDesc = messageDesc;
    }


    public String getMessageTitle() {
        return messageTitle;
    }


    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }


    public String getMessageType() {
        return messageType;
    }


    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }


    public String getMessageDesc() {
        return messageDesc;
    }


    public void setMessageDesc(String messageDesc) {
        this.messageDesc = messageDesc;
    }

}
