--Представим, что мы хотим выполнить несколько переводов в одной транзакции, но один из них должен быть отменён, а остальные — сохранены.
BEGIN;

-- Перевод 1: Alice -> Bob (успешно)
UPDATE accounts SET balance = balance - 100 WHERE name = 'Alice';
UPDATE accounts SET balance = balance + 100 WHERE name = 'Bob';
INSERT INTO transactions_log (from_account, to_account, amount) VALUES (1, 2, 100);

-- Создаём точку сохранения после первого перевода
SAVEPOINT sp1;

-- Перевод 2: Bob -> Alice (ошибка — откатим только его)
UPDATE accounts SET balance = balance - 50 WHERE name = 'Bob';
UPDATE accounts SET balance = balance + 50 WHERE name = 'Alice';
INSERT INTO transactions_log (from_account, to_account, amount) VALUES (2, 1, 50);

-- Проверим промежуточное состояние
SELECT * FROM accounts;

-- Откатываемся до точки sp1
ROLLBACK TO SAVEPOINT sp1;

-- Проверим состояние после отката до точки sp1
SELECT * FROM accounts;

-- Перевод 3: Alice -> Bob (ещё один перевод)
UPDATE accounts SET balance = balance - 30 WHERE name = 'Alice';
UPDATE accounts SET balance = balance + 30 WHERE name = 'Bob';
INSERT INTO transactions_log (from_account, to_account, amount) VALUES (1, 2, 30);

-- Удаляем точку сохранения
RELEASE SAVEPOINT sp1;

-- Фиксируем транзакцию
COMMIT;

-- Итоговое состояние
SELECT * FROM accounts;

---------------------------------------------------
--SAVEPOINT позволяет установить "промежуточные точки" в транзакции.
--ROLLBACK TO SAVEPOINT откатывает изменения после точки.
--RELEASE SAVEPOINT удаляет точку (необязательно, но хорошая практика).