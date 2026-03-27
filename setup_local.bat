@echo off
REM Script para preparar o ambiente local
REM Este script cria o arquivo .env com as variáveis necessárias

echo.
echo ========================================
echo Setup Local - Projeto Musicas
echo ========================================
echo.

REM Verificar se .env já existe
if exist .env (
    echo [!] Arquivo .env ja existe!
    echo Abra o arquivo e configure as variaveis necessarias.
    pause
    exit /b
)

REM Criar arquivo .env baseado no .env.example
echo [*] Criando arquivo .env...
copy .env.example .env

echo.
echo [OK] Arquivo .env criado com sucesso!
echo.
echo [!] IMPORTANTE: Abra o arquivo .env e configure os valores:
echo    - DB_USER e DB_PASS (do PostgreSQL)
echo    - MONGO_URI (se usar MongoDB)
echo    - JWT_SECRET (sua chave secreta)
echo    - SPOTIFY_CLIENT_ID e SPOTIFY_CLIENT_SECRET
echo    - Demais configuracoes necessarias
echo.
echo [*] Depois execute o script SQL:
echo    - Copie create_db.sql.example para create_db.sql
echo    - Substitua os placeholders pelos valores reais
echo    - Execute no PostgreSQL (pgAdmin ou psql)
echo.
echo [*] Para iniciar o projeto:
echo    gradlew bootRun
echo.
pause

