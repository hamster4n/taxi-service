DROP TABLE IF EXISTS orders;

CREATE SEQUENCE orders_order_id_seq
  START WITH 100;

CREATE TABLE orders
(
  "order_id"           Bigint DEFAULT nextval('orders_order_id_seq'),
  "date"               Date,
  "start_description"  VARCHAR(1024),
  "finish_description" VARCHAR(1024),
  "distance"           INTEGER,
  "car_id"             Bigint,
  "base_cost"          INTEGER,
  "final_cost"         INTEGER,
  "loyalty_cost"       INTEGER,
  "discount_cost"      INTEGER,
  "birthday_cost"      INTEGER,
  "auto_feed"          INTEGER,
  "comfort_cost"       INTEGER,
  "user_id"            Bigint,
  CONSTRAINT "unique_orders_order_id" UNIQUE (order_id),
  FOREIGN KEY (car_id) REFERENCES cars (car_id),
  FOREIGN KEY (user_id) REFERENCES users (user_id)
);
ALTER SEQUENCE orders_order_id_seq OWNED BY orders.order_id;