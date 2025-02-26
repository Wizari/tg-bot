CREATE TABLE IF NOT EXISTS users (
                       id BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       telegram_id BIGINT NOT NULL
);
CREATE TABLE IF NOT EXISTS locations (
                           id BIGSERIAL PRIMARY KEY,
                           latitude DOUBLE PRECISION NOT NULL,
                           longitude DOUBLE PRECISION NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           user_id BIGINT NOT NULL,
                           FOREIGN KEY (user_id) REFERENCES users(id)
);
