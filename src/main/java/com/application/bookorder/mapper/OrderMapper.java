package com.application.bookorder.mapper;

import com.application.bookorder.dto.OrderDTO;
import com.application.bookorder.dto.OrderDetailDTO;
import com.application.bookorder.entity.Order;
import com.application.bookorder.entity.OrderDetail;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    private final OrderDetailMapper orderDetailMapper;

    public OrderMapper(OrderDetailMapper orderDetailMapper) {
        this.orderDetailMapper = orderDetailMapper;
    }

    public Order toEntity(OrderDTO dto){
        Order entity = new Order();
        entity.setId(dto.getId());
        entity.setTime(dto.getTime());
        entity.setStatus(dto.getStatus());
        if (dto.getOrderDetailDTOs() != null) {
            List<OrderDetail> orderDetails = dto.getOrderDetailDTOs().stream().map(orderDetailMapper::toEntity).toList();
            entity.setOrderDetails(new ArrayList<>(orderDetails));
        }
        return entity;
    }

    public OrderDTO toDTO(Order entity){
        OrderDTO dto = new OrderDTO();
        dto.setId(entity.getId());
        dto.setTime(entity.getTime());
        dto.setStatus(entity.getStatus());
        if (entity.getOrderDetails() != null){
            List<OrderDetailDTO> orderDetails = entity.getOrderDetails().stream().map(orderDetailMapper::toDTO).toList();
            dto.setOrderDetailDTOs(new ArrayList<>(orderDetails));
        }
        return dto;
    }
}
