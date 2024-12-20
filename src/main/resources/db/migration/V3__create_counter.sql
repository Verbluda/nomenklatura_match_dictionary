CREATE SEQUENCE IF NOT EXISTS counter_sequence INCREMENT 1;

CREATE TABLE IF NOT EXISTS counter (
                                       id int8 PRIMARY KEY DEFAULT nextval('counter_sequence'),
                                       file_name varchar,
                                       counter int8
);