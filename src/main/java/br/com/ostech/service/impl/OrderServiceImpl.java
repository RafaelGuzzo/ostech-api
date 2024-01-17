package br.com.ostech.service.impl;

import br.com.ostech.controller.request.OrderRequest;
import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.exception.OrderNotFoundException;
import br.com.ostech.model.Client;
import br.com.ostech.model.Order;
import br.com.ostech.repository.OrderRepository;
import br.com.ostech.repository.specification.OrderSpecification;
import br.com.ostech.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientServiceImpl clientService;

    @Override
    public Page<Order> findAll(Long id, OrderStatus status, Pageable pageable) {
        Specification<Order> specification = OrderSpecification.filterBy(id, status);
        return orderRepository.findAll(specification, pageable);
    }

    @Override
    public Order save(OrderRequest orderRequest) {
        Client verifiedClient = clientService.findByClientId(orderRequest.getClient().getId());
        if(orderRequest.getId() != null){
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

    private Order findByOrder(Long orderId) {
        Optional<Order> order = orderRepository.findById(orderId);

        if(order.isEmpty()){
            throw new OrderNotFoundException();
        }

        return order.get();
    }
}
