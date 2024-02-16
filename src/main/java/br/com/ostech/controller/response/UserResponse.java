package br.com.ostech.controller.response;

import br.com.ostech.model.User;

public record UserResponse(
        String firstName,
        String lastName,
        String email) {
    public UserResponse(User user) {
        this(
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
