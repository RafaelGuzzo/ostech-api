package br.com.ostech.service.impl;

import br.com.ostech.controller.request.UserRequest;
import br.com.ostech.exception.UserNotFoundException;
import br.com.ostech.model.User;
import br.com.ostech.repository.UserRepository;
import br.com.ostech.repository.specification.UserSpecification;
import br.com.ostech.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public Page<User> findAll(String fisrtName, String email, Pageable pageable) {
        Specification<User> specification = UserSpecification.filterBy(fisrtName, email);
        return userRepository.findAll(specification, pageable);
    }

    @Override
    public User save(UserRequest request) {
        String cryptopass = passwordEncoder.encode(request.getPassword());
        if (request.getId() != null) {
            User userFounded = this.findByUser(request.getId());
            userFounded.update(request.convertToModel(cryptopass));
            return userRepository.save(userFounded);
        }
        User newUser = request.convertToModel(cryptopass);
        return userRepository.save(newUser);
    }

    @Override
    public User findByUserId(Integer userId) {
        return findByUser(userId);
    }

    @Override
    public void delete(Integer userId) {
        User user = findByUser(userId);
        userRepository.delete(user);
    }


    public User findByUser(Integer userId){
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }
}
