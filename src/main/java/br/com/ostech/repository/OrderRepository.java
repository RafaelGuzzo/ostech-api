package br.com.ostech.repository;

import br.com.ostech.model.Client;
import br.com.ostech.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, JpaSpecificationExecutor<Order> {
    Page<Order> findAll(Specification<Order> specification, Pageable pageable);

    Page<Order> findAllByClientId(UUID clientId, Pageable pageable);

    void deleteByClient(Client client);
}
