--16. Найдите рейсы, на которых летел пассажир с фамилией «Sunita»
--(учтите, что ФИО в верхнем регистре).
select f.* from flights f
join segments s on f.flight_id = s.flight_id
join tickets t on s.ticket_no = t.ticket_no
where LOWER(t.passenger_name) like LOWER('SMITH%')
	or LOWER(t.passenger_name) like LOWER('%SMITH')
limit 20;


--17. Выведите аэропорты, из которых не вылетало ни одного рейса за последнюю неделю.
select distinct ad.airport_code from airports_data ad
where ad.airport_code NOT IN
      (select distinct r.departure_airport from routes r
       join flights f on f.route_no = r.route_no
       where f.actual_departure >= CURRENT_DATE - interval '1 week' and
       f.actual_departure <= CURRENT_DATE)

--18. Используя CTE, рассчитайте ежедневную выручку за последний месяц
--и покажите дни с выручкой выше средней.
with days as (
   select DATE(b.book_date) as "date",
          sum(b.total_amount) as total_amount
   from bookings b
   where b.book_date >= CURRENT_DATE - interval '1 month'
      and b.book_date <= CURRENT_DATE
   group by "date"
), avg_per_day as (select avg(total_amount) as avg_value from days)
select * from days d, avg_per_day apd
where d.total_amount > apd.avg_value
order by d."date"
--18*. через подзапросы
select DATE(b.book_date) as "date", SUM(b.total_amount) as sum_total_amount
from bookings b
where b.book_date >= CURRENT_DATE - interval '1 month'
      and b.book_date <= CURRENT_DATE
group by "date"
having SUM(b.total_amount) >
        (select AVG(daily.sum_amount) from
                (select DATE(b.book_date) as "date",
                        sum(b.total_amount) as sum_amount
                from bookings b
                where b.book_date >= CURRENT_DATE - interval '1 month'
                      and b.book_date <= CURRENT_DATE
                group by "date") as daily)
order by "date";

--19. Найдите рейсы с дублирующимися маршрутами и временем вылета
--(возможная ошибка данных).
select f.route_no, count(f.actual_departure) from flights f
group by f.route_no, f.actual_departure
having count(f.actual_departure) > 1


--20. Определите модели самолётов, которые никогда не выполняли рейсы в Европу
--(подсказка: используйте страны или континенты через внешний источник или признаки
-- — но в рамках БД можно ограничиться известными аэропортами, например, SCL, KJA).
select ad.airplane_code, ad.model->>'en' as model from airplanes_data ad
where ad.airplane_code in
(select r.airplane_code  from routes r
  where r.arrival_airport not IN
  (select ad.airport_code from airports_data ad
  where UPPER(ad.timezone)  like 'EUROPE%'))