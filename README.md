# 🚀 Startup Ecosystem Database

## 📌 Overview
This project implements a **Startup Ecosystem Database** using **PostgreSQL** and **Java (JDBC + Swing GUI)**.  
It allows users to perform CRUD operations on startup data and provides insights into relationships among startups, founders, investors, mentors, and accelerators.

---

## 🗂️ Features
- Relational Database Design with **ER & Schema diagrams**.
- **Normalized tables** up to 3NF for efficiency.
- **DDL & DML Scripts** for PostgreSQL.
- **CRUD operations** for Startup entity via GUI.
- GUI built with **Java Swing**.
- JDBC connectivity to PostgreSQL.

---

## ⚙️ Tech Stack
- **Database**: PostgreSQL (pgAdmin recommended)
- **Backend**: Java (JDBC)
- **Frontend**: Java Swing GUI
- **Concepts**: ER Modeling, Normalization, DDL/DML, SQL Queries

---


---

## ▶️ How to Run

### 1. Setup Database
1. Install **PostgreSQL**.
2. Create a database named `DBMS_G39`.
3. Run `schema.sql` to create tables.
4. Run `data.sql` to insert sample records.

### 2. Configure Java Project
1. Place `DatabaseManager.java` and `StartupGUI.java` inside your Java project.
2. Update database credentials in `DatabaseManager.java`:
   ```java
   private static final String URL = "jdbc:postgresql://localhost:5432/DBMS_G39";
   private static final String USER = "postgres";
   private static final String PASSWORD = "your_password";
3. Add PostgreSQL JDBC driver (postgresql-<version>.jar) to your classpath.

### 3. Run and compile
javac DatabaseManager.java StartupGUI.java
java StartupGUI

