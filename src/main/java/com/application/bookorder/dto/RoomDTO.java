package com.application.bookorder.dto;

import com.application.bookorder.entity.enumeration.RoomType;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomDTO {
    @NotNull
    @Min(
            value = 0,
            message = "Number have to be greater than zero"
    )
    private Integer number;
    @NotNull
    private RoomType type;
    private boolean isBooked = false;
    @NotNull
    private Integer price;
}
