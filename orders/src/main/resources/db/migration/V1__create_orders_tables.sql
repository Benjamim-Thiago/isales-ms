CREATE TABLE public.orders (
    id serial4 NOT NULL,
    customer_id int8 NOT NULL,
    "date" timestamp DEFAULT now() NOT NULL,
    payment_key text NULL,
    notes text NULL,
    status varchar(20) NULL,
    total numeric(16, 2) NOT NULL,
    tracking_code varchar(255) NULL,
    url_nf text NULL,
    CONSTRAINT orders_pkey PRIMARY KEY (id),
    CONSTRAINT orders_status_check CHECK (((status)::text = ANY ((ARRAY['REALIZADO'::character varying, 'PAGO'::character varying, 'FATURADO'::character varying, 'ENVIADO'::character varying, 'ERRO_PAGAMENTO'::character varying, 'PREPARANDO_ENVIO'::character varying])::text[])))
);

CREATE TABLE public.orders_items (
    id serial4 NOT NULL,
    order_id int8 NOT NULL,
    product_id int8 NOT NULL,
    amount int4 NOT NULL,
    unit_price numeric(16, 2) NOT NULL,
    CONSTRAINT orders_items_pkey PRIMARY KEY (id)
);

ALTER TABLE public.orders_items ADD CONSTRAINT orders_items_order_id_fkey FOREIGN KEY (order_id) REFERENCES public.orders(id);