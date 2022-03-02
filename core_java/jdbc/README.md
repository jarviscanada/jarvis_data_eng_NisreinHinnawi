# Introduction

The project's purpose is to learn about JDBC and get familiar with JDBC and data access-designed patterns. The application uses JDBC to
connect Java to the PostgreSQL database for running queries and perform the CRUD on the database. Docker container was used to provision the PostgreSQL database.

Tools that were used:
- Git
- Docker
- IntelliJ IDEA
- PostgreSQL and PSQL CLI tool

# Implementation
## ER Diagram

![](./assets/ER.png)


## Design Patterns
#### DAO Pattern:
Data Access Object is an abstraction of data persistence, it provides access to an underlying database or any other persistent storage.
DAO comes in two forms abstraction or a true object. The DAO completely hides the data source implementation details from its clients.


#### Repository Pattern:
Repository is an abstraction of a collection of objects. It encapsulates the logic required to access data sources. Repository also deals with data and hides source implementation details similar to DAO.


# Test
Docker container was used to run the PostgreSQL database.
SQL scripts were used to create the database and its tables.
Then JDBCExecuter class was used to test the CRUD operations and compare the result with the expected result.
