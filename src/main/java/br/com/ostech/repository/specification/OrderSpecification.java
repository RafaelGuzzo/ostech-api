package br.com.ostech.repository.specification;

import br.com.ostech.enuns.OrderStatus;
import br.com.ostech.model.Order;
import org.springframework.data.jpa.domain.Specification;

import static br.com.ostech.utils.Utils.addLike;

public class OrderSpecification {

    private OrderSpecification() {
        throw new IllegalStateException("Specification Static class");
    }

    public static Specification<Order> filterBy(Long id, OrderStatus status) {
        return Specification.where(equalsId(id))
                .and(equalsStatus(status));
    }

    private static Specification<Order> equalsId(Long id) {
        return (root, query, criteriaBuilder) -> id == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("id"), addLike(id.toString()));
    }

    private static Specification<Order> equalsStatus(OrderStatus status) {
        return (root, query, criteriaBuilder) -> status == null ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("status"), addLike(status.toString()));
    }
}
