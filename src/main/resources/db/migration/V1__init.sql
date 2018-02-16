
create table deal(
deal_id int auto_increment not null,
from_currency_iso_code varchar(64) not null,
to_currency_iso_code varchar(64) not null,
deal_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
amount decimal(15,2)not null,
primary key(deal_id)
)engine=InnoDB;


create table deal_file(
id int not null auto_increment,
file_name varchar(256) not null unique,
time_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
primary key(id)
)engine=InnoDB;


create table valid_deal_file(

id int not null auto_increment,
file_name varchar(256) not null unique,
time_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
primary key(id),
foreign key(id) references deal_file(id)
)engine=InnoDB;


create table invalid_deal_file(

id int not null auto_increment,
file_name varchar(256) not null unique,
time_created TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
primary key(id),
foreign key(id) references deal_file(id)

)engine=InnoDB;