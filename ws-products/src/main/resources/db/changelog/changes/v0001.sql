create table category (
  id bigserial not null,
  descricao varchar(20) not null,
  primary key (id)
);

create table product (
  id bigserial not null,
  categoryId bigint not null references category (id),
  descricao varchar(255) not null,
  dataCompra timestamp without time zone not null,
  preco bigint not null,
  origem varchar(15),
  primary key (id)
);