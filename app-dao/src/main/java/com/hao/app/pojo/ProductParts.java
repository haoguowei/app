package com.hao.app.pojo;

import java.io.Serializable;
import java.util.Date;

public class ProductParts implements Serializable {
    private Integer id;

    private String contractNo;

    private String proNo;

    private String partsNo;

    private String partsName;

    private String partsFmt;

    private String partsImg;

    private String units;

    private Double hetongNum;

    private Double kucunNum;

    private Double buyNum;

    private Integer isReceive;

    private Integer isSendout;

    private Double receiveNum;

    private Double sendoutNum;

    private Double skSumkg;

    private Double skHszb;

    private Double skHskg;

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

    public String getPartsNo() {
        return partsNo;
    }

    public void setPartsNo(String partsNo) {
        this.partsNo = partsNo == null ? null : partsNo.trim();
    }

    public String getPartsName() {
        return partsName;
    }

    public void setPartsName(String partsName) {
        this.partsName = partsName == null ? null : partsName.trim();
    }

    public String getPartsFmt() {
        return partsFmt;
    }

    public void setPartsFmt(String partsFmt) {
        this.partsFmt = partsFmt == null ? null : partsFmt.trim();
    }

    public String getPartsImg() {
        return partsImg;
    }

    public void setPartsImg(String partsImg) {
        this.partsImg = partsImg == null ? null : partsImg.trim();
    }

    public String getUnits() {
        return units;
    }

    public void setUnits(String units) {
        this.units = units == null ? null : units.trim();
    }

    public Double getHetongNum() {
        return hetongNum;
    }

    public void setHetongNum(Double hetongNum) {
        this.hetongNum = hetongNum;
    }

    public Double getKucunNum() {
        return kucunNum;
    }

    public void setKucunNum(Double kucunNum) {
        this.kucunNum = kucunNum;
    }

    public Double getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(Double buyNum) {
        this.buyNum = buyNum;
    }

    public Integer getIsReceive() {
        return isReceive;
    }

    public void setIsReceive(Integer isReceive) {
        this.isReceive = isReceive;
    }

    public Integer getIsSendout() {
        return isSendout;
    }

    public void setIsSendout(Integer isSendout) {
        this.isSendout = isSendout;
    }

    public Double getReceiveNum() {
        return receiveNum;
    }

    public void setReceiveNum(Double receiveNum) {
        this.receiveNum = receiveNum;
    }

    public Double getSendoutNum() {
        return sendoutNum;
    }

    public void setSendoutNum(Double sendoutNum) {
        this.sendoutNum = sendoutNum;
    }

    public Double getSkSumkg() {
        return skSumkg;
    }

    public void setSkSumkg(Double skSumkg) {
        this.skSumkg = skSumkg;
    }

    public Double getSkHszb() {
        return skHszb;
    }

    public void setSkHszb(Double skHszb) {
        this.skHszb = skHszb;
    }

    public Double getSkHskg() {
        return skHskg;
    }

    public void setSkHskg(Double skHskg) {
        this.skHskg = skHskg;
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