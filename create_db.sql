-- Script para criar banco de dados e usuário
-- Execute via pgAdmin ou psql

-- Criar banco de dados
CREATE DATABASE db_usuarios_musicas;

-- Criar usuário
CREATE USER postgre WITH PASSWORD 'b2a7c9o3';

-- Conceder privilégios
GRANT ALL PRIVILEGES ON DATABASE db_usuarios_musicas TO postgre;

-- Conceder permissões para objetos futuros no banco
ALTER DATABASE db_usuarios_musicas OWNER TO postgre;