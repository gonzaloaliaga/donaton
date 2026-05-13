CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

INSERT INTO users (username, password, role) VALUES ('admin', '1111', 'ADMIN');
INSERT INTO users (username, password, role) VALUES ('logistica', '2222', 'LOGISTIC');
INSERT INTO users (username, password, role) VALUES ('voluntario', '3333', 'VOLUNTEER');
INSERT INTO users (username, password, role) VALUES ('donante', '4444', 'DONOR');