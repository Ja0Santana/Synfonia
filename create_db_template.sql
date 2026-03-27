-- Script para criar banco de dados e usuário
-- Execute via pgAdmin ou psql
-- Substitua ${DB_USER} e ${DB_PASS} pelos valores do seu arquivo .env

-- Criar banco de dados
CREATE DATABASE db_usuarios_musicas;

-- Criar usuário
CREATE USER ${DB_USER} WITH PASSWORD '${DB_PASS}';

-- Conceder privilégios
GRANT ALL PRIVILEGES ON DATABASE db_usuarios_musicas TO ${DB_USER};

-- Conceder permissões para objetos futuros no banco
ALTER DATABASE db_usuarios_musicas OWNER TO ${DB_USER};
