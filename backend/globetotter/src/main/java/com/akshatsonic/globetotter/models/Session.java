package com.akshatsonic.globetotter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sessions")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Session extends BaseEntity {
    String sessionToken;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    User user;

    LocalDateTime expiryTime;

    Boolean isActive;
}
