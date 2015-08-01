CREATE TABLE roles
(
  description text,
  role text NOT NULL,
  PRIMARY KEY (role)
)

CREATE SEQUENCE user_id_seq;
create table users
(
   id int not null DEFAULT nextval('user_id_seq'),
   username text,
   role text,
   PRIMARY KEY (id)
);