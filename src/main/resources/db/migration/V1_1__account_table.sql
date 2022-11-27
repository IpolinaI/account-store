CREATE TABLE IF NOT EXISTS account
(
    id          UUID NOT NULL PRIMARY KEY,
    customer_id UUID NOT NULL,
    CONSTRAINT account_customer_fk FOREIGN KEY (customer_id) REFERENCES customer(id) ON DELETE CASCADE
);