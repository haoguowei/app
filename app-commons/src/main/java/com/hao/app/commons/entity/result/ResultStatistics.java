package com.hao.app.commons.entity.result;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class ResultStatistics implements Serializable {

    private Integer k;

    private BigDecimal val;
}
