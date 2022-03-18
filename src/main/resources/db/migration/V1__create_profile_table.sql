create table profile (
    id serial primary key,
    email varchar(100) unique,
    password varchar(100),
    name varchar
);