--1. Выведите имена и год первого появления всех супергероев из вселенной
--'marvel', у которых цвет глаз 'Blue Eyes'.
select s."name" ,s."year" from superheroes s
where s.eye = 'Blue Eyes' and s.universe like 'marvel'

--2. Найдите общую сумму (SUM) цен всех продуктов, тип которых 'Онлайн-курс'
--(type_name в product_types).
select sum(p.price)from products p
join product_types pt on p.type_id = pt.id
where pt.type_name = 'Онлайн-курс'

--3. Используя JOIN, выведите название продукта, тип продукта (из таблицы product_types)
--и его цену для всех продуктов, цена которых больше 20000.
select p."name" , pt.type_name  from products p
join product_types pt on p.type_id = pt.id
where p.price >= 20000

--4. Для каждого типа продукта (type_name) посчитайте количество продуктов,
--относящихся к этому типу. Выведите название типа и количество.
select pt.type_name, count(p.type_id) from product_types pt
join products p on pt.id = p.type_id
group by pt.type_name

--5. Найдите имя клиента и дату заказа для всех заказов, сделанных клиентом
--по имени 'Иван Петров'.
select c."name", o.order_date   from customers c
join orders o on o.customer_id = c.id
where c."name" = 'Иван Петров'

--6. Выведите имена супергероев, у которых количество появлений
--(appearances) больше 50 и которые являются 'Good Characters'.
select s.name from superheroes s
where s.align = 'Good Characters' and s.appearances > 50

--7. Найдите продукт с самой высокой ценой. Выведите его название и цену.
--(Подсказка: используйте ORDER BY и LIMIT).
select p."name" , p.price  from products p
order by p.price desc
limit 1

--8. Используя подзапрос, найдите всех клиентов (customers),
--у которых нет ни одного заказа (orders).
select * from customers c
where not exists (select * from orders o where o.customer_id = c.id)

--9. Посчитайте общее количество заказов (orders) для каждого клиента.
--Выведите имя клиента и количество заказов. Включите в результат
--даже тех клиентов, у которых заказов не было (в этом случае
--количество должно быть 0). (Подсказка: используйте
--LEFT JOIN и COALESCE или ISNULL).
select c."name", count(o.id) from customers c
left join orders o on c.id = o.customer_id
group by c."name";
--COALESCE(count(o.id),0)

--10. Найдите среднее количество появлений (AVG(appearances))
--супергероев по полу (gender) в каждой вселенной (universe).
--Выведите вселенную, пол и среднее количество появлений.
select s.universe, s.gender, round(avg(s.appearances),2) as avg
from superheroes s
group by s.universe, gender
order by s.universe;


--11. Найдите среднюю цену продукта для каждого типа продукта,
--но только для тех типов, у которых средняя цена выше 10000.
--Выведите название типа и среднюю цену.
select pt.type_name , avg(p.price) as avg_price from products p
join product_types pt on p.type_id = pt.id
group by pt.type_name
having avg(p.price) > 10000;

--12. Выведите имена клиентов, у которых было больше одного заказа.
select c.name, count(o.id) from customers c
join orders o on c.id = o.customer_id
group by c."name"
having count(o.id) > 1