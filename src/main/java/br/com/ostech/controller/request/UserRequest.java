package br.com.ostech.controller.request;

import br.com.ostech.enuns.Role;
import br.com.ostech.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

    private Integer id;
    private String firstName;
    private String lastName;

    @ValidEmail
    private String email;
    private String password;

    public User convertToModel(String cryptopass) {
        return new User.UserBuilder()
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .password(cryptopass)
                .role(Role.USER)
                .build();
    }

}
