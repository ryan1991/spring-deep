package com.kinbo.boot2deep.controller.vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author songjunbao
 * @date 2024-08-29
 */
public class CreateOrderItemInfo implements Serializable {
    private Long goodsId;
    private String goodsName;
    private BigDecimal goodsAmount;

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public BigDecimal getGoodsAmount() {
        return goodsAmount;
    }

    public void setGoodsAmount(BigDecimal goodsAmount) {
        this.goodsAmount = goodsAmount;
    }
}
