create table schedule
(
    id       bigint       primary key auto_increment,
    passwords varchar(100) not null,
    personname varchar(500) not null,
    todo varchar(500) not null,
    createtime datetime not null,
    updatetime datetime not null

);