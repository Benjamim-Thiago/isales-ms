
create table orders (
	id serial not null primary key,
	client_id bigint not null,
	date timestamp not null default now(),
	payment_key text,
	notes text,
	status varchar(20) check (
		status in ('REALIZADO', 'PAGO', 'FATURADO', 'ENVIADO', 'ERRO_PAGAMENTO', 'PREPARANDO_ENVIO')
	),
	total decimal(16,2) not null,
    tracking_code varchar(255),
	url_nf text
);

create table orders_items (
	id serial not null primary key,
	order_id bigint not null references orders (id),
	product_id bigint not null,
	amount int not null,
	unit_price decimal(16,2) not null
);