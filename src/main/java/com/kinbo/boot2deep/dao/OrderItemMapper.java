package com.kinbo.boot2deep.dao;

import com.kinbo.boot2deep.entity.OrderItem;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author songjunbao
 * @date 2024-08-29
 */
@Mapper
public interface OrderItemMapper {

    @Insert("insert into t_order_item(goods_id,order_id, user_id, goods_name, goods_amount,valid, create_time,update_time) values(#{orderItem.goodsId},#{orderItem.orderId}, #{orderItem.userId},#{orderItem.goodsName},#{orderItem.goodsAmount},#{orderItem.valid},#{orderItem.createTime},#{orderItem.updateTime})")
    int insertOne(@Param("orderItem") OrderItem orderItem);

}
