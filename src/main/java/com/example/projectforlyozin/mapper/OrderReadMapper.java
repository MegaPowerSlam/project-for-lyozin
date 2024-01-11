package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.*;
import com.example.projectforlyozin.entity.DeliveryList;
import com.example.projectforlyozin.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {

    private final ProductReadMapper productReadMapper;
    private final DeliveryListReadMapper deliveryListReadMapper;

    @Override
    public OrderReadDto map(Order object) {
//        List<ProductQuantity> products = null;
        List<ProductQuantity> products = object.getOrderProducts().stream()
                .map(orderProduct -> {
                    return new ProductQuantity(
                      productReadMapper.map(orderProduct.getProduct()),
                      orderProduct.getQuantity()
                    );
                })
                .collect(Collectors.toList());
        DeliveryListReadDto deliveryListReadDto;
        if (object.getDeliveryList() != null){
            deliveryListReadDto = deliveryListReadMapper.map(object.getDeliveryList());
        } else {
            deliveryListReadDto = null;
        }
        return new OrderReadDto(
                object.getOrderId(),
                object.getDateGet(),
//                Optional.ofNullable(object.getDeliveryList()).map(entity -> {
//                    return deliveryListReadMapper.map(entity);
//                }).orElse(null),
                deliveryListReadDto,
                products);
    }
}
