package com.kinbo.boot2deep.dao;

import com.kinbo.boot2deep.entity.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author songjunbao
 * @date 2024-08-29
 */
@Mapper
public interface OrderMapper {

    @Insert("insert into t_order(order_id, user_id, order_amount,valid, create_time,update_time) values(#{order.orderId}, #{order.userId},#{order.orderAmount},#{order.valid},#{order.createTime},#{order.updateTime})")
    int insertOne(@Param("order") Order order);

}
