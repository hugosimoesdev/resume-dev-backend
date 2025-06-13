package dev.resume.backend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import dev.resume.backend.domain.dto.AuthUserDTO;
import dev.resume.backend.exceptions.AuthenticationException;
import dev.resume.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

import java.time.Duration;
import java.time.Instant;

@Service
public class AuthUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${security.token.secret}")
    private String secretKey;

    public AuthUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String execute(AuthUserDTO authUserDTO) {
        var user = this.userRepository.findByEmail(authUserDTO.getEmail());

        if(user == null) {
            throw new AuthenticationException();
        }

        var passwordMatches = this.passwordEncoder.matches(authUserDTO.getPassword(), user.getPassword());

        if(!passwordMatches) {
            throw new AuthenticationException();
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);

        var token = JWT.create()
                .withIssuer("resume_dev")
                .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
                .withSubject(user.getId().toString())
                .sign(algorithm);

        return token;
    }
}
