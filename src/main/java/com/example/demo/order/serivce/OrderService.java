package com.example.demo.order.serivce;

import com.example.demo.order.model.Order;
import com.example.demo.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Cacheable(value = "orders", key = "'order:' + #orderCode", unless = "#result == null")
    public Order getOrderByOrderCode(String orderCode) {
        System.out.println("Lấy dữ liệu từ Database!");
        return orderRepository.findByOrderCode(orderCode);
    }
//    @Autowired
//    private RedisTemplate<String, Object> redisTemplate;

//    public Order saveOrder(Order order) {
//        Order savedOrder = orderRepository.save(order);
//        // Sau khi lưu vào DB, lưu vào Redis để truy xuất nhanh sau này
//        redisTemplate.opsForValue().set("order:" + savedOrder.getId(), savedOrder, 10, TimeUnit.MINUTES);
//        return savedOrder;
//    }
//
//    public Order getOrderByOrderCode(String orderCode) {
//        String key = "order:" + orderCode;
//
//        // 1. Kiểm tra trong Redis trước (Cache Aside Pattern)
//        Order cachedOrder = (Order) redisTemplate.opsForValue().get(key);
//        if (cachedOrder != null) {
//            System.out.println("Lấy dữ liệu từ Redis!");
//            return cachedOrder;
//        }
//
//        // 2. Nếu không có trong Redis, tìm trong Database
//        System.out.println("Lấy dữ liệu từ Database!");
//        Order order = orderRepository.findByOrderCode(orderCode);
//
//        if (order != null) {
//            // Lưu ngược lại vào Redis để lần sau lấy nhanh hơn
//            redisTemplate.opsForValue().set(key, order, 10, TimeUnit.MINUTES);
//            return order;
//        }
//        return null;
//    }
}
