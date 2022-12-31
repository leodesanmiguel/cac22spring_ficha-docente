DROP DATABASE IF EXISTS ficha_docente_test;

CREATE DATABASE  IF NOT EXISTS ficha_docente_test;
USE ficha_docente_test;

CREATE TABLE user (
  id_user bigint NOT NULL AUTO_INCREMENT,
  email varchar(50) NOT NULL,
  lastname varchar(40) NOT NULL,
  name varchar(40) NOT NULL,
  start_date date DEFAULT NULL,
  user_key varchar(20) DEFAULT NULL,
  level int DEFAULT NULL,
  user_name varchar(20) NOT NULL,
  PRIMARY KEY (id_user),
  UNIQUE KEY UKlqjrcobrh9jc8wpcar64q1bfh (user_name)
) ENGINE=InnoDB AUTO_INCREMENT=20026 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE staff (
  id_staff bigint NOT NULL AUTO_INCREMENT,
  birth_date date DEFAULT NULL,
  cuit bigint NOT NULL,
  dni int NOT NULL,
  lastname varchar(40) NOT NULL,
  name varchar(40) NOT NULL,
  sex char(1) DEFAULT NULL,
  PRIMARY KEY (id_staff),
  UNIQUE KEY UKtm5siwenr285bkm7gtk7qc4sw (dni,cuit)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE degree (
  id_degree bigint NOT NULL AUTO_INCREMENT,
  institution varchar(70) DEFAULT NULL,
  name_degree varchar(70) NOT NULL,
  registry_n varchar(15) DEFAULT NULL,
  senior_year int DEFAULT NULL,
  type_degree char(1) DEFAULT NULL,
  id_staff_fk bigint NOT NULL,
  PRIMARY KEY (id_degree),
  KEY FKp7d6vf0ubs8btdkw09u5rgeas (id_staff_fk),
  CONSTRAINT FKp7d6vf0ubs8btdkw09u5rgeas FOREIGN KEY (id_staff_fk) REFERENCES staff (id_staff)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE district (
  id_district bigint NOT NULL AUTO_INCREMENT,
  name_district varchar(50) NOT NULL,
  province varchar(25) NOT NULL,
  PRIMARY KEY (id_district)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE email (
  id_email bigint NOT NULL AUTO_INCREMENT,
  mobile varchar(40) NOT NULL,
  tel_alternative varchar(40) NOT NULL,
  id_staff_fk bigint NOT NULL,
  PRIMARY KEY (id_email),
  KEY FKs9c82c0kvv3l558p6chgxkgc6 (id_staff_fk),
  CONSTRAINT FKs9c82c0kvv3l558p6chgxkgc6 FOREIGN KEY (id_staff_fk) REFERENCES staff (id_staff)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE locality (
  id_locality bigint NOT NULL AUTO_INCREMENT,
  name_locality varchar(50) DEFAULT NULL,
  post_zip int NOT NULL,
  id_district_fk bigint NOT NULL,
  PRIMARY KEY (id_locality),
  KEY FKqi1lnjsxv5sqxa1l3y6vaunrd (id_district_fk),
  CONSTRAINT FKqi1lnjsxv5sqxa1l3y6vaunrd FOREIGN KEY (id_district_fk) REFERENCES district (id_district)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE phone (
  id_phone bigint NOT NULL AUTO_INCREMENT,
  mobile varchar(20) NOT NULL,
  tel_alternative varchar(20) NOT NULL,
  id_staff_fk bigint NOT NULL,
  PRIMARY KEY (id_phone),
  KEY FKor4q20l70oxwadw6sj3bw21il (id_staff_fk),
  CONSTRAINT FKor4q20l70oxwadw6sj3bw21il FOREIGN KEY (id_staff_fk) REFERENCES staff (id_staff)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE address (
  id_address bigint NOT NULL AUTO_INCREMENT,
  street_nro varchar(50) NOT NULL,
  id_location bigint NOT NULL,
  id_staff_fk bigint NOT NULL,
  PRIMARY KEY (id_address),
  KEY FKox7gqq7oovpi9q41mrd69fmj5 (id_location),
  KEY FKielglmmfdkwggm47fsoe4atp1 (id_staff_fk),
  CONSTRAINT FKielglmmfdkwggm47fsoe4atp1 FOREIGN KEY (id_staff_fk) REFERENCES staff (id_staff),
  CONSTRAINT FKox7gqq7oovpi9q41mrd69fmj5 FOREIGN KEY (id_location) REFERENCES locality (id_locality)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
