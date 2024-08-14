create table schedule
(
    id       bigint       primary key auto_increment,
    passwords varchar(100) not null,
    personname varchar(500) not null,
    todo varchar(500) not null

);

delete TABLE schedule ADD COLUMN createtime timestamp default now();
ALTER TABLE schedule ADD COLUMN updatetime datetime default now();