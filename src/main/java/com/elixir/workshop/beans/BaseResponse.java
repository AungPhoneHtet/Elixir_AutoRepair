/**
 *
 */
package com.elixir.workshop.beans;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author aphtet
 */

@Data
@Builder
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
    private String[] data;

}
