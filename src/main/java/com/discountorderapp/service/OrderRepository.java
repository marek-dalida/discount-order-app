package com.discountorderapp.service;

import com.discountorderapp.model.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    private final Map<String, Order> store = new HashMap<>();

    public void save(Order order) {
        store.put(order.getOrderCode(), order);
    }

    public Order getOrder(String orderId) {
        return store.get(orderId);
    }

    public List<Order> getOrders() {return store.values().stream().toList();}
}
