ALTER TABLE users
    ADD uuid VARCHAR(36);
update users
set uuid = 'a0909975-3fc1-4e4a-8842-1dfffbca7356'
WHERE username = 'Gazsi';
update users
set uuid = 'b770cd4d-179e-422e-9015-c0f49f5b4fba'
WHERE username = 'Bela';

ALTER TABLE users
    ADD email_verified BOOLEAN;
update users
set email_verified = true
WHERE username = 'Gazsi';
update users
set email_verified = true
WHERE username = 'Bela';