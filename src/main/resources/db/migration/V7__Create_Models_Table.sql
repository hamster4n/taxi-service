DROP TABLE IF EXISTS models;

CREATE TABLE models
(
  "model_id" SERIAL PRIMARY KEY,
  "name"     Character Varying(32)  NOT NULL,
  "photo"    Character Varying(128) NOT NULL,
  CONSTRAINT "unique_models_model_id" UNIQUE (model_id)
);
