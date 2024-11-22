# Device Manager Microservice
## Overview
**Device Manager** is a backend microservice built with Java and Spring Framework, designed to manage devices and their associations with users (holders). 
The service provides functionality to create and retrieve devices, as well as create and retrieve holders. It also includes logic to connect users to devices. 
This project demonstrates clean code practices, modular architecture, and comprehensive unit test coverage, serving as a showcase of my backend development skills.

## Features
Device Management

Create new devices.
Retrieve device information.
Holder (User) Management

Create new holders.
Retrieve holder information.
Device-Holder Association

Connect devices to holders.
Retrieve associations.

## Architecture
The application is structured into well-defined modules, promoting separation of concerns and scalability:

App: The entry point for the application, responsible for bootstrapping and configuration.
Domain: Contains core business logic, entities, and interfaces, ensuring independence from external frameworks.
Persistence: Manages database interactions and repositories, implementing storage-specific details.
Web: Handles HTTP requests and responses, containing controllers and REST endpoints.
UseCaseApi: Defines interfaces for use cases, promoting clear contracts for application behavior.
UseCaseImpl: Implements the use case logic defined in UseCaseApi, coordinating interactions between other modules.
This modular design ensures that each layer has a single responsibility, improving maintainability and testability.

## Potential Improvements
This project can be extended with additional features and improvements:

**Account Entity**: Introduce an Account entity to represent application users.
Role-Based Access Control (RBAC):
Add roles (e.g., Admin, User) for fine-grained permissions.
Implement authorization logic to restrict access to specific resources.
Authentication and Authorization:
Use an Authentication Manager with one or multiple authentication providers.
Add interceptors to validate user credentials and roles for specific endpoints.
Secure endpoints with JWT, OAuth2, or session-based authentication.
Enhanced Security: Apply security best practices such as HTTPS, CSRF protection, and input validation.
Technologies Used
Language: Java
Framework: Spring Framework
Testing: JUnit for unit tests
Build Tool: Maven
Database: H2 In-Memory
