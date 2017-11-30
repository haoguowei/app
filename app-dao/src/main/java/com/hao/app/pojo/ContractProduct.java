package com.hao.app.pojo;

import java.io.Serializable;
import java.util.Date;

public class ContractProduct implements Serializable {
    private Integer id;

    private String contractNo;

    private String proNo;

    private String proName;

    private String proImg;

    private String proUnits;

    private Double proGross;

    private String remark;

    private String userName;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    public String getProNo() {
        return proNo;
    }

    public void setProNo(String proNo) {
        this.proNo = proNo == null ? null : proNo.trim();
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    public String getProImg() {
        return proImg;
    }

    public void setProImg(String proImg) {
        this.proImg = proImg == null ? null : proImg.trim();
    }

    public String getProUnits() {
        return proUnits;
    }

    public void setProUnits(String proUnits) {
        this.proUnits = proUnits == null ? null : proUnits.trim();
    }

    public Double getProGross() {
        return proGross;
    }

    public void setProGross(Double proGross) {
        this.proGross = proGross;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}