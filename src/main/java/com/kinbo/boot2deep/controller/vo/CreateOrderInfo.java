package com.kinbo.boot2deep.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author songjunbao
 * @date 2024-08-29
 */
public class CreateOrderInfo implements Serializable {

    private Long orderId;
    private Long userId;
    private BigDecimal orderAmount;
    private List<CreateOrderItemInfo> itemInfos;

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

    public List<CreateOrderItemInfo> getItemInfos() {
        return itemInfos;
    }

    public void setItemInfos(List<CreateOrderItemInfo> itemInfos) {
        this.itemInfos = itemInfos;
    }
}
