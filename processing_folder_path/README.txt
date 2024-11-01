# Java Microservices Project with Console Application

This project is a Java-based console application that utilizes a microservices architecture with key components such as Spring Boot, API Gateway, and Eureka for service discovery. The application manages products, customers, and orders, and it incorporates features like data validation, CSV file parsing and writing, and error logging.

## Table of Contents
- [Overview](#overview)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Functionality](#functionality)
- [Dependence Used](#dependence-used)
- [Error Handling](#error-handling)

## Overview
This project simulates a console-based management system for an e-commerce platform. It provides functionality to:
- Load, update, and delete customers and products.
- Process and manage orders.
- Validate and parse CSV data for input and output.
- Handle errors efficiently and log them to a file.

## Project Structure
The project is organized into several packages, each responsible for different parts of the functionality:
- **until**: Contains shared utilities like `CSVReader`, `CSVWriter`, `DataLoader`, and `ErrorLogger`.
- **consoleUi**: Manages the user interface through the console.
- **model**: Defines data models such as `Product`, `Customer`, and `Order`.
- **data.manager**: Contains classes to parse CSV lines into model object and logic read,writer.
- **services**: Provides business logic for managing products, customers, and orders.
- **validator**: Contains validators for data integrity checks.
- **variable.common**: Contains file constants and variables and processing modes.

## setup-and-installation
-use maven run command:mvn clean package
-cd to:MockUpProject\target\
-run command: java -jar MockUpProject-1.0-SNAPSHOT-jar-with-dependencies.jar <function_code> <src_folder>
-function code include:1 2.1 2.2 2.3 3.1 3.2 3.3 4.1 4.2 4.3 5.1 5.2
-folder src:get absolute folder path from input folder: MockUpProject\processing_folder_path

## Functionality
function_code                   Description
1                  load data:load all 3 file input and validate to output and log error
2.1                add new Products: load data and add new list products from file and log error
2.2                update Products : load data and update list products from file and log error
2.3                delete Products: load data and delete list products by list key(id) from file and log error
3.1                delete Customers: load data and delete list customers by list key(phone) from file and log error
3.2                add new Customers: load data and add new list customers  from file and log error
3.3                update Customers: load data and update new list customers  from file and log error
4.1                add new Orders: load data and add new list orders  from file and log error
4.2                update Orders: load data and update new list orders  from file and log error
4.3                delete Orders: load data and delete list orders by list key(id) from file and log error
5.1                search product: load data and search top 3 product by order
5.2                search order: load data and search list orders by list products form file

## Dependence Used
-Lombok 1.18.30
-Open CSV 5.7.1
-jdk 17

## Error Handling
-log to file