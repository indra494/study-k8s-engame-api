CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    nickname VARCHAR(50) NOT NULL,
    total_score BIGINT NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE words (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    english VARCHAR(50) NOT NULL UNIQUE,
    korean_pronunciation VARCHAR(50) NOT NULL,
    korean_meaning VARCHAR(50) NOT NULL,
    category VARCHAR(30) NOT NULL,
    image_filename VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE game_results (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    score INT NOT NULL,
    total_questions INT NOT NULL,
    correct_count INT NOT NULL,
    played_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_game_results_user FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE INDEX idx_words_category ON words(category);
CREATE INDEX idx_game_results_user ON game_results(user_id);
CREATE INDEX idx_users_total_score ON users(total_score DESC);
