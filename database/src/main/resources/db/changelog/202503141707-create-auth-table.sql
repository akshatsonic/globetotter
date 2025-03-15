--liquibase formatted sql
--changeset author:akshat id:202503141707
CREATE TABLE auth (
    id SERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_active BOOLEAN NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);