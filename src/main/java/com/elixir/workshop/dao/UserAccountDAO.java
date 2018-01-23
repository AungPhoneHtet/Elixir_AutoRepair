package com.elixir.workshop.dao;


import com.elixir.workshop.beans.UserAccount;
import com.elixir.workshop.exceptions.CoreException;

import java.util.List;

public interface UserAccountDAO {

    UserAccount save(UserAccount userRDTO) throws CoreException;

    List<UserAccount> finalAll() throws CoreException;

    UserAccount getByUserId(String userId) throws CoreException;

    void deleteUserAccount(String userId) throws CoreException;

    void updateUserAccount(UserAccount userAccount) throws CoreException;

}
