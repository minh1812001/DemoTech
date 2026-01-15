package com.example.demo.order.repository;

import com.example.demo.order.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>
{
    // Bạn có thể thêm các phương thức tìm kiếm tùy chỉnh tại đây
    Order findByOrderCode(String orderCode);
}
