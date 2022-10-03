CREATE TABLE if not exists shortened_urls(
    id              VARCHAR PRIMARY KEY,
    original_url    VARCHAR NOT NULL,
    title           VARCHAR,
    created_at      TIMESTAMP DEFAULT now()
);