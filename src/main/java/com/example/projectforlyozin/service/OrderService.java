package com.example.projectforlyozin.service;

import com.example.projectforlyozin.dto.OrderCreateEditDto;
import com.example.projectforlyozin.dto.OrderReadDto;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.mapper.OrderCreateEditMapper;
import com.example.projectforlyozin.mapper.OrderReadMapper;
import com.example.projectforlyozin.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderReadMapper orderReadMapper;
    private final OrderCreateEditMapper orderCreateEditMapper;

    public OrderReadDto create(OrderCreateEditDto dto){
        return orderReadMapper.map(orderRepository.save(orderCreateEditMapper.map(dto)));
    }

    public OrderReadDto read(Integer id){
        return orderReadMapper.map(orderRepository.findById(id).get());
    }

    public OrderReadDto update(OrderCreateEditDto dto, Integer id){
        Order order = orderRepository.findById(id).get();
        return orderReadMapper.map(orderCreateEditMapper.map(dto, order));
    }

    public void delete(Integer id){
        orderRepository.delete(orderRepository.findById(id).get());
    }

}
