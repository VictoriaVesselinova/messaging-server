create table message
(
	id bigserial PRIMARY KEY,
	type varchar NOT NULL,
	payload varchar(255) NOT NULL,
	created_at timestamp default timestamp 'now ( )'
);