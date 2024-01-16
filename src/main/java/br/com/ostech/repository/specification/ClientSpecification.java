package br.com.ostech.repository.specification;

import br.com.ostech.model.Client;
import org.springframework.data.jpa.domain.Specification;

import static br.com.ostech.utils.Validation.isNullOrBlank;

public class ClientSpecification {
    private ClientSpecification() {
        throw new IllegalStateException("Specification Static class");
    }

    public static Specification<Client> filterBy(String name, String cpf) {
        return Specification
                .where(likeName(name))
                .and(likeCpf(cpf));
    }

    private static Specification<Client> likeName(String name) {
        return (root, query, criteriaBuilder) -> isNullOrBlank(name) ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    private static Specification<Client> likeCpf(String cpf) {
        return (root, query, criteriaBuilder) -> isNullOrBlank(cpf) ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("cpf"), "%" + cpf + "%");
    }
}
