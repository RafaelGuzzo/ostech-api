package br.com.ostech.controller;

import br.com.ostech.controller.request.OrderRequest;
import br.com.ostech.controller.response.OrderResponse;
import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/order")
public class OrderController {

    OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAllOrders(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false)OrderStatus status,
            Pageable pageable){

        Page<OrderResponse> orders = orderService.findAll(id, status, pageable)
                .map(OrderResponse::new);

        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@Valid @RequestBody OrderRequest order){
        OrderResponse newOrder = new OrderResponse(orderService.save(order));

        return ResponseEntity.ok(newOrder);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> removeOrder(@RequestParam Long orderId){
        orderService.delete(orderId);

        return ResponseEntity.ok("order successfully deleted");
    }
}
