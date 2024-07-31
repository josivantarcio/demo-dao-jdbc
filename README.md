# DemoDaoJDBC

## Overview
**DemoDaoJDBC** is a demonstration project that implements the Data Access Object (DAO) pattern using JDBC in Java. This project serves as an educational resource for developers looking to understand and apply the DAO pattern for database management in Java applications. By abstracting and encapsulating all access to the data source, the DAO pattern helps in maintaining a clean and modular architecture.

## Features
- **JDBC Integration**: Demonstrates the use of JDBC for database connectivity and operations.
- **DAO Pattern**: Implements the DAO pattern to handle database operations in a structured and efficient way.
- **Modular Design**: Promotes clean code architecture by separating data access logic from business logic.
- **Transaction Management**: Shows how to manage database transactions effectively.

## Technologies Used
- **Java 17**: The project is built using Java 17, ensuring modern language features and improvements.
- **JDBC**: Java Database Connectivity (JDBC) is used to connect and interact with the database.
- **MySQL**: The project uses MySQL as the relational database management system.

## Setup and Installation
1. **Clone the repository**:
   ```bash
   git clone https://github.com/josivantarcio/demo-dao-jdbc.git
   ```
2. **Import the project**:
   - Open your preferred IDE (e.g., Eclipse, IntelliJ IDEA).
   - Import the project as a Maven project.

3. **Configure the database**:
   - Create a MySQL database for the project.
   - Update the `db.properties` file with your database connection details:
     ```properties
     user=your_db_username
     password=your_db_password
     dburl=jdbc:mysql://localhost:3306/your_database_name
     ```

4. **Run the application**:
   - Run the `Main` class in the `application` package to see the DAO pattern in action.

## Usage
This project can be used as a starting point for learning how to implement the DAO pattern in Java applications. It can also serve as a template for building more complex applications that require a structured approach to database access.

## Contributing
If you find any issues or have suggestions for improvements, feel free to open an issue or submit a pull request.

## License
This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for more details.

## Contact
For any questions or feedback, please reach out through [GitHub Issues](https://github.com/josivantarcio/DemoDaoJDBC/issues).
