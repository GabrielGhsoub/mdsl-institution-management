# Institution Management System

This is an Institution Management System built with Spring Boot, featuring JWT authentication, database integration with H2, and comprehensive API endpoints for managing institutions and users.

## Features

- **JWT Authentication**: Secure API access using JWT tokens.
- **Data Storage**: Integrates H2 and MySQL databases for data storage.
- **Logging**: Implements detailed logging for monitoring and debugging.
- **Unit Testing**: Includes unit tests for service and controller layers.

## Running the Application

1. Clone the repository to your local machine.
2. Open Eclipse IDE.
3. Navigate to `File -> Import -> Maven -> Existing Maven Projects`.
4. Select the root directory of the project and click `Finish`.
5. In the Package Explorer pane, right-click on the project and choose `Run As -> Spring Boot App`.

## Interacting with API Endpoints

You can interact with the API using HTTP clients like curl, Postman, etc. A Postman collection is provided in the repository.

### Available Endpoints

- **Institution Management**
  - `GET /api/institutions`: Retrieve all institutions.
  - `GET /api/institutions/{id}`: Retrieve an institution by ID.
  - `GET /api/institutions/status/{status}`: Retrieve institutions by status.
  - `POST /api/institutions`: Add a new institution.
  - `PUT /api/institutions`: Update an existing institution.
  - `DELETE /api/institutions/{id}`: Remove an institution.

- **User Authentication**
  - `POST /api/auth/login`: Authenticate the user and return a JWT token.
  - `POST /api/auth/refresh`: Refresh the JWT token using a refresh token.

### Authentication and Token Management

1. **Login**: Send a `POST` request to `/api/auth/login` with `username` and `password`. If authentication is successful, a JWT token and refresh token will be returned.
2. **Token Refresh**: Send a `POST` request to `/api/auth/refresh` with the refresh token to obtain a new JWT token.

## Configuring Data Storage

The application supports both H2 and MySQL databases. Switch between them in the `application.yml` file by setting the active profile.

## Logging

Logging is implemented to provide detailed insights into the application's operations. Logs are output to the console.

## Unit Testing

Unit tests are implemented for services and controllers. Run tests via Eclipse by right-clicking on the project and selecting `Run As -> JUnit Test`.

## Postman Collection

A Postman collection is provided in the repository for testing API endpoints. Found in the "Postman Requests" folder, import the collection into Postman to get started. The collection includes:

- **Login**: Authenticate the user and retrieve JWT and refresh tokens.
- **Refresh Token**: Refresh the JWT token using the provided refresh token.
- **CRUD Operations**: Create, retrieve, update, and delete institutions.

## H2 Database Console

Access the H2 database console at [http://localhost:8080/h2-console](http://localhost:8080/h2-console). Use the following credentials:
- **JDBC URL**: `jdbc:h2:mem:test1db`
- **Username**: `sa`
- **Password**: *(leave empty)*

For more detailed information, please refer to the individual Java files in the project.

## Additional Information

For any further information or assistance, please feel free to contact me at: 

ghoussoubgabriel@gmail.com 


---

Thank you!
