# 📚 Student Management System (Java)

![Java](https://img.shields.io/badge/Java-JDK25-blue)
![Status](https://img.shields.io/badge/Project-Stage%202-brightgreen)
![OOP](https://img.shields.io/badge/OOP-Concepts-orange)

A Java console-based Student Management System that performs CRUD (Create, Read, Update, Delete) operations using JDBC and MySQL while demonstrating Object-Oriented Programming (OOP) and Exception Handling.

---

## ✨ Features

- ➕ Add Student
- 📋 View All Students
- 🔍 Search Student by ID
- ✏️ Update Student Details
- ❌ Delete Student
- 💾 Persistent data storage using MySQL
- ⚠️ Input validation and exception handling

---
## 🛠️ Tech Stack

<p align="left">
  <img src="https://skillicons.dev/icons?i=java,mysql,git,github,idea" />
</p>

**Java • JDBC • MySQL • SQL • Git • GitHub • IntelliJ IDEA**

---

## 📚 Concepts Used

✔️ Object-Oriented Programming (OOP)

✔️ CRUD Operations

✔️ Exception Handling

✔️ Input Validation

✔️ JDBC API

✔️ PreparedStatement

✔️ Try-with-Resources

---
## ▶️ How to Run

1. Clone the repository.
2. Open the project in IntelliJ IDEA (or any Java IDE).
3. Install and start MySQL Server.
4. Execute the `database.sql` script to create the required database and table.
5. Copy `DBConnectionExample.java` and rename it to `DBConnection.java`.
6. Update the following fields in `DBConnection.java`:
    - Database URL
    - MySQL Username
    - MySQL Password
7. Add the MySQL JDBC Connector (JAR or Maven dependency).
8. Run `Main.java`.

> **Note:** `DBConnection.java` is excluded from this repository because it contains private database credentials. Use `DBConnectionExample.java` as a template and replace the placeholder values with your own MySQL configuration.
---
## 🚧 Project Roadmap

- ✅ Stage 1: Core CRUD using ArrayList (Completed)
- ✅ Stage 2: JDBC + MySQL Integration (Completed)
- ⏳ Stage 3: Additional Features (In Progress)


---

## 📁 Project Structure

```text
STUDENT-MANAGEMENT-SYSTEM/
│
├── src/
│   ├── Main.java                  # Application entry point
│   ├── Manager.java               # CRUD operations & business logic
│   ├── Student.java               # Student model class
│   └── DBConnectionExample.java   # Database connection template
│
├── database.sql                   # Database & table creation script
├── .gitignore                     # Git ignored files
└── README.md                      # Project documentation
```
---

## 📸 Screenshots

### 🏠 Main Menu

![Main Menu](screenshots/main.png)

### ➕ Add Student
![Add Student](screenshots/add.png)

### 📋 View Students
![View Students](screenshots/display.png)

### 🔍 Search Students
![Search Students](screenshots/search.png)

### 📝 Update Students data
![Update Students data](screenshots/update.png)

### 🗑️ Delete Students
![Delete Students](screenshots/delete.png)

---


## 👨‍💻 Author

**Shubham Joshi**

🔗 GitHub: <https://github.com/dev-jshubham>

📂 Repository: <https://github.com/dev-jshubham/student-management-system>