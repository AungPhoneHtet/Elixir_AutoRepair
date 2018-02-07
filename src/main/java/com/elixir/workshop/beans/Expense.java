package com.elixir.workshop.beans;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Expense extends Root implements Serializable {

    private static final long serialVersionUID = 1L;
    private String description;
    private Double amount;
    private String relatedVoucherNo;
    private String expenseDate;

}
