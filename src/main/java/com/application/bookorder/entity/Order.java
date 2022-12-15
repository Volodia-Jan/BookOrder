package com.application.bookorder.entity;

import com.application.bookorder.entity.enumeration.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Order extends BaseEntity{

    private List<OrderDetail> orderDetails;
    private LocalDateTime time;
    private OrderStatus status;

    public Order() {
    }

    public Order(Long id, List<OrderDetail> orderDetails, LocalDateTime time, OrderStatus status) {
        super(id);
        this.orderDetails = orderDetails;
        this.time = time;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Order other = (Order) o;
        return id.equals(other.id) &&
                orderDetails.equals(other.orderDetails) &&
                time.equals(other.time) &&
                status == other.status;
    }

    @Override
    public int hashCode() {
        int result = (id != null ? id.hashCode() : 0);
        result = 31 * result + (orderDetails != null ? orderDetails.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
