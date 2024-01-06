package com.example.projectforlyozin.repository;

import com.example.projectforlyozin.entity.DeliveryList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryListRepository extends JpaRepository<DeliveryList, Integer> {
}
