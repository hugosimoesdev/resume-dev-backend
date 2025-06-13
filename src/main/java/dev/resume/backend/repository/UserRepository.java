package dev.resume.backend.repository;

import dev.resume.backend.domain.entity.UserEntity;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
    UserEntity findByEmail(@NotNull String email);
}
