alter table medicos add activo tinyint;
alter table pacientes add activo tinyint;
update medicos set activo = 1;
update pacientes set activo = 1;