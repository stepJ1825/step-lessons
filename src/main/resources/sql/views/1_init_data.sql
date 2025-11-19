-- 1. Создание тестовых таблиц
CREATE TABLE IF NOT EXISTS departments (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    location VARCHAR(100),
    budget DECIMAL(12,2)
);

CREATE TABLE IF NOT EXISTS employees (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    hire_date DATE,
    salary DECIMAL(10,2),
    department_id INTEGER REFERENCES departments(id),
    position VARCHAR(100),
    is_active BOOLEAN DEFAULT true
);

CREATE TABLE IF NOT EXISTS projects (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    start_date DATE,
    end_date DATE,
    budget DECIMAL(12,2),
    department_id INTEGER REFERENCES departments(id)
);

-- 2. Вставка тестовых данных
INSERT INTO departments (name, location, budget) VALUES
('IT', 'New York', 1500000.00),
('HR', 'Chicago', 300000.00),
('Finance', 'Boston', 800000.00),
('Marketing', 'San Francisco', 600000.00),
('Sales', 'Los Angeles', 700000.00);

INSERT INTO employees (first_name, last_name, email, hire_date, salary, department_id, position, is_active) VALUES
('John', 'Doe', 'john.doe@company.com', '2020-01-15', 85000.00, 1, 'Senior Developer', true),
('Jane', 'Smith', 'jane.smith@company.com', '2019-03-22', 92000.00, 1, 'Team Lead', true),
('Mike', 'Johnson', 'mike.j@company.com', '2021-07-10', 65000.00, 1, 'Junior Developer', true),
('Sarah', 'Williams', 'sarah.w@company.com', '2018-11-05', 78000.00, 2, 'HR Manager', true),
('Robert', 'Brown', 'robert.b@company.com', '2022-02-28', 72000.00, 2, 'Recruiter', true),
('Emily', 'Davis', 'emily.d@company.com', '2020-09-17', 88000.00, 3, 'Financial Analyst', true),
('David', 'Wilson', 'david.w@company.com', '2017-05-30', 120000.00, 3, 'Finance Director', true),
('Lisa', 'Miller', 'lisa.m@company.com', '2021-04-12', 75000.00, 4, 'Marketing Specialist', true),
('Tom', 'Taylor', 'tom.t@company.com', '2019-08-03', 95000.00, 5, 'Sales Manager', true),
('Anna', 'Anderson', 'anna.a@company.com', '2022-01-20', 68000.00, 5, 'Sales Representative', true),
('Chris', 'Thomas', 'chris.t@company.com', '2023-03-15', 60000.00, 1, 'Intern', true),
('Mark', 'Jackson', 'mark.j@company.com', '2020-12-01', 82000.00, 4, 'Marketing Manager', true);

INSERT INTO projects (name, description, start_date, end_date, budget, department_id) VALUES
('Website Redesign', 'Modernize company website', '2023-01-01', '2023-06-30', 150000.00, 1),
('Mobile App', 'Develop iOS and Android apps', '2023-02-15', '2023-12-31', 250000.00, 1),
('HR System Upgrade', 'Implement new HR software', '2023-03-01', '2023-09-30', 80000.00, 2),
('Annual Audit', 'Yearly financial audit', '2023-01-15', '2023-03-31', 120000.00, 3),
('Marketing Campaign', 'Q3 digital marketing campaign', '2023-07-01', '2023-09-30', 95000.00, 4),
('Sales Training', 'New sales methodology training', '2023-04-01', '2023-05-31', 45000.00, 5);


