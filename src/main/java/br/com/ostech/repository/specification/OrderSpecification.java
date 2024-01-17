package br.com.ostech.repository.specification;

import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.model.Order;
import org.springframework.data.jpa.domain.Specification;

import static br.com.ostech.utils.Validation.*;

public class OrderSpecification {

    private OrderSpecification() {
        throw new IllegalStateException("Specification Static class");
    }

    public static Specification<Order> filterBy(Long id, OrderStatus status){
        return Specification.where(equalsId(id))
                .and(equalsStatus(status));
    }

    private static Specification<Order> equalsId(Long id) {
        return (root, query, criteriaBuilder) -> idNull(id) ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("id"), id);
    }

    private static Specification<Order> equalsStatus(OrderStatus status) {
        return (root, query, criteriaBuilder) -> statusNull(status) ? criteriaBuilder.conjunction() : criteriaBuilder.equal(root.get("status"), status);
    }
}
