DROP TABLE IF EXISTS cars;

CREATE SEQUENCE cars_car_id_seq;

CREATE TABLE cars
(
  "car_id"          Bigint DEFAULT nextval('cars_car_id_seq'),
  "license_plate"   Character Varying(12) NOT NULL,
  "type_id"         BIGINT,
  "production_year" INTEGER,
  "available"       BOOLEAN,
  "model_id"        BIGINT,
  CONSTRAINT "unique_cars_car_id" UNIQUE ("car_id"),
  FOREIGN KEY (type_id) REFERENCES comforts (comfort_id),
  FOREIGN KEY (model_id) REFERENCES models (model_id)
);
ALTER SEQUENCE cars_car_id_seq OWNED BY cars.car_id;
