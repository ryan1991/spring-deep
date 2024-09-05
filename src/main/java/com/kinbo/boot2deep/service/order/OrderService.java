package com.kinbo.boot2deep.service.order;

import com.google.common.collect.Lists;
import com.kinbo.boot2deep.common.ValidStatus;
import com.kinbo.boot2deep.controller.vo.CreateOrderInfo;
import com.kinbo.boot2deep.controller.vo.CreateOrderItemInfo;
import com.kinbo.boot2deep.controller.vo.OrderInfo;
import com.kinbo.boot2deep.controller.vo.OrderItemInfo;
import com.kinbo.boot2deep.dao.OrderItemMapper;
import com.kinbo.boot2deep.dao.OrderMapper;
import com.kinbo.boot2deep.entity.Order;
import com.kinbo.boot2deep.entity.OrderItem;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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


    /**
     * 查询订单信息
     * @param userId
     * @param orderId
     * @return
     */
    public OrderInfo queryOrderInfo(Long userId, Long orderId){
        Order order = orderMapper.selectByOrderId(userId, orderId);
        List<OrderItem> orderItems = orderItemMapper.selectByOrderId(userId, orderId);
        if (Objects.isNull(order) || CollectionUtils.isEmpty(orderItems)){
            return new OrderInfo();
        }
        OrderInfo orderInfo = convertOrderInfo(order, orderItems);
        return orderInfo;
    }

    private OrderInfo convertOrderInfo(Order order, List<OrderItem> orderItems){
        OrderInfo orderInfo = new OrderInfo();
        List<OrderItemInfo> orderItemInfos = Lists.newArrayListWithCapacity(orderItems.size());
        //简单复制
        BeanUtils.copyProperties(order, orderInfo);
        for (OrderItem orderItem : orderItems){
            OrderItemInfo orderItemInfo = new OrderItemInfo();
            BeanUtils.copyProperties(orderItem, orderItemInfo);
            orderItemInfos.add(orderItemInfo);
        }
        orderInfo.setItemInfos(orderItemInfos);
        return orderInfo;
    }
}
