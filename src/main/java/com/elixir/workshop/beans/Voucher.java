package com.elixir.workshop.beans;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Voucher extends Root {

    private String voucherNo;
    private String customerName;
    private String carNo;
    private String date;
    private String status;
    private List<Item> items;
}
