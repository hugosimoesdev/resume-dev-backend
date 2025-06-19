package dev.resume.backend.controller;

import dev.resume.backend.domain.dto.AuthUserDTO;
import dev.resume.backend.domain.entity.UserEntity;
import dev.resume.backend.service.AuthUserUseCase;
import dev.resume.backend.service.CreateUserUseCase;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUserUseCase authUserUseCase;
    private final CreateUserUseCase createUserUseCase;

    public AuthController(AuthUserUseCase authUserUseCase, CreateUserUseCase createUserUseCase)  {
        this.authUserUseCase = authUserUseCase;
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping("/signin")
    public ResponseEntity<Object> create(@RequestBody AuthUserDTO authUserDTO) {
        try {
            var result = authUserUseCase.execute(authUserDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> create(@Valid @RequestBody UserEntity userEntity){
        try {
            var result = this.createUserUseCase.execute(userEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
