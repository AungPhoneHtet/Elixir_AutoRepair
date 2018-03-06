package com.elixir.workshop.beans.report;

import lombok.Data;

import java.io.Serializable;

@Data
public class CommonReport implements Serializable {

    private static final long serialVersionUID = 1L;

    private String reportName;
    private String outputType;
    private String fromPeriod;
    private String toPeriod;
    private String status;

}
