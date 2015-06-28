CREATE TABLE
    model_ref
    (
        id SERIAL,
        name CHARACTER VARYING(100) NOT NULL,
        mark_id bigint NOT NULL,
        PRIMARY KEY (id),
        CONSTRAINT fk_mark_ref FOREIGN KEY (mark_id) REFERENCES mark_ref(id)
    );