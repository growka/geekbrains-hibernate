BEGIN;

DROP TABLE IF EXISTS products CASCADE;
CREATE TABLE products (id bigserial PRIMARY KEY, title VARCHAR(255), price int);
INSERT INTO products (title, price) VALUES
('apple', 100),
('banana', 80),
('pear', 70),
('lemon', 40),
('mango', 90),
('melon', 50),
('orange', 60);

COMMIT;