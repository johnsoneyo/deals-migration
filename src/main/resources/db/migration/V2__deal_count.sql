/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  johnson3yo
 * Created: Feb 10, 2018
 */

create table deal_count(
deal_count_id int not null auto_increment,
currency_iso_code varchar(64) not null,
deal_count int,
primary key(deal_count_id)

)engine=InnoDB;