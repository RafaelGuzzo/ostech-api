package br.com.ostech.repository.specification;

import br.com.ostech.model.User;
import org.springframework.data.jpa.domain.Specification;

import static br.com.ostech.utils.Utils.addLike;
import static br.com.ostech.utils.Utils.isNullOrBlank;

public class UserSpecification {

    private UserSpecification() {
        throw new IllegalStateException("Specification Static class");
    }

    public static Specification<User> filterBy(String fisrtName, String email) {
        return Specification.where(likeName(fisrtName))
                .and(likeEmail(email));
    }

    private static Specification<User> likeName(String fisrtName) {
        return (root, query, criteriaBuilder) -> isNullOrBlank(fisrtName) ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("firstName"), addLike(fisrtName));
    }

    private static Specification<User> likeEmail(String email) {
        return (root, query, criteriaBuilder) -> isNullOrBlank(email) ? criteriaBuilder.conjunction() : criteriaBuilder.like(root.get("email"), addLike(email));
    }
}
