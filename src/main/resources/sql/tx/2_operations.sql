--Предположим, Alice переводит Bob 200 рублей. Это должно включать:
--
--Уменьшение баланса Alice.
--Увеличение баланса Bob.
--Запись в лог транзакций.

BEGIN;

-- Уменьшаем баланс Alice
UPDATE accounts SET balance = balance - 200 WHERE name = 'Alice';

-- Увеличиваем баланс Bob
UPDATE accounts SET balance = balance + 200 WHERE name = 'Bob';

-- Логируем транзакцию
INSERT INTO transactions_log (from_account, to_account, amount)
VALUES (1, 2, 200);

-- Проверим промежуточное состояние (внутри транзакции)
SELECT * FROM accounts;

-- Если всё ок, фиксируем транзакцию
COMMIT;

-- Посмотрим итог
SELECT * FROM accounts;

---------------------------------------------------------------------
---------------------------------------------------------------------

--Теперь предположим, что во время транзакции произошла ошибка (например, недостаточно средств), и мы хотим откатить изменения.

BEGIN;

-- Попытка перевести больше, чем есть
UPDATE accounts SET balance = balance - 1900 WHERE name = 'Bob';

-- Увеличиваем баланс Alice
UPDATE accounts SET balance = balance + 1900 WHERE name = 'Alice';

-- Проверим промежуточное состояние
SELECT * FROM accounts;

-- Осознали, что ошибка — откатываем транзакцию
ROLLBACK;

-- Посмотрим состояние после отката
SELECT * FROM accounts;