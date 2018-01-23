package com.elixir.workshop.security;

import java.util.ArrayList;

import com.elixir.workshop.beans.UserAccount;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.UserAccountService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = Logger.getLogger(CustomAuthenticationProvider.class);

    @Autowired
    UserAccountService userAccountService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String password = authentication.getCredentials().toString();

        logger.info("Name = " + userId + " ,Password = " + password);

        // use the credentials and authenticate against the third-party system
        UserAccount userAccountRDTO = null;
        try {
            userAccountRDTO = userAccountService.getUserAccountByUserId(userId);

        } catch (CoreException e) {
            logger.error(e);
            return null;
        }
        if (userAccountRDTO != null) {
            if (BCrypt.checkpw(password, userAccountRDTO.getPassword())) {
                logger.info("Succesful authentication!");
                return new UsernamePasswordAuthenticationToken(userAccountRDTO, password, new ArrayList<>());
            }
        }

        logger.info("Login fail!");
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
