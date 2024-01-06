package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.DeliveryListReadDto;
import com.example.projectforlyozin.dto.OrderReadDto;
import com.example.projectforlyozin.entity.DeliveryList;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeliveryListReadMapper implements Mapper<DeliveryList, DeliveryListReadDto>{

    private final OrderReadMapper orderReadMapper;

    @Override
    public DeliveryListReadDto map(DeliveryList object) {
        OrderReadDto orderReadDto = orderReadMapper.map(object.getOrder());
        return new DeliveryListReadDto(
                object.getDeliveryId(),
                object.getDateArrived(),
                object.getPaymentMethod(),
                orderReadDto);
    }
}
