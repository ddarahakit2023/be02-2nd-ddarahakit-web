package com.ddarahakit.web.order.repository;

import com.ddarahakit.web.order.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
