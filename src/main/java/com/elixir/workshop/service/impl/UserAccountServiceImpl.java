package com.elixir.workshop.service.impl;

import java.util.List;

import com.elixir.workshop.Messages;
import com.elixir.workshop.beans.UserAccount;
import com.elixir.workshop.dao.UserAccountDAO;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Autowired
    UserAccountDAO userDAO;

    @Autowired
    Messages messages;

    @Override
    public void saveUser(UserAccount userRDTO) throws CoreException {
        validate(userRDTO);
        userDAO.save(userRDTO);
    }

    private void validate(UserAccount userRDTO) throws CoreException {
        if (userDAO.getByUserId(userRDTO.getUserId()) != null) {
            throw new CoreException(messages.get("user.validate.exist"));
        }

    }

    @Override
    public List<UserAccount> findAll() throws CoreException {
        return userDAO.finalAll();
    }

    @Override
    public UserAccount getUserAccountByUserId(String userId) throws CoreException {
        return userDAO.getByUserId(userId);
    }

    @Override
    public void deleteUserAccount(String userId) throws CoreException {
        userDAO.deleteUserAccount(userId);
    }

    @Override
    public void updateUserAccount(UserAccount userAccount) throws CoreException {
        userDAO.updateUserAccount(userAccount);
    }
}
