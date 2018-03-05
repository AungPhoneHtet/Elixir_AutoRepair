package com.elixir.workshop.beans;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Transaction implements Serializable {

    private long id;
    private String transRef;
    private String transDesc;
    private double amount;
    private Date transDate;
}
