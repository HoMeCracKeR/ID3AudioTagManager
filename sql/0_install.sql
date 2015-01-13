-- Installation Script

SET client_min_messages = ERROR;
\set user audiotagmanager
\set password '\'audiotagmanager\''
\set database audiotagmanager
\set promptvar ''

\prompt 'DROP USER [':user'] and DROP DATABASE [':database'] if existing (\\q or Ctrl-C to abort)?' promptvar
:promptvar

DROP DATABASE if exists :database;
DROP USER if exists :user;

\echo -------------------------------------
\echo passwort for user :user = :password
\echo -------------------------------------
\echo

\prompt 'CREATE USER [':user'] and DATABASE [':database'] (\\q or Ctrl-C to abort)?' promptvar
:promptvar

CREATE USER :user WITH PASSWORD :password;
CREATE DATABASE :database WITH OWNER :user ENCODING 'UTF8';
\c :database :user

-- specify encoding to match your files encoding, usually UTF8
-- valid values are: 'UTF8', 'LATIN1', 'WIN1252'
\encoding 'UTF8'

-- create schema
\ir 1_schema.sql

-- insert data in slo-mo or use COPY for speedup
\ir 2_data.sql
--\ir 2_copy.sql

-- create primary keys, constraints, indexes
--\ir 3_constraints.sql


