drop database if exists crud;
drop table if exists teams;
create table teams(
team_id int not null auto_increment,
team_name varchar(25),
primary key (team_id)
);