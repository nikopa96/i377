CREATE SEQUENCE seq1 START WITH 1;

CREATE TABLE "ORDER" (
  id BIGINT NOT NULL PRIMARY KEY,
  order_number VARCHAR(255) NOT NULL,
  order_row_id int
);

CREATE TABLE MyOrder (
  id BIGINT NOT NULL PRIMARY KEY,
  order_number VARCHAR(255) NOT NULL,
  order_row_id int
);