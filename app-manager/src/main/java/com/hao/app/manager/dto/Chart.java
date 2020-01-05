package com.hao.app.manager.dto;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@ToString
public class Chart {

    private String label;

    private BigDecimal y; //百分比

    private BigDecimal pay;//金额

    public Chart(String label, BigDecimal pay) {
        this.label = label;
        this.y = BigDecimal.valueOf(0);
        this.pay = pay;
    }

    public Chart(String label, BigDecimal y, BigDecimal pay) {
        this.label = label;
        this.y = y;
        this.pay = pay;
    }

}
