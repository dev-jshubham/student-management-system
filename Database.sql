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
                                        english INT NOT NULL,
                                        maths INT NOT NULL,
                                        science INT NOT NULL,
                                        hindi INT NOT NULL,
                                        computer INT NOT NULL
    );

-- =====================================================
-- Database setup completed successfully.
-- =====================================================