CREATE TABLE
    provider_ref
    (
        id SERIAL,
        name CHARACTER VARYING(50) NOT NULL,
        site_url CHARACTER VARYING(100),
        PRIMARY KEY (id)
    );