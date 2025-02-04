CREATE TABLE IF NOT EXISTS VEHICLE (
  id bigint(20) NOT NULL AUTO_INCREMENT,
  registration_number varchar(100) DEFAULT NULL,
  mark varchar(50) DEFAULT NULL,
  sub_brand varchar(50) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;