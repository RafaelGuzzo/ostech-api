package br.com.ostech.service;

import br.com.ostech.controller.request.OrderRequest;
import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    Page<Order> findAll(Long id, OrderStatus status, Pageable pageable);

    Page<Order> listAllOrdersBy(String clientId, Pageable pageable);

    Order getOrderBy(Long orderId);

    Order changeStatus(Long orderId);

    Order save(OrderRequest order);

    void delete(Long orderId);
}
