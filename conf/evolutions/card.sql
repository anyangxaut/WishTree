create table if not exists card (
    id int not null AUTO_INCREMENT primary key,
    name varchar(20) not null,
    summary varchar(100),
    thumbnail varchar(100),
    type int default 0
);