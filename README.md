# Student Management System

## 1. Project Overview

The Student Management System is a Java-based application developed to simplify student information management and attendance tracking.

The application uses Object-Oriented Programming (OOP) principles and MySQL database programming to store, retrieve, update, and manage student records.

## 2. Features

### Student Management
- Admin login
- Register new students
- View all registered students
- Search students by Student ID
- Update student information
- Delete student records

### Attendance Management
- Record student attendance
- Mark students as PRESENT or ABSENT
- View all attendance records
- Search attendance by Student ID
- Generate attendance summaries
- Calculate attendance percentages

## 3. Technologies Used

- Java
- MySQL
- JDBC (Java Database Connectivity)
- MySQL Connector/J
- Visual Studio Code
- Git and GitHub

## 4. Object-Oriented Programming Concepts

The project demonstrates:

- Classes and Objects
- Inheritance
- Polymorphism
- Encapsulation
- Constructors and Methods
- Built-in and User-defined Methods
- Arrays and ArrayLists

## 5. Project Structure

```text
StudentManagementSystem/
│
├── lib/
│   └── mysql-connector-j.jar
│
├── Person.java
├── Student.java
├── Attendance.java
├── StudentDAO.java
├── AttendanceDAO.java
├── DBConnection.java
├── StudentManagementSystem.java
├── .gitignore
└── README.md
```

## 6. Database Configuration

The application uses MySQL.

Create a database named `student_management` and configure the required tables:

- students
- attendance

Configure your MySQL username and password using the environment variables expected by `DBConnection.java`.

Do not publish your database credentials.

## 7. How to Run the Project

1. Install the Java Development Kit (JDK).
2. Install MySQL Server and MySQL Workbench.
3. Create the `student_management` database and required tables.
4. Place MySQL Connector/J inside the `lib` directory.
5. Configure the `SMS_DB_PASSWORD` environment variable.
6. Open the project folder in VS Code.
7. Compile the Java source files:

```powershell
javac -cp "lib/*;." StudentManagementSystem.java Person.java Student.java StudentDAO.java DBConnection.java Attendance.java AttendanceDAO.java
```

8. Run the application:

```powershell
java -cp "lib/*;." StudentManagementSystem
```

## 8. Project Purpose

This project demonstrates the practical application of Java programming, object-oriented design, database connectivity, and CRUD operations in solving a real-world student information management problem.

