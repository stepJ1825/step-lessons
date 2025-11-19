--Функция перевода с проверкой баланса
CREATE OR REPLACE FUNCTION transfer_money(
    from_acc_id INT,
    to_acc_id INT,
    amount DECIMAL
) RETURNS VOID AS $$
BEGIN
    -- Проверка, что у отправителя достаточно средств
    IF (SELECT balance FROM accounts WHERE id = from_acc_id FOR UPDATE)
			< amount
    THEN RAISE EXCEPTION 'Insufficient funds for account %', from_acc_id;
    END IF;

    -- Выполняем перевод
    UPDATE accounts SET balance = balance - amount WHERE id = from_acc_id;
    UPDATE accounts SET balance = balance + amount WHERE id = to_acc_id;

    -- Логируем транзакцию
    INSERT INTO transactions_log (from_account, to_account, amount)
    VALUES (from_acc_id, to_acc_id, amount);
END;
$$ LANGUAGE plpgsql;

-------------------------------------------------------------------------------------
-- Запускаем транзакцию
BEGIN;
SELECT transfer_money(1,2,100);
COMMIT;
-- Смотрим результат
select * from accounts a

-------------------------------------------------------------------------------------

--Что произошло:
--Перевод 1: Alice -> Bob (50 рублей) — успешно.
--Точка sp1 установлена.
--Перевод 2: Bob -> Alice (2000 рублей) — ошибка, т.к. недостаточно средств.
--Откат до sp1 — перевод 2 отменён.
--Перевод 3: Alice -> Bob (30 рублей) — успешно.
--Транзакция зафиксирована.


--------------------------------------------------------------------------------------
-- DROP FUNCTION public.try_transfer_with_savepoint(int4, int4, numeric);

CREATE OR REPLACE FUNCTION public.try_transfer_with_savepoint(from_acc_id integer, to_acc_id integer, amount numeric)
 RETURNS text
 LANGUAGE plpgsql
AS $function$
BEGIN
    PERFORM transfer_money(from_acc_id, to_acc_id, amount);
    RETURN 'OK';
EXCEPTION
    WHEN OTHERS THEN
        RETURN 'ERROR: ' || SQLERRM;
END;
$function$
;
