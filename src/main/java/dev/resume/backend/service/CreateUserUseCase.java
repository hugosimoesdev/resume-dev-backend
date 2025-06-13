package dev.resume.backend.service;

import dev.resume.backend.domain.entity.UserEntity;
import dev.resume.backend.exceptions.UserAlreadyExistException;
import dev.resume.backend.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateUserUseCase {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity execute(UserEntity userEntity){
        var user = this.userRepository.findByEmail(userEntity.getEmail());

        if(user != null){
            throw new UserAlreadyExistException();
        }

        var password = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(password);

        return this.userRepository.save(userEntity);
    }
}
