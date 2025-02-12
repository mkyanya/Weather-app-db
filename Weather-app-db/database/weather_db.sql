DROP TABLE IF EXISTS weather, users;

CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password (hashed, not plain-text)
  salt varchar(256) NOT NULL		  -- Password Salt
);

CREATE TABLE weather(
    weather_id serial PRIMARY KEY,
    user_id int,
    zipcode int,
    name varchar(255),
    weather_date DATE,
    temp numeric(8, 4),
    feels_like numeric(8, 4),
    humidity int,
    description varchar(255),
    CONSTRAINT FK_weather_users FOREIGN KEY (user_id) REFERENCES users (id)

);
