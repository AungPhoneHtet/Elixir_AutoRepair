package com.elixir.workshop.beans.report;

import lombok.Data;

@Data
public class VoucherSummaryReport {

    private String voucherNo;
    private String customerName;
    private String carNo;
    private String date;
    private String status;
    private Double amount;
}
