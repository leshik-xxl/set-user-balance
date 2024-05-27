CREATE TABLE user_balance
(
    id      INTEGER NOT NULL,
    name    VARCHAR(255),
    balance INTEGER NOT NULL,
    CONSTRAINT pk_user_balance PRIMARY KEY (id)
);