CREATE TABLE public.customers (
    id serial4 NOT NULL,
    "name" varchar(150) NOT NULL,
    "document" bpchar(11) NOT NULL,
    street varchar(100) NULL,
    "number" varchar(10) NULL,
    area varchar(100) NULL,
    email varchar(150) NULL,
    phone varchar(20) NULL,
    CONSTRAINT customers_pkey PRIMARY KEY (id)
);