/*********************************************************************
 * -- version : V00.
 * -- Script  : Create database and users for Bank application.
 *********************************************************************/

/* Create database */
create database bank_db;

/* Create user bank_db_usr */
CREATE USER bank_db_usr WITH PASSWORD 'bank_db_pwd';

/* Give all privileges to the bank user. */
GRANT ALL PRIVILEGES ON DATABASE bank_db to bank_db_usr;