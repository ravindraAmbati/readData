CREATE TABLE ProductPrices(
	id SERIAL PRIMARY KEY,
	prodId VARCHAR NOT NULL,
	price decimal,
	maxPrice decimal,
	minPrice decimal,
	avgPrice decimal,
	stdDevPrice decimal,
	varPrice decimal,
	timeStamp timestamp DEFAULT now()
);