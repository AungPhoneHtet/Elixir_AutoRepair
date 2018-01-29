package com.elixir.workshop.beans;

import lombok.Data;

import java.io.Serializable;

@Data
public class Stock implements Serializable {

    private long id;
    private String code;
    private String description;
    private String brand;
    private double buyPrice;
    private double sellPrice;
    private long qty;

}
