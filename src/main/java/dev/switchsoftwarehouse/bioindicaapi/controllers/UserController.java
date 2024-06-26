package dev.switchsoftwarehouse.bioindicaapi.controllers;
import dev.switchsoftwarehouse.bioindicaapi.models.User;
import dev.switchsoftwarehouse.bioindicaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequestMapping("bioindica/api/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @CrossOrigin
    @PostMapping("/register-user")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> registerNewUser(@RequestBody User newUser) {
        if (userRepository.existsByEmail(newUser.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email duplicado");
        }

        userRepository.save(newUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CrossOrigin
    @PutMapping("/update-user/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable Long id, @RequestBody User user) {
        if (!userRepository.existsById(id)) throw new RuntimeException("User not found");
        user.setId(id);
        userRepository.save(user);
    }

    @CrossOrigin
    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUsers(@RequestBody List<User> users) {
        users.forEach(user -> userRepository.delete(user));
    }
}

