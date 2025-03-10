package com.akshatsonic.globetotter.models;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table("sessions")
@Data
@Builder
public class Session extends BaseEntity {
    String sessionToken;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "user_id")
    User user;

    Long expiryTime;

    Boolean isActive;
}
