package com.elixir.workshop;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {

    @Autowired
    private MessageSource messageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(messageSource, Locale.US);
    }

    public String get(String code) {
        try {
            return accessor.getMessage(code);
        } catch (NoSuchMessageException e) {
            return null;
        }

    }

    public String get(String code, Object[] values) {
        return accessor.getMessage(code, values);
    }

    public String get(String code, int... values) {
        return accessor.getMessage(code, new Object[]{values});
    }

    public String get(String code, String... values) {
        return accessor.getMessage(code, new Object[]{values});
    }
}
