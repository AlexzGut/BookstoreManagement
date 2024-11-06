# Bookstore Management System

The **Bookstore Management System** is a SQL-based project designed to handle the core functions of managing a bookstore. This project includes features for storing and managing books, authors, genres, publishers, and addresses, with a focus on efficient data management and relational integrity.

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Database Schema](#database-schema)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Project Overview

This project demonstrates database design principles, SQL schema creation, and handling complex relationships between tables. The bookstore database supports full CRUD functionality and integrates seamlessly with RESTful API endpoints, allowing users to perform operations on books, authors, genres, and publishers.

## Features

- **CRUD Operations**: Add, view, update, and delete records for books, authors, genres, and publishers.
- **Relational Database Design**:
  - **One-to-Many**: Publishers can have multiple books.
  - **Many-to-Many**: Books can have multiple authors and genres.
  - **One-to-One (Optional)**: Each publisher can optionally have one address.
- **Data Integrity**: Primary keys, foreign keys, and unique constraints ensure robust data management.
- **API Integration**: Supports RESTful API calls to interact with the database.

## Database Schema

The following tables are included in the schema:

- **book**: Stores book details including ISBN, title, language, price, and publisher.
- **author**: Contains author details such as name, pseudonym, and nationality.
- **book_author**: Linking table to support the many-to-many relationship between books and authors.
- **genre**: Stores genre categories and descriptions.
- **book_genre**: Linking table to support the many-to-many relationship between books and genres.
- **publisher**: Stores publisher information, including a possible relationship to an address.
- **address**: Contains address details for publishers.

### Entity Relationships

1. **Books and Authors**: A many-to-many relationship using the `book_author` table.
2. **Books and Genres**: A many-to-many relationship using the `book_genre` table.
3. **Publishers and Books**: A one-to-many relationship, where one publisher can have multiple books.
4. **Publishers and Addresses**: An optional one-to-one relationship for publishers' addresses.

## Technologies Used

- **SQL**: Database creation and management.
- **Java EE (Jersey)**: REST API integration.
- **JDBC**: For database connection and CRUD operations.
- **Eclipse**: Development environment.

## Installation

1. **Clone the Repository**
   ```bash
   git clone https://github.com/AlexzGut/BookstoreManagement.git
   ```
2. **Database Setup:**

- Create a database named bookstore.
- Import the SQL schema from
```bash
BookstoreManagement/
├── src/
│   ├── main/
│   │   └── resources/
│       ├── ddl/                      # SQL Data Definition Language
│       │   ├── book.txt              # Database schema
│       ├── dml/                      # SQL Data Manipulation Language
│       │   └── insert statements.txt # Database Data
```
3. **Configure Database Connection:** Adjust JDBC connection settings in the application code if needed.
Run the Application.

## Usage
- **Postman** or similar tools can be used to test the API endpoints.
- Run CRUD operations on *books* to interact with the database.
- 
## Contributing
Contributions are welcome! Please open an issue or submit a pull request for any updates or feature suggestions.

## License
This project is licensed under the MIT License.
