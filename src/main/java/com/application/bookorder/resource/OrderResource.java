package com.application.bookorder.resource;

import com.application.bookorder.dto.OrderDetailAction;
import com.application.bookorder.dto.OrderDTO;
import com.application.bookorder.dto.OrderDetailDTO;
import com.application.bookorder.service.OrderService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderResource {

    private final OrderService orderService;

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDTO> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping(path = "/{id}")
    public OrderDTO getOrderById(@PathVariable("id") Long orderId) {
        return orderService.getOrderById(orderId);
    }

    @PostMapping
    public OrderDTO addNewOrder() {
        return orderService.addNewOrder(new OrderDTO());
    }

    @PatchMapping(path = "/{id}/pending")
    public OrderDTO pendingOrderById(@PathVariable("id") Long orderId) {
        return orderService.pendingOrderById(orderId);
    }

    @PatchMapping(path = "/{id}/cancel")
    public OrderDTO cancelOrderById(@PathVariable("id") Long orderId) {
        return orderService.cancelOrderById(orderId);
    }

    @PatchMapping(path = "/{id}/close")
    public OrderDTO closeOrderById(@PathVariable("id") Long orderId) {
        return orderService.closeOrderById(orderId);
    }

    @PatchMapping(path = "/add-order-detail")
    public OrderDTO addOrderDetail(@RequestBody @Validated OrderDetailAction addOrderDetail) {
        return orderService.addOrderDetail(addOrderDetail);
    }

    @DeleteMapping
    public OrderDTO deleteOrderDetail(@RequestBody @Validated OrderDetailAction deleteOrderDetail) {
        return orderService.deleteOrderDetail(deleteOrderDetail);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteOrderById(@PathVariable("id") Long orderId) {
        orderService.deleteOrderById(orderId);
    }
}
