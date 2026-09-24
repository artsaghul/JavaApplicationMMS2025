-- ============================================================
-- ABC Management System (Hospital Management System)
-- Database Schema (MySQL)
-- ============================================================

DROP DATABASE IF EXISTS abc_management_system;
CREATE DATABASE abc_management_system;
USE abc_management_system;

-- ------------------------------------------------------------
-- Departments
-- ------------------------------------------------------------
CREATE TABLE departments (
    department_id   INT AUTO_INCREMENT PRIMARY KEY,
    name            VARCHAR(100) NOT NULL UNIQUE,
    location        VARCHAR(100)
);

-- ------------------------------------------------------------
-- Doctors
-- ------------------------------------------------------------
CREATE TABLE doctors (
    doctor_id       INT AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(50)  NOT NULL,
    last_name       VARCHAR(50)  NOT NULL,
    specialization  VARCHAR(100),
    phone           VARCHAR(20),
    email           VARCHAR(100),
    department_id   INT,
    FOREIGN KEY (department_id) REFERENCES departments(department_id)
        ON DELETE SET NULL
);

-- ------------------------------------------------------------
-- Patients
-- ------------------------------------------------------------
CREATE TABLE patients (
    patient_id      INT AUTO_INCREMENT PRIMARY KEY,
    first_name      VARCHAR(50)  NOT NULL,
    last_name       VARCHAR(50)  NOT NULL,
    date_of_birth   DATE,
    gender          ENUM('M','F','Other'),
    phone           VARCHAR(20),
    email           VARCHAR(100),
    address         VARCHAR(255),
    registered_on   DATE DEFAULT (CURRENT_DATE)
);

-- ------------------------------------------------------------
-- Appointments
-- ------------------------------------------------------------
CREATE TABLE appointments (
    appointment_id  INT AUTO_INCREMENT PRIMARY KEY,
    patient_id      INT NOT NULL,
    doctor_id       INT NOT NULL,
    appointment_date DATETIME NOT NULL,
    reason          VARCHAR(255),
    status          ENUM('SCHEDULED','COMPLETED','CANCELLED') DEFAULT 'SCHEDULED',
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (doctor_id)  REFERENCES doctors(doctor_id)   ON DELETE CASCADE
);

-- ------------------------------------------------------------
-- Rooms / Admissions
-- ------------------------------------------------------------
CREATE TABLE rooms (
    room_id         INT AUTO_INCREMENT PRIMARY KEY,
    room_number     VARCHAR(10) NOT NULL UNIQUE,
    room_type       ENUM('GENERAL','PRIVATE','ICU') DEFAULT 'GENERAL',
    is_occupied     BOOLEAN DEFAULT FALSE
);

CREATE TABLE admissions (
    admission_id    INT AUTO_INCREMENT PRIMARY KEY,
    patient_id      INT NOT NULL,
    room_id         INT NOT NULL,
    admitted_on     DATETIME DEFAULT CURRENT_TIMESTAMP,
    discharged_on   DATETIME NULL,
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (room_id)    REFERENCES rooms(room_id)
);

-- ------------------------------------------------------------
-- Billing
-- ------------------------------------------------------------
CREATE TABLE billing (
    bill_id         INT AUTO_INCREMENT PRIMARY KEY,
    patient_id      INT NOT NULL,
    appointment_id  INT NULL,
    amount          DECIMAL(10,2) NOT NULL,
    bill_date       DATE DEFAULT (CURRENT_DATE),
    status          ENUM('PENDING','PAID') DEFAULT 'PENDING',
    FOREIGN KEY (patient_id) REFERENCES patients(patient_id) ON DELETE CASCADE,
    FOREIGN KEY (appointment_id) REFERENCES appointments(appointment_id) ON DELETE SET NULL
);

-- ------------------------------------------------------------
-- Sample seed data
-- ------------------------------------------------------------
INSERT INTO departments (name, location) VALUES
('Cardiology', 'Block A, Floor 2'),
('Orthopedics', 'Block B, Floor 1'),
('Pediatrics', 'Block A, Floor 1'),
('General Medicine', 'Block C, Floor 1');

INSERT INTO doctors (first_name, last_name, specialization, phone, email, department_id) VALUES
('Ade', 'Okoro', 'Cardiologist', '08011112222', 'ade.okoro@abchospital.com', 1),
('Ngozi', 'Bello', 'Orthopedic Surgeon', '08022223333', 'ngozi.bello@abchospital.com', 2),
('Emeka', 'Nwosu', 'Pediatrician', '08033334444', 'emeka.nwosu@abchospital.com', 3);

INSERT INTO rooms (room_number, room_type, is_occupied) VALUES
('101', 'GENERAL', FALSE),
('102', 'GENERAL', FALSE),
('201', 'PRIVATE', FALSE),
('ICU-1', 'ICU', FALSE);
