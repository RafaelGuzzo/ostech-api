package br.com.ostech.controller;

import br.com.ostech.controller.request.OrderRequest;
import br.com.ostech.controller.response.OrderResponse;
import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.model.Order;
import br.com.ostech.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/order")
public class OrderController {

    private final OrderService orderService;


    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAllOrders(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) OrderStatus status,
            @PageableDefault Pageable pageable
    ) {
        Page<OrderResponse> orders = orderService.findAll(id, status, pageable)
                .map(OrderResponse::new);

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{clientId}/client")
    public ResponseEntity<Page<OrderResponse>> listAllOrdersByClient(
            @PathVariable String clientId,
            @PageableDefault Pageable pageable
    ) {
        Page<OrderResponse> orderPaginated = orderService.listAllOrdersBy(clientId, pageable)
                .map(OrderResponse::new);
        return ResponseEntity.ok(orderPaginated);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> getOrderBy(@PathVariable Long orderId) {
        Order order = orderService.getOrderBy(orderId);
        return ResponseEntity.ok(new OrderResponse(order));
    }

    @PostMapping
    public ResponseEntity<OrderResponse> addOrder(@Valid @RequestBody OrderRequest order) {
        OrderResponse newOrder = new OrderResponse(orderService.save(order));

        return ResponseEntity.ok(newOrder);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderResponse> changeStatus(@PathVariable Long orderId) {
        OrderResponse orderResponse = new OrderResponse(orderService.changeStatus(orderId));
        return ResponseEntity.ok(orderResponse);
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<?> removeOrder(@PathVariable Long orderId) {
        orderService.delete(orderId);

        return ResponseEntity.ok("order successfully deleted");
    }
}
