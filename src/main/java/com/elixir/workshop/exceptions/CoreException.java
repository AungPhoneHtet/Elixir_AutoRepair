package com.elixir.workshop.exceptions;

import org.springframework.beans.factory.annotation.Autowired;

import com.elixir.workshop.Messages;

public final class CoreException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 2186675328681507845L;

    @Autowired
    Messages messages;

    private String message;

    public CoreException(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message == null ? messages.get("system.error") : message;
    }

}
