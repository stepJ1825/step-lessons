CREATE OR REPLACE VIEW the_best_passenger_vw AS
select t.passenger_name, count(f.flight_id) from tickets t
join boarding_passes bp using (ticket_no)
join flights f using (flight_id)
where f.scheduled_departure >= CURRENT_DATE - interval '3 year'
	and f.scheduled_departure <= CURRENT_DATE
group by t.passenger_name
having count(f.flight_id) > 5
order by count(f.flight_id) desc

select * from the_best_passenger
------------------------------------------------------------------
CREATE MATERIALIZED VIEW IF NOT EXISTS the_best_passenger_mv_vw as
select t.passenger_name, count(f.flight_id) from tickets t
join boarding_passes bp using (ticket_no)
join flights f using (flight_id)
where f.scheduled_departure >= CURRENT_DATE - interval '3 year'
	and f.scheduled_departure <= CURRENT_DATE
group by t.passenger_name
having count(f.flight_id) > 5
order by count(f.flight_id) desc

select * from the_best_passenger_mv_vw

