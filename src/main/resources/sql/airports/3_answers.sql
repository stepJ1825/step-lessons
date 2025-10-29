--11. Найдите топ-5 аэропортов мира с наибольшим числом вылетов за последний месяц.
select r.departure_airport as rda, COUNT(*) as total_sum from flights f
join routes r on f.route_no = r.route_no
where f.actual_departure >= CURRENT_DATE - interval '1 month'
  and f.scheduled_departure <= CURRENT_DATE
group by rda
order by total_sum desc
limit 5;

--12. Для каждой модели самолёта рассчитайте среднюю заполняемость рейсов
--за последний квартал.
--12.1 заполняемость пассажиров в штуках
select a.model,
      (sum(c1)/count(c1))::int as avg_count from
(select f.route_no as rn, f.scheduled_departure as sd, COUNT(*) as c1 from flights f
join segments s on s.flight_id = f.flight_id
where f.actual_departure >= CURRENT_DATE - interval '3 month'
  and f.scheduled_departure <= CURRENT_DATE
group by f.route_no, f.scheduled_departure)
join routes r on r.route_no = rn
join airplanes a on r.airplane_code = a.airplane_code
group by a.model
order by a.model;

--12.2 заполняемость самолётов в % --TODO:
select a.model,
      (sum(in1.c1)/count(in1.c1))::int as avg_count ,
      count(in2.count) as seat_count
      from
((select f.route_no as rn, f.scheduled_departure as sd, COUNT(*) as c1 from flights f
join segments s on s.flight_id = f.flight_id
where f.actual_departure >= CURRENT_DATE - interval '3 month'
  and f.scheduled_departure <= CURRENT_DATE
group by f.route_no, f.scheduled_departure) as in1,
(select a.model, count(s.seat_no) from airplanes a
join seats s on s.airplane_code = a.airplane_code
group by a.model) as in2)
join routes r on r.route_no = in1.rn
join airplanes a on r.airplane_code = a.airplane_code
join seats s on s.airplane_code = a.airplane_code
group by a.model
order by a.model;


--13. Выведите топ-10 самых дорогих бронирований за всё время.
--14. Сколько билетов было продано ежедневно за последнюю неделю?
--15. Найдите аэропорты, в которые прибыло менее 5 рейсов за последнюю неделю.