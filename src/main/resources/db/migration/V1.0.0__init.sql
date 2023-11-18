create schema if not exists warehousedb;

create TABLE IF NOT EXISTS warehousedb.warehouse (
    id uuid NOT NULL DEFAULT random_uuid(),
    version numeric NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    name varchar(56) NOT NULL,
    text varchar(200),
    latitude varchar(200),
    longitude varchar(200),
    type varchar NOT NULL,
    PRIMARY KEY(id)
    );

create TABLE IF NOT EXISTS warehousedb.locator (
    id uuid NOT NULL DEFAULT random_uuid(),
    version numeric NOT NULL,
    created_at timestamp NOT NULL,
    updated_at timestamp NOT NULL,
    name varchar(50),
    warehouse_id uuid NOT NULL,
    aisle numeric,
    level numeric,
    type varchar(30),
    PRIMARY KEY(id),
    FOREIGN KEY (warehouse_id)
    REFERENCES warehouse(id)
    );

insert into warehousedb.warehouse values ('6d62d909-f957-430e-8689-b5129c0bb75e', 1, current_timestamp, current_timestamp,'Antifragile', 'Antifragile - Things that gains from disorder. By Nassim Nicholas Taleb', null, null, 'REGULAR');
insert into warehousedb.warehouse values ('a0a4f044-b040-410d-8ead-4de0446aec7e', 1, current_timestamp, current_timestamp,'Sapiens', 'Sapiens - A brief history of mankind. By Yuval Noah Harari', null, null, 'REGULAR');

insert into warehousedb.locator values ('00000000-b5c6-4896-987c-f30f3678f601', 1, current_timestamp, current_timestamp,'Antifragile', '6d62d909-f957-430e-8689-b5129c0bb75e', 1, 1, 'REGULAR');
insert into warehousedb.locator values ('00000000-b5c6-4896-987c-f30f3678f602', 1, current_timestamp, current_timestamp,'Sapiens', 'a0a4f044-b040-410d-8ead-4de0446aec7e', 1, 1, 'REGULAR');
