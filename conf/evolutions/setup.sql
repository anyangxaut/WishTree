# --- !root login

# --- !create database
DROP DATABASE IF EXISTS wishtree
CREATE DATABASE IF NOT EXISTS wishtree;

# --- !create user
CREATE USER 'wishtree_admin'@'%' IDENTIFIED BY 'password';

# --- |grant user privileges
GRANT ALL ON wishtree.* TO 'wishtree_admin'@'%';