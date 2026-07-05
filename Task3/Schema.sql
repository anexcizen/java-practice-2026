create table product (
	id serial unique not null,
	name char(20) not null,
	price integer check(price > -1)
);
insert into product (name, price) values ('Киткат', 80);
insert into product (name, price) values ('Несквик', 75);
insert into product (name, price) values ('Яблочный сок', 130);
insert into product (name, price) values ('Мороженое', 160);
insert into product (name, price) values ('Мармеладки', 100);
SELECT * FROM product
