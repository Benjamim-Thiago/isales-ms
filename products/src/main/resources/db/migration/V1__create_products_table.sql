CREATE TABLE public.products (
    id serial4 NOT NULL,
    "name" varchar(100) NOT NULL,
    unit_price numeric(16, 2) NOT NULL,
    CONSTRAINT isales_products_pkey PRIMARY KEY (id)
);