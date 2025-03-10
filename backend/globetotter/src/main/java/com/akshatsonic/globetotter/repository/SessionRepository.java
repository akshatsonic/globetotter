package com.akshatsonic.globetotter.repository;

import com.akshatsonic.globetotter.models.Session;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SessionRepository extends CrudRepository<Session, Long> {
    Optional<Session> findBySessionTokenAndUserId(String sessionToken, Long userId);
    Optional<Session> findFirstByUserIdOrderByIdDesc(Long userId);
}
