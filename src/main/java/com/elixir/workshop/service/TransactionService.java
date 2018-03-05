package com.elixir.workshop.service;

import com.elixir.workshop.beans.Transaction;
import com.elixir.workshop.exceptions.CoreException;

public interface TransactionService {

    void save(Transaction transaction) throws CoreException;
}
