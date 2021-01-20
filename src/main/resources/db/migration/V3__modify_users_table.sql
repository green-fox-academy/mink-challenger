ALTER TABLE users
    ADD password VARCHAR(60);

update users
set password = 'pass'
WHERE id = 1;

update users
set password = 'pass'
WHERE id = 2;

ALTER TABLE users
    ADD email VARCHAR(320);

update users
set email = 'bela@email.hu'
WHERE id = 1;

update users
set email = 'gazsi@email.hu'
WHERE id = 2;





