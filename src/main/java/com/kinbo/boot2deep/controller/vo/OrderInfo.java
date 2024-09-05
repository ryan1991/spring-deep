package com.kinbo.boot2deep.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author songjunbao
 * @date 2024-08-29
 */
public class OrderInfo implements Serializable {

    private Long id;
    private Long orderId;
    private Long userId;
    private BigDecimal orderAmount;
    private Integer valid;
    //正常情况 字符串格式
    private Date createTime;
    private Date updateTime;
    private List<OrderItemInfo> itemInfos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
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

    public List<OrderItemInfo> getItemInfos() {
        return itemInfos;
    }

    public void setItemInfos(List<OrderItemInfo> itemInfos) {
        this.itemInfos = itemInfos;
    }
}
