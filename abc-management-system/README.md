# ABC Management System (Hospital Management System)

A console-based Hospital Management System built with **Java (JDBC)** and **MySQL**.

## Features
- **Patients** — register, search, update, delete
- **Doctors** — register, search by specialization, delete
- **Appointments** — book, view (by patient/doctor), cancel, mark completed
- **Billing** — create bills, view, mark as paid
- Departments, rooms, and admissions tables are included in the schema for future extension

## Project Structure
```
abc-management-system/
├── pom.xml
├── sql/
│   └── schema.sql              # database schema + seed data
└── src/main/java/com/abc/hms/
    ├── Main.java                # console menu entry point
    ├── model/                   # Patient, Doctor, Appointment, Bill
    ├── dao/                     # JDBC data-access classes (CRUD)
    └── util/DBConnection.java   # JDBC connection helper
```

## 1. Set up the database
Make sure MySQL is installed and running, then run:
```bash
mysql -u root -p < sql/schema.sql
```
This creates the `abc_management_system` database with tables for
departments, doctors, patients, appointments, rooms, admissions, and billing,
plus a bit of seed data (departments, 3 sample doctors, 4 rooms).

## 2. Configure the connection
`DBConnection.java` reads these environment variables (with sensible defaults
for a local MySQL instance on port 3306, user `root`, no password):

| Variable          | Default                                                                 |
|-------------------|--------------------------------------------------------------------------|
| `ABC_DB_URL`      | `jdbc:mysql://localhost:3306/abc_management_system?useSSL=false&serverTimezone=UTC` |
| `ABC_DB_USER`     | `root`                                                                  |
| `ABC_DB_PASSWORD` | *(empty)*                                                               |

Set them before running, e.g.:
```bash
export ABC_DB_USER=root
export ABC_DB_PASSWORD=yourpassword
```

## 3. Build and run (Maven)
```bash
mvn clean package
java -jar target/abc-management-system-jar-with-dependencies.jar
```

If you're not using Maven, download the
[MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/) jar,
put it on your classpath, compile the `src` tree, and run
`com.abc.hms.Main`.

## 4. Using the app
You'll see a menu:
```
1. Patient Management
2. Doctor Management
3. Appointment Management
4. Billing
0. Exit
```
Each section has its own sub-menu for adding, listing, searching, updating
and deleting records. IDs shown when you add a record (e.g. patient ID,
doctor ID) are what you use later to book appointments or create bills.

## Notes / next steps you could add
- A Swing or JavaFX GUI instead of the console menu
- Login/authentication for staff (an `users` table with roles)
- Admissions/room-assignment workflow using the existing `rooms` and
  `admissions` tables (schema included but not yet wired into `Main`)
- Input validation and prepared-statement batch operations for bulk loads
