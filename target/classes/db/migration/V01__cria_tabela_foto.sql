/*
create table tb_fotos (
id_foto serial primary key,
dados oid,
tipo varchar(100),
nome varchar(100),
id_usuario bigint,
foreign key (id_usuario) references tb_users(id)
);
*/