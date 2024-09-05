package com.kinbo.boot2deep.controller;
import com.kinbo.boot2deep.controller.vo.CreateOrderInfo;
import com.kinbo.boot2deep.controller.vo.OrderInfo;
import com.kinbo.boot2deep.service.order.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {


    @Autowired
    private OrderService orderService;


    @PostMapping("/createOrder")
    public boolean createOrder(@RequestBody CreateOrderInfo createOrderInfo){

        return orderService.createOrder(createOrderInfo);
    }


    @GetMapping("/queryOrderInfo")
    public OrderInfo queryOrderInfo(Long userId, Long orderId){

        return orderService.queryOrderInfo(userId, orderId);
    }


}
