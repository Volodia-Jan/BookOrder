package com.application.bookorder.service;

import com.application.bookorder.dto.OrderDetailAction;
import com.application.bookorder.dto.OrderDTO;
import com.application.bookorder.dto.OrderDetailDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getAllOrders();

    OrderDTO getOrderById(Long orderId);

    OrderDTO addNewOrder(OrderDTO orderDTO);

    OrderDTO pendingOrderById(Long orderId);

    OrderDTO cancelOrderById(Long orderId);

    OrderDTO closeOrderById(Long orderId);

    OrderDTO addOrderDetail(OrderDetailAction addOrderDetail);

    OrderDTO deleteOrderDetail(OrderDetailAction deleteOrderDetail);

    void deleteOrderById(Long orderId);
}
