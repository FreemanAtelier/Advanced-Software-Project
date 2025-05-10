package com.freemanatelier.freemanateliercustomstore.repository;

import com.freemanatelier.freemanateliercustomstore.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {}
