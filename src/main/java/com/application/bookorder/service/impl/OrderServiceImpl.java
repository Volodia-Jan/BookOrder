package com.application.bookorder.service.impl;

import com.application.bookorder.dto.OrderDTO;
import com.application.bookorder.dto.OrderDetailAction;
import com.application.bookorder.dto.OrderDetailDTO;
import com.application.bookorder.entity.Hotel;
import com.application.bookorder.entity.Order;
import com.application.bookorder.entity.OrderDetail;
import com.application.bookorder.entity.Room;
import com.application.bookorder.entity.enumeration.OrderStatus;
import com.application.bookorder.exception.ServiceException;
import com.application.bookorder.mapper.OrderDetailMapper;
import com.application.bookorder.mapper.OrderMapper;
import com.application.bookorder.repository.HotelRepository;
import com.application.bookorder.repository.OrderRepository;
import com.application.bookorder.service.OrderService;
import com.application.bookorder.util.OrderStatusUtil;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final HotelRepository hotelRepository;
    private final OrderDetailMapper detailMapper;
    private final OrderMapper mapper;

    public OrderServiceImpl(OrderRepository orderRepository, HotelRepository hotelRepository, OrderDetailMapper detailMapper, OrderMapper mapper) {
        this.orderRepository = orderRepository;
        this.hotelRepository = hotelRepository;
        this.detailMapper = detailMapper;
        this.mapper = mapper;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public OrderDTO getOrderById(Long orderId) {
        return mapper.toDTO(orderRepository.findById(orderId));
    }

    @Override
    public OrderDTO addNewOrder(OrderDTO orderDTO) {
        Order newOrder = mapper.toEntity(orderDTO);
        newOrder.setStatus(OrderStatus.OPEN);
        return mapper.toDTO(orderRepository.save(newOrder));
    }

    @Override
    public OrderDTO pendingOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (!OrderStatusUtil.canBePending(order))
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "Order cannot be moved to pending status.",
                    String.format("Order id:%d, current status:%s", orderId, order.getStatus()));
        order.setStatus(OrderStatus.PENDING);
        return mapper.toDTO(orderRepository.update(order));
    }

    @Override
    public OrderDTO cancelOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (!OrderStatusUtil.canBeCancelled(order))
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "Order cannot be moved to canceled status.",
                    String.format("Order id:%d, current status:%s", orderId, order.getStatus()));
        order.setStatus(OrderStatus.CANCELED);
        return mapper.toDTO(orderRepository.update(order));
    }

    @Override
    public OrderDTO closeOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId);
        if (!OrderStatusUtil.canBeClosed(order))
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "Order cannot be moved to closed status.",
                    String.format("Order id:%d, current status:%s", orderId, order.getStatus()));
        order.setStatus(OrderStatus.CLOSED);
        order.setTime(LocalDateTime.now());
        for (var orderDetail : order.getOrderDetails())
            orderDetail.getRoom().setBooked(true);
        return mapper.toDTO(orderRepository.update(order));
    }

    @Override
    public OrderDTO addOrderDetail(OrderDetailAction addOrderDetail) {
        Order order = orderRepository.findById(addOrderDetail.getOrderId());
        if (!OrderStatusUtil.canBeModified(order))
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "Order cannot be modified.",
                    String.format("Order id:%d, current status:%s", order.getId(), order.getStatus()));
        Hotel hotel = hotelRepository.findById(addOrderDetail.getHotelId());
        Room room = hotel.getRooms().stream()
                .filter(r -> r.getNumber().equals(addOrderDetail.getRoomNumber()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("Cannot find room by number:%d", addOrderDetail.getRoomNumber())));
        if (room.isBooked())
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "Room is already booked",
                    String.format("Hotel id:%d, Room id:%d", hotel.getId(), room.getNumber()));
        OrderDetail orderDetail = new OrderDetail(hotel, room);
        List<OrderDetail> orderDetails = order.getOrderDetails();
        if (orderDetails != null) {
            if (orderDetails.contains(orderDetail))
                throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                        "Room is already in order",
                        String.format("Order id:%d, Hotel id:%d, Room id:%d",
                                order.getId(), hotel.getId(), room.getNumber()));
            orderDetails.add(orderDetail);
        } else {
            List<OrderDetail> newOrderDetails = new ArrayList<>();
            newOrderDetails.add(orderDetail);
            order.setOrderDetails(newOrderDetails);
        }
        return mapper.toDTO(orderRepository.update(order));
    }

    @Override
    public OrderDTO deleteOrderDetail(OrderDetailAction deleteOrderDetail) {
        Order order = orderRepository.findById(deleteOrderDetail.getHotelId());
        if (!OrderStatusUtil.canBeModified(order))
            throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                    "Order cannot be modified.",
                    String.format("Order id:%d, current status:%s", order.getId(), order.getStatus()));
        if (order.getOrderDetails() != null || !order.getOrderDetails().isEmpty()) {
            OrderDetailDTO orderDetailDTO = new OrderDetailDTO(deleteOrderDetail.getHotelId(), deleteOrderDetail.getRoomNumber());
            List<OrderDetail> orderDetails = order.getOrderDetails().stream()
                    .filter(orderDetail -> !orderDetail.equals(detailMapper.toEntity(orderDetailDTO)))
                    .toList();
            order.setOrderDetails(new ArrayList<>(orderDetails));
            return mapper.toDTO(orderRepository.update(order));
        }
        throw new ServiceException(HttpStatus.BAD_REQUEST.value(),
                "Cannot delete order details cause its empty or null.",
                String.format("Order id:%d", order.getId()));
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
