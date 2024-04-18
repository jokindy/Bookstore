# BookStore App ReadME

This project is a Spring Boot application designed to manage books and authors. It provides various endpoints to perform CRUD operations on books and authors, with different access levels for authorized users and admins.

## Endpoints

### Public Endpoints

#### GET /books
- **Description:** Returns all books.
- **Query Parameters:** Can manage list by page parameters.

#### GET /books/unique
- **Description:** Returns books with unique titles.
- **Query Parameters:** Can manage list by page parameters.

#### GET /books/name
- **Description:** Returns books with author name.
- **Query Parameters:** Can manage list by page parameters.

#### GET /books/{bookId}
- **Description:** Returns book with provided Id.

### Admin Endpoints

#### POST /books
- **Description:** Adds a new book.

#### PUT /books
- **Description:** Updates an existing book.

#### DELETE /books/{bookId}
- **Description:** Deletes a book by bookId.

#### POST /authors
- **Description:** Adds a new author.

### Access Control
- Public endpoints are accessible to authorized users.
- Admin endpoints are accessible only to users with admin privileges.

## Technologies Used

### Spring Framework
- **Spring Boot 3.2.4:** Framework for creating Spring-based applications.
- **Spring Data JPA:** Provides support for data access using JPA (Java Persistence API) and Hibernate.
- **Spring Security:** Framework for authentication, authorization, and protection against common security exploits.

### Database
- **PostgreSQL:** Relational database management system used for storing application data.
- **H2:** In-memory database for development and testing purposes.
- **Liquibase:** Database schema change management tool.

### Testing
- **Spring Boot Tests:** Framework for testing Spring Boot applications.
- **JUnit 5:** Testing framework for Java applications.
- **JaCoCo:** Java Code Coverage library for measuring code coverage during testing.

### Other
- **MapStruct:** Annotation-based code generator for mapping between Java bean types.
- **Lombok:** Library to reduce boilerplate code in Java applications.
- **Keycloak:** Open-source identity and access management solution for securing applications and services.

## Usage
To get started with the project, follow these steps:

1. **Clone the repository:**
    ```bash
    git clone <repository_url>
    ```

2. **Configure your database settings:**
    - Open `application.yaml` and update the database configuration according to your PostgreSQL setup.

3. **Run PostgreSQL database:**
    - Ensure PostgreSQL is running on `localhost:5432`.

4. **Run Keycloak and PostgreSQL containers:**
    - Use the provided `docker-compose.yaml` file to run Keycloak and PostgreSQL containers.
    ```bash
    docker-compose -f docker-compose.yaml up -d
    ```

5. **Import BookStore realm in Keycloak:**
    - Access Keycloak admin console (default URL: `http://localhost:8484`) and import the provided `BookStore` realm.
    - Default username and password are "user" and "bitnami".

6. **Build and run the application:**
    - Build the application using Maven or your IDE.

7. **Access the endpoints:**
    - Use Postman to access the endpoints. Collections for this are included in the project directory.


## Testing (in development)
- Unit tests are available for testing each endpoint and service.
- Run `mvn test` to execute the tests.
- JaCoCo is used for code coverage analysis.
