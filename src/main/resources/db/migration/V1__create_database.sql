CREATE TABLE users
(
    id        BIGINT AUTO_INCREMENT,
    username  VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(60)  NOT NULL,
    email     VARCHAR(320) NOT NULL,
    user_type VARCHAR(50),
    PRIMARY KEY (id)
);

INSERT INTO users(username, password, email, user_type)
VALUES ('Bela', '$2y$12$jnlHdPuXJ04ExbPEWxDMjuURSrzurWnWCsbf/RSxxWxLEuYJzuttW', 'gazsi@email.hu', 'ADMIN'),
       ('Gazsi', '$2y$12$jnlHdPuXJ04ExbPEWxDMjuURSrzurWnWCsbf/RSxxWxLEuYJzuttW', 'bela@email.hu', 'USER');

CREATE TABLE challenges
(
    id                 BIGINT AUTO_INCREMENT,
    name               VARCHAR(50) NOT NULL UNIQUE,
    start_date         DATE,
    end_date           DATE,
    minimum_commitment BIGINT,
    PRIMARY KEY (id)
);

INSERT INTO challenges (name, start_date, end_date, minimum_commitment)
VALUES ('running challenge', '2021-01-01', '2021-01-31', 1);

CREATE TABLE commitments
(
    id           BIGINT AUTO_INCREMENT,
    description  VARCHAR(120) NOT NULL,
    date         DATE,
    done         boolean,
    user_id      BIGINT       NOT NULL,
    challenge_id BIGINT       NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (challenge_id) REFERENCES challenges (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);
INSERT INTO commitments(description, date, done, user_id, challenge_id)
VALUES ('One Hour run', '2021-01-19', false, 1, 1),
       ('One Hour run', '2021-01-01', true, 2, 1);





