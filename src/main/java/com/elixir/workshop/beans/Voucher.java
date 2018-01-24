package com.elixir.workshop.beans;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Voucher {

    private String voucherNo;
    private String customerName;
    private String carNo;
    private Date date;
    private List<Item> items;
}
