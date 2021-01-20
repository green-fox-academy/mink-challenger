ALTER TABLE challenges
    ADD minimum_commitment BIGINT NOT NULL;
update challenges
set minimum_commitment = '1'
WHERE id = 1;