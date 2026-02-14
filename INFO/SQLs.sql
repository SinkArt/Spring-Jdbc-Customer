
-- БАЗА ДАНИХ
-- Можливість створення БД з метою уникнення некваліфікованих
-- дій, краще залишити за розробником.
-- Тому такий функціонал у додатку не прописуємо.
-- Можемо створити БД через візуальний інструмент.
CREATE DATABASE demo_db;


-- ТАБЛИЦІ
-- Можливість створення таблиць БД, з метою уникнення некваліфікованих
-- дій, краще залишити за розробником.
-- Тому такий функціонал у додатку не прописуємо.
-- Попередньо, необхідно спроектувати таблиці та їх зв'язки,
-- на основі сутностей реального світу.
-- Можемо створити таблиці БД через візуальний інструмент.

CREATE TABLE IF NOT EXISTS users (
    PRIMARY KEY (id),
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name  VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS products (
    PRIMARY KEY (id),
    id      BIGINT       NOT NULL AUTO_INCREMENT,
    name    VARCHAR(255) NOT NULL,
    measure VARCHAR(255) NOT NULL,
    quota   DECIMAL(5,2) NOT NULL,
    price   DECIMAL(6,2) NOT NULL
);





