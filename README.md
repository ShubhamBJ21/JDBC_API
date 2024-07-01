# JDBC API Examples

This repository contains examples demonstrating the use of Java Database Connectivity (JDBC) with Statements and Prepared Statements. JDBC is an API that enables Java applications to interact with databases.

## Table of Contents

- [Overview](#overview)
- [Setup](#setup)
- [JDBC Statements](#jdbc-statements)
- [JDBC Prepared Statements](#jdbc-prepared-statements)
- [JDBC Callable Statements](#jdbc-callable-statements)
- [Running the Examples](#running-the-examples)

## Overview

Java Database Connectivity (JDBC) is a Java-based API used to connect to and execute queries on databases. It provides methods to query and update data in a database and is part of the Java Standard Edition platform.

There are two main types of statements in JDBC:
1. **Statements**: Used for executing static SQL queries.
2. **Prepared Statements**: Used for executing precompiled SQL queries with parameters, offering better performance and security.
3. **Callable Statement**: Used to call a stored procedures and functions.
## Setup

### Prerequisites

- JDK 8 or higher
- MySQL Database
- MySQL JDBC Driver

### Database Setup

1. Install MySQL and create a database named `studentdb`.
2. Create a `student` table with the following schema:

    ```sql
    CREATE TABLE student (
        id INT PRIMARY KEY,
        name VARCHAR(50),
        address VARCHAR(100),
        contact BIGINT
    );
    ```

3. Insert some initial data into the `student` table (optional).

### Project Setup

1. Clone this repository:

    ```sh
    git clone https://github.com/yourusername/jdbc-api-examples.git
    ```

2. Navigate to the project directory:

    ```sh
    cd jdbc-api-examples
    ```

3. Add the MySQL JDBC Driver to your project's classpath. You can download it from the [MySQL website](https://dev.mysql.com/downloads/connector/j/) or use a dependency management tool like Maven or Gradle.

## JDBC Statements

JDBC Statements are used for executing static SQL queries. Below is an example of inserting a record using a JDBC Statement.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentInsertStatement {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";
        String password = "root";

        String query = "INSERT INTO student (id, name, address, contact) VALUES (1, 'Shubham Jadhav', 'At post Katraj, Pune', 8830086429)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement statement = connection.createStatement()) {

            int result = statement.executeUpdate(query);

            if (result != 0) {
                System.out.println("Data inserted successfully using Statement");
            } else {
                System.out.println("Data not inserted!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
```
## JDBC Statements
JDBC Statements are used for executing static SQL queries. Below is an example of inserting a record using a JDBC PreparedStatement.

```java
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class StudentInsertPreparedStatement {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();  

        System.out.println("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.println("Enter Student Address: ");
        String address = sc.nextLine();

        System.out.println("Enter Student Contact Number: ");
        long contact = sc.nextLong();

        String url = "jdbc:mysql://localhost:3306/studentdb";
        String username = "root";
        String password = "root";

        String query = "INSERT INTO student (id, name, address, contact) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(url, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, address);
            preparedStatement.setLong(4, contact);

            int result = preparedStatement.executeUpdate();

            if (result != 0) {
                System.out.println("Data inserted successfully using PreparedStatement");
            } else {
                System.out.println("Data not inserted!");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
