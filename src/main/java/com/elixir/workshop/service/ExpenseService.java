package com.elixir.workshop.service;

import com.elixir.workshop.beans.Expense;
import com.elixir.workshop.exceptions.CoreException;

import java.util.Date;
import java.util.List;

public interface ExpenseService {

    Expense save(Expense expense) throws CoreException;

    void paid(long id) throws CoreException;

    void delete(long id) throws CoreException;

    List<Expense> findByDate(Date date) throws CoreException;

    Expense findById(long id) throws CoreException;

}
