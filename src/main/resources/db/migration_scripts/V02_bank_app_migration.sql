
/**Add sequence table to manage transaction id */
CREATE SEQUENCE TRANSACTION_TABLE_SEQ
    INCREMENT 17
    START 1
    MINVALUE 1
    MAXVALUE 9223372036854775807
    CACHE 1;

/*Create Transaction table*/
CREATE TABLE transaction (
	ID  BIGINT NOT NULL PRIMARY KEY,
	transaction_date date,
    transaction_amount DECIMAL NOT NULL,
	transaction_type VARCHAR(50) NOT NULL,
    account_number bigint NOT NULL
);

/* Add comments on transaction table. */
COMMENT ON TABLE transaction IS 'Entity representing a bank transactions.';
COMMENT ON COLUMN transaction.ID IS 'Uniq identifier for the entity.';
COMMENT ON COLUMN transaction.transaction_type IS 'Transaction type';
COMMENT ON COLUMN transaction.transaction_date IS 'The transaction date';
COMMENT ON COLUMN transaction.transaction_amount IS 'The amount of the transaction';