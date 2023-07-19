# School Payment Management System
## Project description

The School Payment Management project Basically, is a money management system for a school. we have to pay the salary to the teachers and collect the fees from the students., which includes the following features:
* Student Management
* Teacher Management
* Grade Management
* Fee Management
* Payment Management
* Salary Management

This project uses technologies related to the Spring framework, and the main components of the project are implemented based on a number of patterns, which are explained below.

## Project Technologies
* The database of this project is **H2**. A sample of how to set up this database could be found in [this](https://github.com/RahaShafaei/javaExercises/tree/main/SchoolPaymentManagementSystem/src/main/resources) directory.
* The build tool of this project is **Maven**.
* Interactions with the database are done through **Spring JPA**.
* Building **RESTful** web services with **Spring Boot** to access the inner world of `School Payment Management Project`.
* Use **HATEOAS** to Link builder API to create links pointing to Spring MVC controller methods.

## Project Details
* Use **ResponseEntityExceptionHandler** to centralize Spring MVC exception handling.
* Use **DTO Design Pattern**, to prevent `StackOverflow Exception` happening when using `Entity Beans`.
* Use **Observer Pattern** to notify students or teachers when their payment status changes.
* Use **FactoryMethod Pattern** to create different types of payment objects based on the payment mode.
* Use **Singleton Pattern** to manage the payment gateway integration or the notification system.
* Use **Decorator Pattern** to apply additional processing or validation logic to payment or salary objects.
* Use **Builder Pattern** to handle all `Controllers` constructor parameters.
* Trying to adhere to **SOLID** principles.
* For API documentation, **OpenAPI** is used with these addresses : `your server`/swagger-ui/index.html and `your server`/v3/api-docs

## Some additional helper tools
* [JSON Formatter](https://chrome.google.com/webstore/detail/json-formatter/bcjindcccaagfpapjjmafapmmgkkhgoa?hl=en-US)
* [Talend API Tester](https://chrome.google.com/webstore/detail/talend-api-tester-free-ed/aejoelaoggembcahagimdiliamlcdmfm?hl=en-US)