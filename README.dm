# Bank App
## What is it?
POC for a bank application.

The POC implements the following technologies:
- Spring : 
    - Spring-boot
    - Spring Web
    - Spring Data
    - Spring Test
    
- Persistence :
    - Database : Postgresql
	- Managing database schema and migration scripts versioning: Liquibase
	- DataBase for tests : H2
- Tools :
    - Swagger for API documentation
    - Lombok for light code generation.
    

## Running

1 - Create Database schema and the application user :
	Run the sql file creation : /bank-app/src/main/resources/db/V00_create_bank_db.sql

2 - Build the project by running the command :

	mvn clean install
It will compile the project, execute the tests and build the project.
	
3 - Run the project : It will deploy the project and also migrate database scripts.
You can use spring CLI : 
 	
 	mvn spring-boot:run
 Or run it with java :
 	
 	java -jar target/bank-app-0.0.1-SNAPSHOT.jar 




## Test it

You can use the swagger interface on :
    http://localhost:8080/swagger-ui.html#/bank-transaction-controller/
    
Or use any http client :

- Accont consultation :
    localhost:8080/account/1
    
- Deposit :
    localhost:8080/deposit
    
    {
      "amount": 1,
      "operatingAccount": 100
    }
    
- withdraw :
    localhost:8080/withdraw
    
    {
      "amount": 1,
      "operatingAccount": 100
    }

