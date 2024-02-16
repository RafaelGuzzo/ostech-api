package br.com.ostech.controller;

import br.com.ostech.controller.request.ClientRequest;
import br.com.ostech.controller.request.UserRequest;
import br.com.ostech.controller.response.AuthenticationResponse;
import br.com.ostech.controller.response.ClientResponse;
import br.com.ostech.controller.response.UserResponse;
import br.com.ostech.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<Page<UserResponse>> getAllUsers(@RequestParam(required = false) String fisrtName,
            @RequestParam(required = false) String email, Pageable pageable){
        Page<UserResponse> users = userService.findAll(fisrtName, email, pageable).map(UserResponse::new);
        return ResponseEntity.ok(users);
    }


    @PostMapping
    public ResponseEntity<UserResponse> addUpdateUser(@RequestBody UserRequest request) {
        UserResponse newUser = new UserResponse(userService.save(request));

        return ResponseEntity.ok(newUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getOneClient(@PathVariable Integer userId) {
        UserResponse userFound = new UserResponse(userService.findByUserId(userId));

        return ResponseEntity.ok(userFound);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> removeClient(@PathVariable Integer userId) {
        userService.delete(userId);

        return ResponseEntity.ok("user successfully deleted");
    }
}
