package com.elixir.workshop.dao;

import com.elixir.workshop.beans.Transaction;

public interface TransactionDAO {

    void save(Transaction transaction);

    String getTransactionSerial();
}
