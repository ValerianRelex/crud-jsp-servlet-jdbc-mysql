CREATE DATABASE keeper;
USE keeper;

create table flowers (
                       id  int(3) NOT NULL AUTO_INCREMENT,
                       variety varchar(120) NOT NULL,
                       alias varchar(120),
                       height int,
                       price int,
                       PRIMARY KEY (id)
);