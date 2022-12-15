package com.application.bookorder.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailAction {
    @NotNull
    private Long orderId;
    @NotNull
    private Long hotelId;
    @NotNull
    private Integer roomNumber;
}
