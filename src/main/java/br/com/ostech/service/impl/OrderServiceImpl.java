package br.com.ostech.service.impl;

import br.com.ostech.controller.request.OrderRequest;
import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.exception.OrderNotFoundException;
import br.com.ostech.model.Client;
import br.com.ostech.model.Order;
import br.com.ostech.repository.OrderRepository;
import br.com.ostech.repository.specification.OrderSpecification;
import br.com.ostech.service.ClientService;
import br.com.ostech.service.OrderExportService;
import br.com.ostech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;


    private final ClientService clientService;
    private final OrderExportService orderExportService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ClientServiceImpl clientService, OrderExportService orderExportService) {
        this.orderRepository = orderRepository;
        this.clientService = clientService;
        this.orderExportService = orderExportService;
    }

    @Override
    public Page<Order> findAll(Long id, OrderStatus status, Pageable pageable) {
        Specification<Order> specification = OrderSpecification.filterBy(id, status);
        return orderRepository.findAll(specification, pageable);
    }

    @Override
    public Page<Order> listAllOrdersBy(String clientId, Pageable pageable) {
        return orderRepository.findAllByClientId(UUID.fromString(clientId), pageable);
    }

    @Override
    public Order getOrderBy(Long orderId) {
        return findByOrder(orderId);
    }

    @Override
    public Order changeStatus(Long orderId) {
        Order order = findByOrder(orderId);
        order.changeNextStatus();
        return orderRepository.save(order);
    }

    @Override
    public Order save(OrderRequest orderRequest) {
        UUID clientId = UUID.fromString(orderRequest.getClientId());
        Client verifiedClient = clientService.findByClientId(clientId);
        if (orderRequest.getId() != null) {
            Order orderFounded = this.findByOrder(orderRequest.getId());
            orderFounded.update(orderRequest.convertToModel(verifiedClient));
            return orderRepository.save(orderFounded);
        }
        Order newOrder = orderRequest.convertToModel(verifiedClient);
        return orderRepository.save(newOrder);
    }

    @Override
    public void delete(Long orderId) {
        Order order = findByOrder(orderId);

        orderRepository.delete(order);
    }

    public byte[] getReportOrder(Long orderId) {
        Order order = findByOrder(orderId);
        try {
            return orderExportService.exportToPDF(order);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    private Order findByOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        if (order.isEmpty()) {
            throw new OrderNotFoundException();
        }

        return order.get();
    }
}
