/**
 * CREATE Script for sample data
 */

-- Create some restaurants

insert into restaurant (id,name,opening_time,closing_time) values (1,'restaurant - 1','10:30:00.000','20:00:00.000');
insert into restaurant (id,name,opening_time,closing_time) values (2,'new restaurant - 2',null,null);
insert into restaurant (id,name,opening_time,closing_time) values (3,'other restaurant - 3','09:15:00.000','19:00:00.000');

-- create restaurant tables or slots
insert into restaurant_table (id,capacity,restaurant_id) values (1,7,1);
insert into restaurant_table (id,capacity,restaurant_id) values (2,5,1);
insert into restaurant_table (id,capacity,restaurant_id) values (3,4,1);
insert into restaurant_table (id,capacity,restaurant_id) values (4,5,1);

insert into restaurant_table (id,capacity,restaurant_id) values (5,5,2);
insert into restaurant_table (id,capacity,restaurant_id) values (6,3,2);
insert into restaurant_table (id,capacity,restaurant_id) values (7,4,2);
insert into restaurant_table (id,capacity,restaurant_id) values (8,5,2);
insert into restaurant_table (id,capacity,restaurant_id) values (9,5,2);
insert into restaurant_table (id,capacity,restaurant_id) values (10,5,2);

insert into restaurant_table (id,capacity,restaurant_id) values (11,5,3);
insert into restaurant_table (id,capacity,restaurant_id) values (12,3,3);
insert into restaurant_table (id,capacity,restaurant_id) values (13,4,3);
insert into restaurant_table (id,capacity,restaurant_id) values (14,5,3);
insert into restaurant_table (id,capacity,restaurant_id) values (15,5,3);

insert into restaurant_table (id,capacity,restaurant_id) values (16,5,3);
insert into restaurant_table (id,capacity,restaurant_id) values (17,3,3);
insert into restaurant_table (id,capacity,restaurant_id) values (18,4,3);
insert into restaurant_table (id,capacity,restaurant_id) values (19,5,3);
insert into restaurant_table (id,capacity,restaurant_id) values (20,5,3);

-- create some reservations
insert into reservations (id,start_date,end_date,party_size,restaurant_table_id) values (1,'2021-10-11 13:30:00.000','2021-10-11 15:30:00.000',2,4);
insert into reservations (id,start_date,end_date,party_size,restaurant_table_id) values (2,'2021-10-11 18:00:00.000','2021-10-11 19:30:00.000',5,4);
insert into reservations (id,start_date,end_date,party_size,restaurant_table_id) values (3,'2021-10-11 19:15:00.000','2021-10-11 20:15:00.000',4,4);

insert into reservations (id,start_date,end_date,party_size,restaurant_table_id) values (4,'2021-10-11 13:30:00.000','2021-10-11 15:30:00.000',2,8);
insert into reservations (id,start_date,end_date,party_size,restaurant_table_id) values (5,'2021-10-11 18:00:00.000','2021-10-11 19:30:00.000',5,8);
insert into reservations (id,start_date,end_date,party_size,restaurant_table_id) values (6,'2021-10-11 19:15:00.000','2021-10-11 20:15:00.000',4,8);

