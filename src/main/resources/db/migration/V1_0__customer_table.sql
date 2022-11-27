CREATE TABLE IF NOT EXISTS customer
(
    id      UUID        NOT NULL PRIMARY KEY,
    name    VARCHAR(30) NOT NULL,
    surname VARCHAR(30) NOT NULL
);