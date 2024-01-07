package com.example.projectforlyozin.service;

import com.example.projectforlyozin.dto.*;
import com.example.projectforlyozin.entity.*;
import com.example.projectforlyozin.mapper.DeliveryListCreateEditMapper;
import com.example.projectforlyozin.mapper.OrderCreateEditMapper;
import com.example.projectforlyozin.mapper.OrderReadMapper;
import com.example.projectforlyozin.mapper.ProductCreateEditMapper;
import com.example.projectforlyozin.repository.DeliveryListRepository;
import com.example.projectforlyozin.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final DeliveryListRepository deliveryListRepository;
    private final OrderReadMapper orderReadMapper;
    private final OrderCreateEditMapper orderCreateEditMapper;
    private final DeliveryListCreateEditMapper deliveryListCreateEditMapper;
    private final ProductCreateEditMapper productCreateEditMapper;

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

//    @Transactional
//    public OrderReadDto addProductInOrder(Integer orderId, ProductCreateEditDto dto) {
//        Optional<Order> order = orderRepository.findById(orderId);
//        OrderProduct orderProduct = new OrderProduct();
//        orderProduct.setOrder(order.get());
//        orderProduct.setProduct(productCreateEditMapper.map(dto));
//        orderProduct.setQuantity(1);
//        return order.map(entity -> {
//                    List<OrderProduct> orderProductList = entity.getOrderProducts();
//                    orderProductList.add();
//                    for (OrderProduct op: orderProductList) {
//                        if (op.getProduct().getProductId() == dto.getProductId()) {
//                            op.setQuantity(dto.getQuantity());
//                        }
//                    }
//                    return orderReadMapper.map(entity);
//                })
//                .get();
//    }

    @Transactional
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

//    @Transactional
//    public OrderReadDto addDeliveryList(Integer orderId, DeliveryListCreateEditDto dto) {
//        Optional<Order> order = orderRepository.findById(orderId);
//        order.map(entity -> {
//            entity.setDeliveryList(deliveryListCreateEditMapper.map(dto));
//            return entity;
//        });
//        return orderReadMapper.map(orderRepository.save(order.get()));
//    }
    @Transactional
    public OrderReadDto addDeliveryList(Integer orderId, DeliveryListCreateEditDto dto) {
        Optional<Order> order = orderRepository.findById(orderId);
        DeliveryList deliveryList= deliveryListCreateEditMapper.map(dto);
        deliveryList.setOrder(order.get());
        deliveryListRepository.save(deliveryList);
        order.map(entity -> {
            entity.setDeliveryList(deliveryList);
            return entity;
        });
        return orderReadMapper.map(orderRepository.save(order.get()));
    }
}
