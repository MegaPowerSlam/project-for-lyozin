package com.example.projectforlyozin.controller;

import com.example.projectforlyozin.dto.OrderCreateEditDto;
import com.example.projectforlyozin.dto.OrderReadDto;
import com.example.projectforlyozin.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/order")
@RestController
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderReadDto> create(@RequestBody OrderCreateEditDto dto){
        return new ResponseEntity<>(orderService.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderReadDto> read(@RequestBody Integer id){
        return new ResponseEntity<>(orderService.read(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderReadDto> update(@RequestBody OrderCreateEditDto dto, Integer id){
        return new ResponseEntity<>(orderService.update(dto, id), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public HttpStatus delete(@PathVariable Integer id){
        orderService.delete(id);
        return HttpStatus.I_AM_A_TEAPOT;
    }
}
