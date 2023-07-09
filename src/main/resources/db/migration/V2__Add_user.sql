
use my_db;
insert into usr (id, username, password, active) values (1, 'abuser', '$2a$08$Ia15JrI2G1eDMzYkWQIlIO2j2yTp44kE9fjW92KBX5PmVsZoM6OJK', true);
insert into user_role (user_id, roles) values (1, 'USER'), (1, 'ADMIN');
