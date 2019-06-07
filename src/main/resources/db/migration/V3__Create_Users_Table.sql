DROP TABLE IF EXISTS users;

CREATE SEQUENCE users_user_id_seq
  START WITH 100;

CREATE TABLE users
(
  "user_id"           Bigint DEFAULT nextval('users_user_id_seq'),
  "name"              Character Varying(128),
  "mail"              Character Varying(128) NOT NULL,
  "password"          Character Varying(128) NOT NULL,
  "credit_card"       Character Varying(19),
  "profile_bonus"     BOOLEAN,
  "discount"          Integer,
  "traveled_distance" Integer,
  "loyalty"           Integer,
  "birthday"          Date,
  "role_id"           Bigint,
  CONSTRAINT "unique_users_mail" UNIQUE (mail),
  CONSTRAINT "unique_users_user_id" UNIQUE (user_id),
  FOREIGN KEY (role_id) REFERENCES roles (role_id)
);
ALTER SEQUENCE users_user_id_seq OWNED BY users.user_id;
