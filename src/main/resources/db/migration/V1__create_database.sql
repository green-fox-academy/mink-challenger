CREATE TABLE users
(
    id          BIGINT AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    challenge_id     BIGINT,
    PRIMARY KEY (id)
    #FOREIGN KEY (challenge_id) REFERENCES challenges (id)
);

INSERT INTO users(name, challenge_id)
VALUES ('Bela', 2),
       ('Gazsi', 1);

CREATE TABLE commitments
(
    id          BIGINT AUTO_INCREMENT,
    description        VARCHAR(120) NOT NULL,
    date    DATE,
    done    boolean,
    user_id     BIGINT NOT NULL,
    challenge_id     BIGINT NOT NULL,
    PRIMARY KEY (id)
);
INSERT INTO commitments(description, date, done, user_id, challenge_id)
VALUES ('One Hour run', '2021-01-19', false, 1, 1),
        ('One Hour run', '2021-01-01', true, 2, 1);

CREATE TABLE challenges
(
    id          BIGINT AUTO_INCREMENT,
    name        VARCHAR(50) NOT NULL UNIQUE,
    start_date  DATE,
    end_date    DATE,
    commitment_id     BIGINT,
    user_id     BIGINT,
    PRIMARY KEY (id),
    FOREIGN KEY (commitment_id) REFERENCES commitments (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
);

INSERT INTO challenges (name, start_date, end_date, commitment_id, user_id)
VALUES ('running challenge', '2021-01-01', '2021-01-31', 1, 2);



