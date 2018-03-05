package com.elixir.workshop.service.impl;

import com.elixir.workshop.beans.Transaction;
import com.elixir.workshop.dao.TransactionDAO;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.TransactionService;
import com.elixir.workshop.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final static Logger logger = Logger.getLogger(TransactionServiceImpl.class);

    @Autowired
    TransactionDAO transactionDAO;

    @Override
    public void save(Transaction transaction) throws CoreException {
        logger.info("save trans : " + transaction.getTransDesc() + " with : " + transaction.getAmount());
        initTransRef(transaction);
        transactionDAO.save(transaction);
    }

    private void initTransRef(Transaction transaction) {
        transaction.setTransRef(DateUtils.getCurrentDateInYYYYMMDD() + "-" + transactionDAO.getTransactionSerial());
    }
}
