package br.com.ostech.service;

import br.com.ostech.controller.request.UserRequest;
import br.com.ostech.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    Page<User> findAll(String fisrtName, String email, Pageable pageable);

    User save(UserRequest request);

    User findByUserId(Integer userId);

    void delete(Integer userId);
}
