package com.application.bookorder.dto;

import com.application.bookorder.entity.enumeration.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private Long id;
    private List<OrderDetailDTO> orderDetailDTOs;
    private LocalDateTime time;
    private OrderStatus status;
}
