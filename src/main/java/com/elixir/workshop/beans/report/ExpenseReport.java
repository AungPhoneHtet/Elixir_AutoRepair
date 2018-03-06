package com.elixir.workshop.beans.report;

import lombok.Data;

import java.io.Serializable;

@Data
public class ExpenseReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String description;
    private Double amount;
    private String relatedVoucherNo;
    private String expenseDate;
    private String expenseType;
    private String status;
}
