package br.com.ostech.service.impl;

import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.exception.OrderNotFoundException;
import br.com.ostech.model.Order;
import br.com.ostech.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    OrderServiceImpl orderService;

    @MockBean
    OrderRepository orderRepository;

/*
    @Test
    public void findAllWhenIdAndStatusProvided() {
        Long orderId = 1L;
        OrderStatus orderStatus = OrderStatus.OPEN;
        Pageable pageable = Mockito.mock(Pageable.class);

        when(orderRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(Mockito.mock(Page.class));

        Page<Order> result = orderService.findAll(orderId, orderStatus, pageable);

        verify(orderRepository).findAll(any(Specification.class), any(Pageable.class));

        assertEquals(0, result.getSize());
    }

    @Test
    public void findAllWhenIdAndStatusNotProvided() {
        Pageable pageable = Mockito.mock(Pageable.class);

        when(orderRepository.findAll((Specification<Order>) isNull(), Mockito.eq(pageable))).thenReturn(Mockito.mock(Page.class));

        Page<Order> result = orderService.findAll(null, null, pageable);

        verify(orderRepository).findAll((Specification<Order>) isNull(), Mockito.eq(pageable));

        assertEquals(0, result.getSize());
    }

    @Test
    public void findAllWhenIdAndStatusNotProvidedWithError() {
        Pageable pageable = Mockito.mock(Pageable.class);

        when(orderRepository.findAll(any(Pageable.class))).thenThrow(new RuntimeException("Error fetching orders"));

        assertThrows(RuntimeException.class, () -> orderService.findAll(null, null, pageable));

        verify(orderRepository).findAll(any(Pageable.class));
    }

    @Test
    public void findAllWhenValidId() {
        Long orderId = 1L;
        OrderStatus orderStatus = null;
        Pageable pageable = Mockito.mock(Pageable.class);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(Mockito.mock(Order.class)));

        Page<Order> result = orderService.findAll(orderId, orderStatus, pageable);

        verify(orderRepository, never()).findAll(any(Specification.class), any(Pageable.class));

        assertEquals(1, result.getSize());
    }

    @Test
    public void findAllWhenIdNotFound() {
        Long orderId = 1L;
        OrderStatus orderStatus = null;
        Pageable pageable = Mockito.mock(Pageable.class);

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.findAll(orderId, orderStatus, pageable));

        verify(orderRepository, never()).findAll(any(Specification.class), any(Pageable.class));
    }

    @Test
    public void findAllWhenValidStatus() {
        Long orderId = null;
        OrderStatus orderStatus = OrderStatus.OPEN;
        Pageable pageable = Mockito.mock(Pageable.class);

        when(orderRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(Mockito.mock(Page.class));

        Page<Order> result = orderService.findAll(orderId, orderStatus, pageable);

        verify(orderRepository).findAll(any(Specification.class), any(Pageable.class));

        assertEquals(0, result.getSize());
    }

    @Test
    public void findAllWhenStatusNotFound() {

        Long orderId = null;
        OrderStatus orderStatus = OrderStatus.OPEN;
        Pageable pageable = Mockito.mock(Pageable.class);

        when(orderRepository.findAll(any(Specification.class), any(Pageable.class))).thenReturn(new PageImpl<>(Collections.emptyList()));

        assertThrows(OrderNotFoundException.class, () -> orderService.findAll(orderId, orderStatus, pageable));

        verify(orderRepository).findAll(any(Specification.class), any(Pageable.class));
    }
*/
    @Test
    public void testDelete() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(Mockito.mock(Order.class)));

        orderService.delete(orderId);

        verify(orderRepository).findById(orderId);

        verify(orderRepository).delete(Mockito.any(Order.class));
    }

    @Test
    public void testDeleteNonexistentOrder() {
        Long orderId = 1L;

        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> orderService.delete(orderId));

        verify(orderRepository).findById(orderId);

        verify(orderRepository, Mockito.never()).delete(Mockito.any(Order.class));

    }

}
