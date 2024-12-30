# Authenticate Login System Using Java

A secure Java web application built with Jakarta EE that implements user authentication with email verification. This system provides a robust login mechanism with features like secure user registration, email verification, and token-based authentication.

## Features

- User email verification system
- Secure token-based verification
- SMTP email integration with Gmail
- Database-backed token storage and verification
- Secure random token generation
- SQL injection prevention using prepared statements
- Real-time active user tracking
- Admin dashboard with total active users count
- User session management

## Technologies Used

- Java
- Jakarta EE
- Maven
- MySQL 8.3.0
- Jakarta Mail (for email functionality)
- PHPMyAdmin 5.2.1 (for database management)
- Apache Tomcat Server (for deployment)

## Project Structure

```
LoginHere/
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── mycompany/
│                   └── loginhere/
│                       ├── modal/      # Business logic and data models
│                       ├── servlet/    # Web controllers
│                       ├── listener/   # Event listeners
│                       └── resources/  # Application resources
├── login.sql          # Database schema
├── pom.xml           # Maven configuration
└── README.md         # Project documentation
```

## Setup

1. Clone the repository
2. Import database schema:
   - Create a database named `mydb1` in MySQL
   - Import `login.sql` using PHPMyAdmin or MySQL command line
   - The schema includes a `login` table with fields for username, password, name, email, verification token, and verification status
3. Configure your database connection settings
4. Update the SMTP settings with your Gmail credentials
5. Build the project using Maven:
   ```
   mvn clean install
   ```
6. Deploy to Apache Tomcat:
   - Copy the generated WAR file from `target` folder to Tomcat's `webapps` directory
   - Start Tomcat server (use `startup.bat` for Windows)
   - Access the application at `http://localhost:8080/LoginHere`

## Server Requirements

- Apache Tomcat 10.x or higher (required for Jakarta EE 9+ support)
- JDK 11 or higher
- Default port: 8080 (configurable in Tomcat's server.xml)

## Database Schema

The application uses a MySQL database with the following structure:

### Login Table
```sql
CREATE TABLE login (
  username varchar(50) NOT NULL,
  password varchar(50) NOT NULL,
  fname varchar(50) NOT NULL,
  lname varchar(50) NOT NULL,
  email varchar(50) NOT NULL,
  token varchar(50) NOT NULL,
  verify varchar(50) NOT NULL
)
```

## Configuration

### Email Configuration
The application uses Gmail SMTP for sending verification emails. Update the following settings in the `Mail.java`:
- SMTP Host: smtp.gmail.com
- SMTP Port: 587
- Enable TLS: true

### Database Configuration
Configure your database connection properties in your application's configuration file:
- Database Name: mydb1
- Server: localhost:3306
- Database Type: MySQL 8.3.0

### Tomcat Configuration
- Default port: 8080
- Context path: /LoginHere
- Application can be accessed at: http://localhost:8080/LoginHere

## Security Features

- Secure random token generation for email verification
- SQL prepared statements to prevent SQL injection
- TLS-enabled SMTP connection

## Contributing

Feel free to submit issues and enhancement requests.

## License

[MIT License](LICENSE)
