package com.kinbo.boot2deep.service.order;

import com.google.common.collect.Lists;
import com.kinbo.boot2deep.common.ValidStatus;
import com.kinbo.boot2deep.controller.vo.CreateOrderInfo;
import com.kinbo.boot2deep.controller.vo.CreateOrderItemInfo;
import com.kinbo.boot2deep.dao.OrderItemMapper;
import com.kinbo.boot2deep.dao.OrderMapper;
import com.kinbo.boot2deep.entity.Order;
import com.kinbo.boot2deep.entity.OrderItem;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author songjunbao
 * @date 2024-08-29
 */
@Service
public class OrderService {


    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    /**
     * 创建订单
     * @return
     */
    @Transactional
    public boolean createOrder(CreateOrderInfo createOrderInfo){
        Order order = convertOrder(createOrderInfo);
        List<OrderItem> orderItems = convertOrderItem(createOrderInfo);
        orderMapper.insertOne(order);
        for(OrderItem orderItem : orderItems){
            orderItemMapper.insertOne(orderItem);
        }
        return true;
    }


    private Order convertOrder(CreateOrderInfo orderInfo){
        Order order = new Order();
        order.setOrderId(orderInfo.getOrderId());
        order.setOrderAmount(orderInfo.getOrderAmount());
        order.setUserId(orderInfo.getUserId());
        order.setValid(ValidStatus.VAILD.getCode());
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        return order;
    }

    private List<OrderItem> convertOrderItem(CreateOrderInfo orderInfo){
        if (CollectionUtils.isEmpty(orderInfo.getItemInfos())){
            return Collections.emptyList();
        }

        List<OrderItem> orderItems = Lists.newArrayListWithCapacity(orderInfo.getItemInfos().size());
        for (CreateOrderItemInfo orderItemInfo : orderInfo.getItemInfos()){
            OrderItem orderItem = new OrderItem();
            orderItem.setGoodsId(orderItemInfo.getGoodsId());
            orderItem.setGoodsAmount(orderItemInfo.getGoodsAmount());
            orderItem.setGoodsName(orderItemInfo.getGoodsName());
            orderItem.setOrderId(orderInfo.getOrderId());
            orderItem.setUserId(orderInfo.getUserId());
            orderItem.setValid(ValidStatus.VAILD.getCode());
            orderItem.setCreateTime(new Date());
            orderItem.setUpdateTime(new Date());
            orderItems.add(orderItem);
        }
        return orderItems;
    }

}
