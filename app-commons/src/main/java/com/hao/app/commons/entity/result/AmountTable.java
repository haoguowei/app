package com.hao.app.commons.entity.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class AmountTable implements Serializable {

    private int projects;

    private int type3;

    private BigDecimal amount;
}
