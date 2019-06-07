DROP TABLE IF EXISTS roles;

CREATE TABLE roles
(
  "role_id"     SERIAL PRIMARY KEY,
  "title"       Character Varying(10)  NOT NULL,
  "description" Character Varying(128) NOT NULL,
  CONSTRAINT "unique_roles_role_id" UNIQUE (role_id)
);
