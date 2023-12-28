drop database if exists humans_friends;

-- 7. В подключенном MySQL репозитории создать базу данных “Друзья человека”

create database humans_friends;

-- 8. Создать таблицы с иерархией из диаграммы в БД

use humans_friends;

create table animals_class
(
	id int auto_increment primary key,
    class_name varchar(20)
);

insert into animals_class (class_name)
values ('Домашние'),
('Вьючные'); 

create table home_animals
(
	id int auto_increment primary key,
    type_name varchar(20),
    class_id int,
    foreign key (class_id) references animals_class (id) on delete cascade on update cascade
);

insert into home_animals (type_name, class_id)
values ('Кошка/Кот', 1),
('Собака', 1),
('Хомяк', 1);

create table packed_animals
(
	id int auto_increment primary key,
    type_name varchar(20),
    class_id int,
    foreign key (class_id) references animals_class (id) on delete cascade on update cascade
);

insert into packed_animals (type_name, class_id)
values ('Лошадь', 2),
('Осел', 2),
('Верблюд', 2);

-- 9. Заполнить низкоуровневые таблицы именами(животных), командами которые они выполняют и датами рождения

create table cats
(
	id int auto_increment primary key,
	name varchar(20),
    birthday date,
    commands varchar(30),
    type_id int,
    foreign key (type_id) references home_animals (id) on delete cascade on update cascade
);

insert into cats (name, birthday, commands, type_id)
values ('Вжик', '2016-01-01', 'Ко мне', 1),
('Рыжик', '2012-01-01', 'Спать', 1),
('Филя', '2020-01-01', 'Кушать', 1);

create table dogs
(
	id int auto_increment primary key,
	name varchar(20),
    birthday date,
    commands varchar(30),
    type_id int,
    foreign key (type_id) references home_animals (id) on delete cascade on update cascade
);

insert into dogs (name, birthday, commands, type_id)
values ('Джесси', '2013-01-01', 'Ко мне, Лежать, Лапу, Фу', 2),
('Байден', '2021-01-01', 'Сидеть, Лежать, Голос', 2),
('Боня', '2018-01-01', 'Голос, Место, К ноге', 2);

create table hamsters
(
	id int auto_increment primary key,
	name varchar(20),
    birthday date,
    commands varchar(30),
    type_id int,
    foreign key (type_id) references home_animals (id) on delete cascade on update cascade
);

insert into hamsters (name, birthday, commands, type_id)
values ('Шизик', '2022-01-01', null, 3),
('Пухляш', '2023-01-01', null, 3),
('Чуня', '2020-01-01', null, 3);

create table horses
(
	id int auto_increment primary key,
	name varchar(20),
    birthday date,
    commands varchar(30),
    type_id int,
    foreign key (type_id) references home_animals (id) on delete cascade on update cascade
);

insert into horses (name, birthday, commands, type_id)
values ('Платва', '2013-01-01', 'Галопом, Рысью, Кушать', 1),
('Газель', '2016-01-01', 'Рысью, Стоп, Бррр', 1),
('Гром', '2022-01-01', 'Галопом, Рысью, Кушать, Бррр', 1);

create table donkeys
(
	id int auto_increment primary key,
	name varchar(20),
    birthday date,
    commands varchar(30),
    type_id int,
    foreign key (type_id) references home_animals (id) on delete cascade on update cascade
);

insert into donkeys (name, birthday, commands, type_id)
values ('Язва', '2020-01-01', null, 2),
('Грузный', '2023-01-01', null, 2),
('Медляш', '2022-01-01', null, 2);

create table camels
(
	id int auto_increment primary key,
	name varchar(20),
    birthday date,
    commands varchar(30),
    type_id int,
    foreign key (type_id) references home_animals (id) on delete cascade on update cascade
);

insert into camels (name, birthday, commands, type_id)
values ('Диего', '2010-01-01', 'Не плюй', 3),
('Буря', '2022-01-01', 'Ко мне', 3),
('Скала', '2020-01-01', 'Стоп', 3);

-- 10. Удалив из таблицы верблюдов, т.к. верблюдов решили перевезти в другой питомник на зимовку. 
-- Объединить таблицы лошади, и ослы в одну таблицу.

delete from camels;

select * from horses
union
select * from donkeys;

-- 11. Создать новую таблицу “молодые животные” в которую попадут все животные старше 1 года, 
-- но младше 3 лет и в отдельном столбце с точностью до месяца подсчитать возраст животных в новой таблице

create table tmp
select *, 'Кошка/Кот' as types from cats
union 
select *, 'Собака' as types from dogs
union 
select *, 'Хомяк' as types from hamsters
union
select *, 'Лошадь' as types from horses
union 
select *, 'Осел' as types from donkeys;

create table young_animals
select name, birthday, commands, types, timestampdiff(month, birthday, curdate()) as age_in_months
from tmp 
where birthday between adddate(curdate(), interval -3 year) and adddate(curdate(), interval -1 year);

select * from young_animals;

-- 12. Объединить все таблицы в одну, при этом сохраняя поля, 
-- указывающие на прошлую принадлежность к старым таблицам.

select h.name, h.birthday, h.commands, pa.type_name, yu.age_in_months
from horses as h
left join young_animals as yu on yu.name = h.name
left join packed_animals as pa on pa.id = h.type_id
union 
select d.name, d.birthday, d.commands, pa.type_name, yu.age_in_months
from donkeys as d 
left join young_animals as yu on yu.name = d.name
left join packed_animals as pa on pa.id = d.type_id
union
select c.name, c.birthday, c.commands, ha.type_name, yu.age_in_months 
from cats as c
left join young_animals as yu on yu.name = c.name
left join home_animals as ha on ha.id = c.type_id
union
select d.name, d.birthday, d.commands, ha.type_name, yu.age_in_months 
from dogs as d
left join young_animals as yu on yu.name = d.name
left join home_animals as ha on ha.id = d.type_id
union
select hm.name, hm.birthday, hm.commands, ha.type_name, yu.age_in_months 
from hamsters as hm
left join young_animals as yu on yu.name = hm.name
left join home_animals as ha on ha.id = hm.type_id;