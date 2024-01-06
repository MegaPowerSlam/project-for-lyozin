package com.example.projectforlyozin.service;

import com.example.projectforlyozin.dto.DeliveryListCreateEditDto;
import com.example.projectforlyozin.dto.OrderCreateEditDto;
import com.example.projectforlyozin.dto.OrderReadDto;
import com.example.projectforlyozin.dto.ProductQuantityCreateEditDto;
import com.example.projectforlyozin.entity.Customer;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.entity.OrderProduct;
import com.example.projectforlyozin.mapper.DeliveryListCreateEditMapper;
import com.example.projectforlyozin.mapper.OrderCreateEditMapper;
import com.example.projectforlyozin.mapper.OrderReadMapper;
import com.example.projectforlyozin.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderReadMapper orderReadMapper;
    private final OrderCreateEditMapper orderCreateEditMapper;
    private final DeliveryListCreateEditMapper deliveryListCreateEditMapper;

    public OrderReadDto create(OrderCreateEditDto dto){
        return orderReadMapper.map(orderRepository.save(orderCreateEditMapper.map(dto)));
    }

//    public OrderReadDto read(Integer id){
//        return orderReadMapper.map(orderRepository.findById(id).get());
//    }
//
//    public OrderReadDto update(OrderCreateEditDto dto, Integer id){
//        Order order = orderRepository.findById(id).get();
//        return orderReadMapper.map(orderCreateEditMapper.map(dto, order));
//    }

    public void delete(Integer id){
        orderRepository.delete(orderRepository.findById(id).get());
    }

    public OrderReadDto changeCountOfProduct(Integer orderId, ProductQuantityCreateEditDto dto) {
        Optional<Order> order = orderRepository.findById(orderId);
        return order.map(entity -> {
            List<OrderProduct> orderProductList = entity.getOrderProducts();
            for (OrderProduct op: orderProductList) {
                if (op.getProduct().getProductId() == dto.getProductId()) {
                    op.setQuantity(dto.getQuantity());
                }
            }
            return orderReadMapper.map(entity);
        })
        .get();
    }

    public OrderReadDto addDeliveryList(Integer orderId, DeliveryListCreateEditDto dto) {
        Optional<Order> order = orderRepository.findById(orderId);
        order.map(entity -> {
                    entity.setDeliveryList(deliveryListCreateEditMapper.map(dto));
                    return entity;
                });
        return orderReadMapper.map(orderRepository.save(order.get()));
    }
}
