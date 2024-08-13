create table schedule
(
    id       bigint       primary key auto_increment,
    passwords varchar(100) not null,
    personid varchar(500) not null,
    createtime DATETIME not null ,
    updatetime TIMESTAMP not null default current_timestamp,
    todo varchar(500) not null

);

create table person
(
    id       bigint       primary key auto_increment,
    personname varchar(100) not null,
    email varchar(500) not null,
    createtime DATETIME not null ,
    updatetime TIMESTAMP not null default current_timestamp

);

ALTER table person change column id personid bigint;
ALTER table schedule modify personid bigint;

ALTER TABLE schedule ADD CONSTRAINT schedule_fk_id FOREIGN KEY(personid) REFERENCES person(personid);