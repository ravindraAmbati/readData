CREATE TABLE product(
	id SERIAL PRIMARY KEY,
	prodId VARCHAR NOT NULL,
	name VARCHAR,
	quantity VARCHAR,
	price decimal,
	timeStamp timestamp DEFAULT now()
);