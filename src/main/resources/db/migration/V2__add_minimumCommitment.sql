ALTER TABLE challenges
    ADD COLUMN minimum_commitment BIGINT NOT NULL;
update challenges
set minimum_commitment = '1'
WHERE id = 1;