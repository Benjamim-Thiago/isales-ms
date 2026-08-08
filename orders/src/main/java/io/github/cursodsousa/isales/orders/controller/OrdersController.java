package io.github.cursodsousa.isales.orders.controller;

import io.github.cursodsousa.isales.orders.controller.dto.AddNewPaymentDTO;
import io.github.cursodsousa.isales.orders.controller.dto.NewOrderDTO;
import io.github.cursodsousa.isales.orders.controller.mapers.OrderMapper;
import io.github.cursodsousa.isales.orders.model.ErrorResponse;
import io.github.cursodsousa.isales.orders.model.exception.ItemNotFoundException;
import io.github.cursodsousa.isales.orders.model.exception.ValidatorException;
import io.github.cursodsousa.isales.orders.publish.OrderDetailMapper;
import io.github.cursodsousa.isales.orders.publish.representation.OrderDetailRepresentation;
import io.github.cursodsousa.isales.orders.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrdersController {
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final OrderDetailMapper orderDetailMapper;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody NewOrderDTO dto) {
        try {
            var order = orderMapper.map(dto);
            orderService.save(order);
            return ResponseEntity.ok(order.getId());
        } catch (ValidatorException e) {
            ErrorResponse errorResponse = new ErrorResponse("Erro validação", e.getField(), e.getMessage());
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }


    @PostMapping("new-payment")
    public ResponseEntity<Object> addNewPayment(
            @RequestBody AddNewPaymentDTO body
    ) {
       try {
           orderService.addNewPaymentType(body.code(), body.cardData(), body.paymentType());
           return ResponseEntity.noContent().build();
       } catch (ItemNotFoundException e) {
           ErrorResponse errorResponse = new ErrorResponse("Item não encontrado", "code", e.getMessage());
           return ResponseEntity.badRequest().body(errorResponse);
       }
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDetailRepresentation> detailRepresentation(@PathVariable("id") Long id) {
        return orderService.findFullOrderById(id)
                .map(orderDetailMapper::map)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
