create database if not exists db_academia;
use db_academia;

-- tabla profesor
create table tbl_profesor(
	id int auto_increment primary key,
    nombres varchar(100) not null,
    apellidos varchar(100) not null,
    email varchar(100) not null unique
);
select * from tbl_profesor;

-- tabla curso
create table tbl_curso(
	id int auto_increment primary key,
    nombre varchar(100) not null,
    nivel char(1) not null, -- 'B = Basico, I = Intermedio, A = Avanzado'
    precio decimal(10,2) not null,
    activo tinyint(1) not null default 1,
    id_profesor int not null,
    foreign key(id_profesor) references tbl_profesor(id)
);
select * from tbl_curso;

-- Realizamos insercion de prueba
insert into tbl_profesor(nombres,apellidos,email) 
values
('Ernesto','Arbulu','ernesto@academia.com'),
('Monica','Sotomayor','monica@academia.com');
