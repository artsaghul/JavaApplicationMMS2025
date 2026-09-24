CREATE DATABASE IF NOT EXISTS hospital_db;
USE hospital_db;

CREATE TABLE departments (
  id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL UNIQUE, description VARCHAR(255));

CREATE TABLE staff (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(60) NOT NULL, last_name VARCHAR(60) NOT NULL,
  gender VARCHAR(10), date_of_birth DATE, phone VARCHAR(30), email VARCHAR(100), address VARCHAR(255),
  role ENUM('ADMIN','DOCTOR','NURSE','PHARMACIST','LAB_TECHNICIAN','RECEPTIONIST') NOT NULL,
  department_id INT NULL, hire_date DATE,
  FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL);

CREATE TABLE users (
  id INT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(50) NOT NULL UNIQUE,
  password_hash CHAR(64) NOT NULL,
  role ENUM('ADMIN','DOCTOR','NURSE','PHARMACIST','LAB_TECHNICIAN','RECEPTIONIST') NOT NULL,
  staff_id INT NULL, FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE SET NULL);

CREATE TABLE doctors (staff_id INT PRIMARY KEY, specialization VARCHAR(100), license_no VARCHAR(50),
  FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE);
CREATE TABLE nurses (staff_id INT PRIMARY KEY, shift VARCHAR(20),
  FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE);
CREATE TABLE pharmacists (staff_id INT PRIMARY KEY, license_no VARCHAR(50),
  FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE);
CREATE TABLE lab_technicians (staff_id INT PRIMARY KEY, specialty VARCHAR(100),
  FOREIGN KEY (staff_id) REFERENCES staff(id) ON DELETE CASCADE);

CREATE TABLE wards (
  id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, department_id INT, type VARCHAR(50),
  FOREIGN KEY (department_id) REFERENCES departments(id) ON DELETE SET NULL);
CREATE TABLE rooms (
  id INT AUTO_INCREMENT PRIMARY KEY, room_number VARCHAR(20) NOT NULL, ward_id INT NOT NULL,
  type VARCHAR(50), available BOOLEAN DEFAULT TRUE,
  FOREIGN KEY (ward_id) REFERENCES wards(id) ON DELETE CASCADE);
CREATE TABLE beds (
  id INT AUTO_INCREMENT PRIMARY KEY, bed_number VARCHAR(20) NOT NULL, room_id INT NOT NULL,
  occupied BOOLEAN DEFAULT FALSE, FOREIGN KEY (room_id) REFERENCES rooms(id) ON DELETE CASCADE);

CREATE TABLE patients (
  id INT AUTO_INCREMENT PRIMARY KEY,
  first_name VARCHAR(60) NOT NULL, last_name VARCHAR(60) NOT NULL,
  gender VARCHAR(10), date_of_birth DATE, phone VARCHAR(30), email VARCHAR(100), address VARCHAR(255),
  blood_group VARCHAR(5), emergency_contact VARCHAR(100));

CREATE TABLE appointments (
  id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT NOT NULL, doctor_id INT NOT NULL,
  appointment_time DATETIME NOT NULL, status VARCHAR(20) DEFAULT 'SCHEDULED', reason VARCHAR(255),
  FOREIGN KEY (patient_id) REFERENCES patients(id), FOREIGN KEY (doctor_id) REFERENCES doctors(staff_id));
CREATE TABLE admissions (
  id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT NOT NULL, bed_id INT NOT NULL, doctor_id INT NOT NULL,
  admit_date DATE NOT NULL, discharge_date DATE NULL, reason VARCHAR(255),
  FOREIGN KEY (patient_id) REFERENCES patients(id), FOREIGN KEY (bed_id) REFERENCES beds(id),
  FOREIGN KEY (doctor_id) REFERENCES doctors(staff_id));

CREATE TABLE medical_records (
  id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT NOT NULL, doctor_id INT NOT NULL,
  record_date DATE NOT NULL, notes TEXT,
  FOREIGN KEY (patient_id) REFERENCES patients(id), FOREIGN KEY (doctor_id) REFERENCES doctors(staff_id));
CREATE TABLE diagnoses (
  id INT AUTO_INCREMENT PRIMARY KEY, medical_record_id INT NOT NULL, description VARCHAR(255) NOT NULL,
  icd_code VARCHAR(20), FOREIGN KEY (medical_record_id) REFERENCES medical_records(id) ON DELETE CASCADE);
CREATE TABLE treatments (
  id INT AUTO_INCREMENT PRIMARY KEY, medical_record_id INT NOT NULL, name VARCHAR(100) NOT NULL,
  description VARCHAR(255), start_date DATE, end_date DATE, cost DECIMAL(10,2) DEFAULT 0,
  FOREIGN KEY (medical_record_id) REFERENCES medical_records(id) ON DELETE CASCADE);

CREATE TABLE medications (
  id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100) NOT NULL, description VARCHAR(255),
  unit_price DECIMAL(10,2) NOT NULL, stock INT DEFAULT 0);
CREATE TABLE prescriptions (
  id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT NOT NULL, doctor_id INT NOT NULL,
  prescription_date DATE NOT NULL, notes VARCHAR(255),
  FOREIGN KEY (patient_id) REFERENCES patients(id), FOREIGN KEY (doctor_id) REFERENCES doctors(staff_id));
CREATE TABLE prescription_items (
  id INT AUTO_INCREMENT PRIMARY KEY, prescription_id INT NOT NULL, medication_id INT NOT NULL,
  dosage VARCHAR(50), quantity INT NOT NULL, instructions VARCHAR(255),
  FOREIGN KEY (prescription_id) REFERENCES prescriptions(id) ON DELETE CASCADE,
  FOREIGN KEY (medication_id) REFERENCES medications(id));
CREATE TABLE medication_dispensing (
  id INT AUTO_INCREMENT PRIMARY KEY, prescription_item_id INT NOT NULL, pharmacist_id INT NOT NULL,
  dispensed_at DATETIME NOT NULL, quantity INT NOT NULL,
  FOREIGN KEY (prescription_item_id) REFERENCES prescription_items(id),
  FOREIGN KEY (pharmacist_id) REFERENCES pharmacists(staff_id));

CREATE TABLE laboratory_tests (
  id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT NOT NULL, doctor_id INT NOT NULL, technician_id INT NULL,
  test_name VARCHAR(100) NOT NULL, requested_date DATE NOT NULL, result TEXT,
  status VARCHAR(20) DEFAULT 'REQUESTED', cost DECIMAL(10,2) DEFAULT 0,
  FOREIGN KEY (patient_id) REFERENCES patients(id), FOREIGN KEY (doctor_id) REFERENCES doctors(staff_id),
  FOREIGN KEY (technician_id) REFERENCES lab_technicians(staff_id));
CREATE TABLE nurse_assignments (
  id INT AUTO_INCREMENT PRIMARY KEY, nurse_id INT NOT NULL, patient_id INT NOT NULL, ward_id INT NOT NULL,
  assignment_date DATE NOT NULL, shift VARCHAR(20),
  FOREIGN KEY (nurse_id) REFERENCES nurses(staff_id), FOREIGN KEY (patient_id) REFERENCES patients(id),
  FOREIGN KEY (ward_id) REFERENCES wards(id));

CREATE TABLE invoices (
  id INT AUTO_INCREMENT PRIMARY KEY, patient_id INT NOT NULL, invoice_date DATE NOT NULL,
  total DECIMAL(10,2) DEFAULT 0, status VARCHAR(20) DEFAULT 'UNPAID',
  FOREIGN KEY (patient_id) REFERENCES patients(id));
CREATE TABLE invoice_items (
  id INT AUTO_INCREMENT PRIMARY KEY, invoice_id INT NOT NULL, description VARCHAR(255) NOT NULL,
  quantity INT DEFAULT 1, unit_price DECIMAL(10,2) NOT NULL,
  FOREIGN KEY (invoice_id) REFERENCES invoices(id) ON DELETE CASCADE);
CREATE TABLE payments (
  id INT AUTO_INCREMENT PRIMARY KEY, invoice_id INT NOT NULL, amount DECIMAL(10,2) NOT NULL,
  payment_date DATE NOT NULL, method VARCHAR(30),
  FOREIGN KEY (invoice_id) REFERENCES invoices(id));

-- Default login: admin / admin123
INSERT INTO users (username, password_hash, role) VALUES ('Utomi', SHA2('Utomi123',256), 'ADMIN');
INSERT INTO departments (name, description) VALUES ('General Medicine','General care'),('Pharmacy','Drug dispensing');
