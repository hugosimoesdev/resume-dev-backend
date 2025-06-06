package dev.resume.backend.usecases;

import dev.resume.backend.entities.UserEntity;
import dev.resume.backend.exceptions.UserAlreadyExistException;
import dev.resume.backend.repositories.UserRepository;
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
