CREATE TABLE IF NOT EXISTS match (
                                         id int8 PRIMARY KEY,
                                         row_name varchar,
                                         nomenklatura_name  varchar
);

CREATE SEQUENCE match_sequence START 15 INCREMENT 1;