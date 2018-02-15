package com.elixir.workshop.service;

import com.elixir.workshop.beans.ExpenseType;
import com.elixir.workshop.exceptions.CoreException;

import java.util.List;

public interface ExpenseTypeService {

    List<ExpenseType> findAll() throws CoreException;
}
