package com.akshatsonic.globetotter.models;

import com.akshatsonic.globetotter.enums.UserRole;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Table(name = "users")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User extends BaseEntity{
    String username;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    UserRole role = UserRole.PLAYER;

    @OneToOne(mappedBy = "user")
    AuthEntity authEntity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    List<Session> session;
}
