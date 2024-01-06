package com.example.projectforlyozin.repository;

import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.entity.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer>{
//    List<OrderProduct> findOrderProductsBysByIds(List<Integer> ids);
}
