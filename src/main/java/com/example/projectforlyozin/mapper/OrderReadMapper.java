package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.OrderProductReadDto;
import com.example.projectforlyozin.dto.OrderReadDto;
import com.example.projectforlyozin.dto.ProductQuantity;
import com.example.projectforlyozin.dto.ProductReadDto;
import com.example.projectforlyozin.entity.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderReadMapper implements Mapper<Order, OrderReadDto> {

    private final ProductReadMapper productReadMapper;

    @Override
    public OrderReadDto map(Order object) {
        List<ProductQuantity> products = object.getOrderProducts().stream()
                .map(orderProduct -> {
                    return new ProductQuantity(
                      productReadMapper.map(orderProduct.getProduct()),
                      orderProduct.getQuantity()
                    );
                })
                .collect(Collectors.toList());
        return new OrderReadDto(
                object.getOrderId(),
                object.getDateGet(),
                products);
    }
}
