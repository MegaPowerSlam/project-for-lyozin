package com.example.projectforlyozin.mapper;

import com.example.projectforlyozin.dto.OrderCreateEditDto;
import com.example.projectforlyozin.entity.Order;
import com.example.projectforlyozin.entity.OrderProduct;
import com.example.projectforlyozin.entity.Product;
import com.example.projectforlyozin.repository.OrderProductRepository;
import com.example.projectforlyozin.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class OrderCreateEditMapper implements Mapper<OrderCreateEditDto, Order>{

    private final OrderProductRepository orderProductRepository;
    private final ProductRepository productRepository;

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
//        toObject.setDateGet(object.getDateGet());
//        List<Product> products = getProducts(object.getProductIds());
//        for (products) {
//            OrderProduct op = new OrderProduct();
//            op.setProduct(products[0]);
//            op.setOrder(order);
//            order.getOrderProducts().add(op);
//        }
//
//        repository.save(order);
    }

    private List<Product> getProducts(List<Integer> productsIds){
        List<Product> products = new ArrayList<>();
        for(int i = 0; i < productsIds.size(); i++){
            Integer id = productsIds.get(i);
            Product product = Optional.ofNullable(id)
                    .flatMap(productRepository::findById)
                    .orElse(null);
            products.add(product);
        }
        return products;
    }

}
