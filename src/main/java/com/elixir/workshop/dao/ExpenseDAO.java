package com.elixir.workshop.dao;

import com.elixir.workshop.beans.Expense;

import java.util.Date;
import java.util.List;

public interface ExpenseDAO {

    Expense save(Expense expense);

    void delete(long id);

    List<Expense> findByDate(Date date);

    Expense findById(long id);

}
