package com.elixir.workshop.service;

import com.elixir.workshop.beans.UserAccount;
import com.elixir.workshop.exceptions.CoreException;

import java.util.List;

public interface UserAccountService {

    void saveUser(UserAccount userRDTO) throws CoreException;

    List<UserAccount> findAll() throws CoreException;

    UserAccount getUserAccountByUserId(String userId) throws CoreException;

    void deleteUserAccount(String userId) throws CoreException;

    void updateUserAccount(UserAccount userAccount) throws CoreException;

}
