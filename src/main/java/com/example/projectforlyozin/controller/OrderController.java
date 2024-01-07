package com.example.projectforlyozin.controller;

import com.example.projectforlyozin.dto.*;
import com.example.projectforlyozin.entity.Product;
import com.example.projectforlyozin.service.DeliveryListService;
import com.example.projectforlyozin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/orders")
@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final DeliveryListService deliveryListService;

    @PostMapping("/{id}")
    public ResponseEntity<OrderReadDto> addProductInOrder(@PathVariable("id") Integer orderId,
                                                          @RequestBody ProductQuantityCreateEditDto dto){

        OrderReadDto orderReadDto = orderService.addProductInOrder(orderId, dto);

        return ResponseEntity
                .ok()
                .body(orderReadDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderReadDto> changeCountOfProduct(@PathVariable("id") Integer orderId,
                                                             @RequestBody ProductQuantityCreateEditDto dto){

        OrderReadDto orderReadDto = orderService.changeCountOfProduct(orderId, dto);

        return ResponseEntity
                .ok()
                .body(orderReadDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductFromOrder(@PathVariable("id") Integer orderId,
                                                    @RequestBody Integer productId){
        orderService.deleteProductFromOrder(orderId, productId);
        return ResponseEntity
                .ok()
                .build();
    }

    @PostMapping("/{id}/form-order")
    public ResponseEntity<OrderReadDto> addDeliveryList(@RequestBody DeliveryListCreateEditDto dto,
                                                             @PathVariable("id") Integer orderId){

        OrderReadDto orderReadDto = orderService.addDeliveryList(orderId, dto);

        return ResponseEntity
                .ok()
                .body(orderReadDto);
    }

//    @PostMapping
//    public ResponseEntity<OrderReadDto> create(@RequestBody OrderCreateEditDto dto){
//        return new ResponseEntity<>(orderService.create(dto), HttpStatus.CREATED);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<OrderReadDto> read(@RequestBody Integer id){
//        return new ResponseEntity<>(orderService.read(id), HttpStatus.OK);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<OrderReadDto> update(@RequestBody OrderCreateEditDto dto, Integer id){
//        return new ResponseEntity<>(orderService.update(dto, id), HttpStatus.ACCEPTED);
//    }
//

}
