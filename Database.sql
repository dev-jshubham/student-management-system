-- =====================================================
-- Student Management System Database
-- =====================================================

-- Create the database
CREATE DATABASE IF NOT EXISTS project1_sms;

-- Use the database
USE project1_sms;

-- Create the students table
CREATE TABLE IF NOT EXISTS students (
                id INT PRIMARY KEY,
                name VARCHAR(100) NOT NULL,
                english INT NOT NULL CHECK (english BETWEEN 0 AND 100),
                maths INT NOT NULL CHECK (maths BETWEEN 0 AND 100),
                science INT NOT NULL CHECK (science BETWEEN 0 AND 100),
                hindi INT NOT NULL CHECK (hindi BETWEEN 0 AND 100),
                computer INT NOT NULL CHECK (computer BETWEEN 0 AND 100)
    );

-- =====================================================
-- Sample Data (Optional)
-- Uncomment the following statements to insert sample data.
-- =====================================================

-- INSERT INTO students (id, name, english, maths, science, hindi, computer)
-- VALUES
-- (101, 'Shubham', 95, 90, 89, 86, 99),
-- (102, 'Rahul', 87, 92, 81, 79, 88),
-- (103, 'Priya', 91, 85, 94, 90, 93),
-- (104, 'Neha', 92, 94, 90, 89, 95),
-- (105, 'Monu', 76, 80, 72, 78, 81),
-- (106, 'Aman', 69, 74, 71, 68, 75);

-- =====================================================
-- Database setup completed successfully.
-- =====================================================