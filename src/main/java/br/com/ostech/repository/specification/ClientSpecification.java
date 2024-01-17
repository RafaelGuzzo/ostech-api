package br.com.ostech.repository.specification;

import br.com.ostech.model.Client;
import org.springframework.data.jpa.domain.Specification;

import static br.com.ostech.utils.Utils.addLike;
import static br.com.ostech.utils.Utils.isNullOrBlank;


public class ClientSpecification {

    private ClientSpecification() {
        throw new IllegalStateException("Specification Static class");
    }

    public static Specification<Client> filterBy(String name, String documentNumber) {
        return Specification.where(likeName(name))
                .and(likedocumentNumber(documentNumber));
    }

    private static Specification<Client> likeName(String name) {
        return (root, query, criteriaBuilder) -> isNullOrBlank(name) ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("name"), addLike(name));
    }

    private static Specification<Client> likedocumentNumber(String documentNumber) {
        return (root, query, criteriaBuilder) -> isNullOrBlank(documentNumber) ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("documentNumber"), addLike(documentNumber));
    }

}
