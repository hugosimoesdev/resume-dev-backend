package dev.resume.backend.controller;

import dev.resume.backend.domain.dto.AuthUserDTO;
import dev.resume.backend.service.AuthUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthUserUseCase authUserUseCase;


    public AuthController(AuthUserUseCase authUserUseCase) {
        this.authUserUseCase = authUserUseCase;
    }

    @PostMapping
    public ResponseEntity<Object> create(@RequestBody AuthUserDTO authUserDTO) {
        try {
            var result = authUserUseCase.execute(authUserDTO);
            return ResponseEntity.ok(result);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
