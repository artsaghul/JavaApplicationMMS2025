# Hospital Management System (Java + MySQL)

## Setup
1. Install MySQL and run `sql/schema.sql` (creates `hospital_db`, all tables, default login `admin` / `admin123`).
2. If your MySQL is not root/blank password, set env vars `HMS_DB_URL`, `HMS_DB_USER`, `HMS_DB_PASS`.
3. Open in NetBeans (Maven project) or run: `mvn compile exec:java -Dexec.mainClass=hospital.Main`

## Layout
- `hospital.models`   - 27 model classes (Person > Patient/Staff > Doctor/Nurse/Pharmacist/LaboratoryTechnician, plus records)
- `hospital.database` - JDBC connection
- `hospital.dao`      - Patient, Pharmacist, Room, Treatment, User, Ward, MedicalRecord DAOs
- `hospital.Main`     - console menu
