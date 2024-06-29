package dev.switchsoftwarehouse.bioindicaapi.controllers;

import dev.switchsoftwarehouse.bioindicaapi.models.User;
import dev.switchsoftwarehouse.bioindicaapi.models.request.AuthenticationRequest;
import dev.switchsoftwarehouse.bioindicaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RestController
@RequestMapping("bioindica/api/authentication")
public class AuthenticationController {
    @Autowired
    private UserRepository userRepository;

    @CrossOrigin
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authentication) {
        User user = (User) userRepository.findByEmail(authentication.getEmail());

        if (user == null || !user.getPassword().equals(authentication.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorretos");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuário autenticado com sucesso");
    }

    // @CrossOrigin
    // @PostMapping
    // @ResponseStatus(HttpStatus.CREATED)
    // public ResponseEntity<String> createUser(@RequestBody SignUp signUp) {
    //     if (userRepository.existsByEmail(signUp.getEmail())) {
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
    //     }
    //     if (!signUp.getPassword().equals(signUp.getConfirmPassword())) {
    //         return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Senhas não são iguais");
    //     }
    //     userRepository.save(signUp.toUser());
    //     return ResponseEntity.status(HttpStatus.CREATED).body("Usuário cadastrado com sucesso");
    // }
}
