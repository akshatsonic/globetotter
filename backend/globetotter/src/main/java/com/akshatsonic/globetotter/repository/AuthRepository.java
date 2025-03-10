package com.akshatsonic.globetotter.repository;

import com.akshatsonic.globetotter.models.AuthEntity;
import com.akshatsonic.globetotter.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends CrudRepository<AuthEntity, Long> {
    Optional<AuthEntity> findByUserAndPassword(User user, String password);
}
