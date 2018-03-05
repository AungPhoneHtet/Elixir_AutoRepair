package com.elixir.workshop.service.impl;


import com.elixir.workshop.beans.Expense;
import com.elixir.workshop.beans.Item;
import com.elixir.workshop.beans.Transaction;
import com.elixir.workshop.beans.Voucher;
import com.elixir.workshop.constants.Constants;
import com.elixir.workshop.dao.ExpenseDAO;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.ExpenseService;
import com.elixir.workshop.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    ExpenseDAO expenseDAO;

    @Autowired
    private TransactionService transactionService;

    @Override
    public Expense save(Expense expense) {
        expense.setStatus(Constants.Status.SAVE);
        return expenseDAO.save(expense);
    }

    @Override
    public void paid(long id) throws CoreException {
        expenseDAO.updateStatus(id, Constants.Status.PAID);
        Transaction transaction = prepareTransaction(expenseDAO.findById(id));
        transactionService.save(transaction);
    }

    private Transaction prepareTransaction(Expense expense) {
        Transaction transaction = new Transaction();
        transaction.setTransDesc(Constants.Transaction.EXPENSE_TRANS_DESC + expense.getId());
        transaction.setAmount(expense.getAmount());
        return transaction;
    }

    @Override
    public void delete(long id) {
        expenseDAO.delete(id);
    }

    @Override
    public List<Expense> findByDate(Date date) {
        return expenseDAO.findByDate(date);
    }

    @Override
    public Expense findById(long id) {
        return expenseDAO.findById(id);
    }
}
