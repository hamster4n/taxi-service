DROP TABLE IF EXISTS comforts;

CREATE TABLE comforts
(
  "comfort_id"  SERIAL PRIMARY KEY,
  "title"       Character Varying(10)  NOT NULL,
  "description" Character Varying(128) NOT NULL,
  CONSTRAINT "unique_comforts_comfort_id" UNIQUE (comfort_id)
);
