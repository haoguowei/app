package com.hao.app.pojo;

import com.hao.app.commons.entity.Dicts;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


@Data
@ToString
public class OtherCostDO implements Serializable {

    private Integer id;

    private Integer projects;

    private String projectsName;

    private Date payDay;

    private Integer payType; //pay_type
    private String payTypeStr; //pay_type

    private BigDecimal payAmount;//pay_amount

    private String remark;

    private String creater;

    private Date createTime;

    private Date updateTime;

    public void setPayType(Integer payType) {
        this.payType = payType;
        this.payTypeStr = Dicts.otherPayTypeMap.get(payType);
    }

}