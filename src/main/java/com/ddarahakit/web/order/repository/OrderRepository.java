package com.ddarahakit.web.order.repository;

import com.ddarahakit.web.order.model.Orders;
import com.ddarahakit.web.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {
    public List<Orders> findByUser(User user);
}
