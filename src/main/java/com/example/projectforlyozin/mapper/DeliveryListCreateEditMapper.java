package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.DeliveryListCreateEditDto;
import com.example.projectforlyozin.entity.DeliveryList;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DeliveryListCreateEditMapper implements Mapper<DeliveryListCreateEditDto, DeliveryList>{

    private final OrderRepository orderRepository;

    @Override
    public DeliveryList map(DeliveryListCreateEditDto fromObject, DeliveryList toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public DeliveryList map(DeliveryListCreateEditDto object) {
        DeliveryList deliveryList = new DeliveryList();
        copy(object, deliveryList);
        return deliveryList;
    }
    private void copy (DeliveryListCreateEditDto fromObject, DeliveryList toObject) {
        toObject.setDeliveryId(fromObject.getDeliveryId());
        toObject.setDateArrived(fromObject.getDateArrived());
        toObject.setPaymentMethod(fromObject.getPaymentMethod());
        toObject.setOrder(getOrder(fromObject.getOrderId()));
    }
    private Order getOrder(Integer orderId) {
        return Optional.ofNullable(orderId)
                .flatMap(orderRepository::findById)
                .orElse(null);
    }
}
