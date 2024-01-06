package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.OrderCreateEditDto;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.entity.OrderProduct;
import com.example.projectforlyozin.entity.Product;
import com.example.projectforlyozin.repository.OrderProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDto, Order>{

    private final OrderProductRepository orderProductRepository;

    @Override
    public Order map(OrderCreateEditDto fromObject, Order toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Order map(OrderCreateEditDto object) {
        Order order = new Order();
        copy(object, order);
        return order;
    }

    private void copy(OrderCreateEditDto object, Order toObject){
        toObject.setOrderId(object.getOrderId());
        toObject.setDateGet(object.getDateGet());
        toObject.setOrderProducts(getProducts(object.getOrderProductsIds(), toObject.getOrderProducts()));
    }

    private List<OrderProduct> getProducts(List<Integer> orderProductsIds, List<OrderProduct> orderProducts){
        for(int i = 0; i < orderProductsIds.size(); i++){
            Integer id = orderProductsIds.get(i);
            orderProducts.remove(i);
            OrderProduct orderProduct = Optional.ofNullable(id)
                    .flatMap(orderProductRepository::findById)
                    .orElse(null);
            orderProducts.add(i, orderProduct);
        }
        return orderProducts;
    }

}
