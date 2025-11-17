-- Подключаемся к БД (например, через psql)
-- \c your_database_name

-- Создаём таблицы
CREATE TABLE accounts (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    balance DECIMAL(10, 2) NOT NULL
);

CREATE TABLE transactions_log (
    id SERIAL PRIMARY KEY,
    from_account INT,
    to_account INT,
    amount DECIMAL(10, 2),
    transaction_date TIMESTAMP DEFAULT NOW()
);

INSERT INTO accounts (name, balance) VALUES
    ('Alice', 1000.00),
    ('Bob', 500.00);

SELECT * FROM accounts;