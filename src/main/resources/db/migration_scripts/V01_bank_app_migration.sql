/**Add sequence table to manage ACCOUNT id */
CREATE SEQUENCE ACCOUNT_TABLE_SEQ
    INCREMENT 13
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

/*Create ACCOUNT table*/
CREATE TABLE account (
	account_number BIGINT NOT NULL,
	balance DECIMAL NOT NULL,
	
	PRIMARY KEY (account_number)
);

/** Add comments on account table.*/
COMMENT ON TABLE account IS 'Entity representing a bank account.';
COMMENT ON COLUMN account.account_number IS 'Uniq identifier for the entity.';
COMMENT ON COLUMN account.balance IS 'Balance represents the amount of the account.';

/** Insert dataset for */
INSERT INTO account VALUES (1, 0.0);
INSERT INTO account VALUES (2, 0.0);