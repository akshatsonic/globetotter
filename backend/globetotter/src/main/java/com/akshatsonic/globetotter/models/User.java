package com.akshatsonic.globetotter.models;

import com.akshatsonic.globetotter.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Table("users")
@Entity
@Builder
public class User extends BaseEntity{
    String username;
    UserRole role;

    @OneToOne(mappedBy = "user")
    AuthEntity authEntity;

    @OneToMany(mappedBy = "user")
    Session session;
}
