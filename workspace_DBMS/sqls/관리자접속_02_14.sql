
-- DBMS ������ CRUD ��� : SQL
-- SQL : Structured Query Language (����ȭ�� ���� ���) - ��û
-- Oracle, MySQL, MSSQL

--ī�װ� ��
--SQL
--DDL (Data Definition Language)        : DBMS ���� ��ü�� ���� / ���� / ���� / ��ȸ�ϴ� ��� ����
--                                          create / drop / alter
--DML (Data Manipulation Language)      : DBMS Table�� �����͸� ���� / ���� / ���� / ��ȸ�ϴ� ��� ����
--                                          insert / update / delete / select
--DCL (Data Control Language)           : DBMS ������ ������ �����ϴ� ��� ����
--                                          grant / revoke
--TCL (Transaction Control Language)    : Transaction ���� ��� ����
--                                          commit / rollback / savepoint

create user kh identified by kh;
grant connect,resource to kh;