CREATE DATABASE IF NOT EXISTS dmz-metadata;

\c dmz-metadata;

CREATE TABLE IF NOT EXISTS Document
(
    id   BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    size INTEGER
);
