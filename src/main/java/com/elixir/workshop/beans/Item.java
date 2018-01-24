package com.elixir.workshop.beans;


import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {

    private String item;
    private double price;
    private long qty;
    private double amount;

}
