package com.wz.cashloan.core.model;

import java.util.Date;

public class ProductLoan {
    private Long id;

    private String name;

    private String picture;

    private String description;

    private String minAmount;

    private String maxAmount;

    private String minLimit;

    private String maxLimit;

    private String limitUnit;

    private String rate;

    private String rateType;

    private String proCode;

    private Integer sort;

    private String proUrl;

    private String applyCondition;

    private String applyProcessImg;

    private String state;

    private String proInstructions;

    private String labelIds;

    private String applyCount;

    private String hotLabel;

    private String createUser;

    private Date createTime;

    private Date updateTime;

    private String remark;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture == null ? null : picture.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount == null ? null : minAmount.trim();
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount == null ? null : maxAmount.trim();
    }

    public String getMinLimit() {
        return minLimit;
    }

    public void setMinLimit(String minLimit) {
        this.minLimit = minLimit == null ? null : minLimit.trim();
    }

    public String getMaxLimit() {
        return maxLimit;
    }

    public void setMaxLimit(String maxLimit) {
        this.maxLimit = maxLimit == null ? null : maxLimit.trim();
    }

    public String getLimitUnit() {
        return limitUnit;
    }

    public void setLimitUnit(String limitUnit) {
        this.limitUnit = limitUnit == null ? null : limitUnit.trim();
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate == null ? null : rate.trim();
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType == null ? null : rateType.trim();
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode == null ? null : proCode.trim();
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getProUrl() {
        return proUrl;
    }

    public void setProUrl(String proUrl) {
        this.proUrl = proUrl == null ? null : proUrl.trim();
    }

    public String getApplyCondition() {
        return applyCondition;
    }

    public void setApplyCondition(String applyCondition) {
        this.applyCondition = applyCondition == null ? null : applyCondition.trim();
    }

    public String getApplyProcessImg() {
        return applyProcessImg;
    }

    public void setApplyProcessImg(String applyProcessImg) {
        this.applyProcessImg = applyProcessImg == null ? null : applyProcessImg.trim();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public String getProInstructions() {
        return proInstructions;
    }

    public void setProInstructions(String proInstructions) {
        this.proInstructions = proInstructions == null ? null : proInstructions.trim();
    }

    public String getLabelIds() {
        return labelIds;
    }

    public void setLabelIds(String labelIds) {
        this.labelIds = labelIds == null ? null : labelIds.trim();
    }

    public String getApplyCount() {
        return applyCount;
    }

    public void setApplyCount(String applyCount) {
        this.applyCount = applyCount == null ? null : applyCount.trim();
    }

    public String getHotLabel() {
        return hotLabel;
    }

    public void setHotLabel(String hotLabel) {
        this.hotLabel = hotLabel == null ? null : hotLabel.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}