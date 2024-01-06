package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.OrderProductReadDto;
import com.example.projectforlyozin.entity.OrderProduct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderProductReadMapper implements Mapper<OrderProduct, OrderProductReadDto> {

    @Override
    public OrderProductReadDto map(OrderProduct object) {
        return new OrderProductReadDto(
                object.getOrderProductId(),
                object.getQuantity());
    }
}
