package br.com.ostech.repository;

import br.com.ostech.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderServiceRepository extends JpaRepository<Order, Long> {
}
