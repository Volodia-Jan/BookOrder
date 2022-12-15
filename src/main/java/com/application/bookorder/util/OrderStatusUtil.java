package com.application.bookorder.util;

import com.application.bookorder.entity.Order;
import com.application.bookorder.entity.enumeration.OrderStatus;

public class OrderStatusUtil {

    public static boolean canBeModified(Order order){
        return order.getStatus() == OrderStatus.OPEN;
    }

    public static boolean canBeCancelled(Order order){
        return order.getStatus() == OrderStatus.OPEN || order.getStatus() == OrderStatus.PENDING;
    }

    public static boolean canBeClosed(Order order){
        return order.getStatus() == OrderStatus.PENDING;
    }

    public static boolean canBePending(Order order){
        return order.getStatus() == OrderStatus.OPEN;
    }
}

