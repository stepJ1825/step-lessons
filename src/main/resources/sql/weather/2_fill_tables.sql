-- Вставка language
INSERT INTO language (id, language_name) VALUES
    (0, 'ENG'),
    (1, 'BEL'),
    (2, 'RUS'),
    (3, 'ESP'),
    (4, 'ITA');

-- Вставка погодных условий
INSERT INTO precipitation (id, precipitation_name) VALUES
    (0, 'RAIN'),
    (1, 'SNOW'),
    (2, 'NO');

INSERT INTO weather.precipitation (id,precipitation_name) VALUES
	 (0,'RAIN'),
	 (1,'SNOW'),
	 (2,'NO');

INSERT INTO weather.my_region ("name",square,citizen_type) VALUES
	 ('minsk region',1234.22,'farmers'),
	 ('grodno region',500.22,'workers');

INSERT INTO weather.weather (my_region,"date",temperature,precipitation) VALUES
	 (1,'2025-10-22',5.00,2);




