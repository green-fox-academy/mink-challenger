ALTER TABLE users
    ADD password VARCHAR(60);

ALTER TABLE users
    ADD user_type INTEGER;

update users
set password = '$2y$12$jnlHdPuXJ04ExbPEWxDMjuURSrzurWnWCsbf/RSxxWxLEuYJzuttW'
WHERE id = 1;

update users
set password = '$2y$12$jnlHdPuXJ04ExbPEWxDMjuURSrzurWnWCsbf/RSxxWxLEuYJzuttW'
WHERE id = 2;

ALTER TABLE users
    ADD email VARCHAR(320);

update users
set email = 'bela@email.hu'
WHERE id = 1;

update users
set email = 'gazsi@email.hu'
WHERE id = 2;





