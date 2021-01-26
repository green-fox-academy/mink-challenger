ALTER TABLE users
    DROP COLUMN user_type;

CREATE TABLE roles
(
    role_id BIGINT AUTO_INCREMENT,
    name    VARCHAR(50),
    primary key (role_id)
);
INSERT INTO roles(name)
VALUES ('ADMIN'),
       ('USER');

CREATE TABLE users_roles
(
    id      BIGINT AUTO_INCREMENT,
    user_id BIGINT,
    role_id BIGINT,
    primary key (id),
    foreign key (user_id) REFERENCES users (id),
    foreign key (role_id) REFERENCES roles (role_id)
);

# ALTER TABLE users
# ADD COLUMN role_id BIGINT;
#
# UPDATE users
# SET users.role_id = 1
# WHERE id = 1;
#
# UPDATE users
# SET users.role_id = 2
# WHERE id = 2;
INSERT INTO users_roles(user_id, role_id)
VALUES (1, 1),
       (2, 2);