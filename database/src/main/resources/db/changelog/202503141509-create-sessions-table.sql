--liquibase formatted sql
--changeset author:akshat id:202503141509
CREATE TABLE sessions (
    id SERIAL PRIMARY KEY,
    session_token VARCHAR(36) NOT NULL,
    user_id BIGINT NOT NULL,
    is_active BOOLEAN NOT NULL,
    expiry_time TIMESTAMP NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);