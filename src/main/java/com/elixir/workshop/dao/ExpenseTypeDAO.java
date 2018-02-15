package com.elixir.workshop.dao;

import com.elixir.workshop.beans.ExpenseType;

import java.util.List;

public interface ExpenseTypeDAO {

    List<ExpenseType> findAll();
}
