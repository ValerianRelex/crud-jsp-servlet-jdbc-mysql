CREATE DATABASE keeper;
USE keeper;

create table flowers (
                       id  int NOT NULL PRIMARY KEY AUTO_INCREMENT,
                       variety varchar(120) NOT NULL,
                       alias varchar(120),
                       last_water_date DATE,
                       next_water_date DATE,
                       set_interval int
);