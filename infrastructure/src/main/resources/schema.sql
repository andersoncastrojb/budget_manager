
CREATE DATABASE IF NOT EXISTS budget_manager;
USE budget_manager;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(512),
    email VARCHAR(512),
    password VARCHAR(512)
);


CREATE TABLE accounts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_user INT,
    name VARCHAR(512),
    type VARCHAR(64),
    balance BIGINT,
    FOREIGN KEY (id_user) REFERENCES users(id)
);


CREATE TABLE incomes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_account BIGINT,
    type VARCHAR(64),
    value BIGINT,
    description VARCHAR(512),
    date TIMESTAMP,
    FOREIGN KEY (id_account) REFERENCES accounts(id)
);


CREATE TABLE loans (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_user INT,
    lender VARCHAR(512),
    amount BIGINT,
    loan_date TIMESTAMP,
    limit_to_pay_date TIMESTAMP,
    status VARCHAR(64),
    FOREIGN KEY (id_user) REFERENCES users(id)
);


CREATE TABLE fixed_expenses (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_user INT,
    amount BIGINT,
    description VARCHAR(512),
    frequency VARCHAR(64),
    start_date TIMESTAMP,
    end_date TIMESTAMP,
    FOREIGN KEY (id_user) REFERENCES users(id)
);


CREATE TABLE monthly_balances (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_user INT,
    month INT,
    year INT,
    total_incomes BIGINT,
    total_expenses BIGINT,
    loans BIGINT,
    final_balance BIGINT,
    FOREIGN KEY (id_user) REFERENCES users(id)
);
