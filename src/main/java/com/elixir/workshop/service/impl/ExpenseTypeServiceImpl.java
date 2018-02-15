package com.elixir.workshop.service.impl;


import com.elixir.workshop.beans.ExpenseType;
import com.elixir.workshop.dao.ExpenseTypeDAO;
import com.elixir.workshop.exceptions.CoreException;
import com.elixir.workshop.service.ExpenseService;
import com.elixir.workshop.service.ExpenseTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseTypeServiceImpl implements ExpenseTypeService {

    @Autowired
    ExpenseTypeDAO expenseTypeDAO;

    @Override
    public List<ExpenseType> findAll() throws CoreException {
        return expenseTypeDAO.findAll();
    }
}
