package com.example.projectforlyozin.repository;

import com.example.projectforlyozin.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
//    List<Order> findOrdersByIds(List<Integer> ids);
}
