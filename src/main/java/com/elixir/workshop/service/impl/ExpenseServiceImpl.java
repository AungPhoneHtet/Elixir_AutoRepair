package com.elixir.workshop.service.impl;


import com.elixir.workshop.beans.Expense;
import com.elixir.workshop.dao.ExpenseDAO;
import com.elixir.workshop.service.ExpenseService;
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

    @Override
    public Expense save(Expense expense) {
        return expenseDAO.save(expense);
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
