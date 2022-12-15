package com.application.bookorder.repository;

import com.application.bookorder.entity.Order;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository extends BaseRepository<Order> {

    @Override
    public Order update(Order entity) {
        Order updated = findById(entity.getId());
        updated.setOrderDetails(entity.getOrderDetails());
        updated.setStatus(entity.getStatus());
        updated.setTime(entity.getTime());
        return updated;
    }
}
