
-- DBMS 데이터 CRUD 방식 : SQL
-- SQL : Structured Query Language (구조화된 질의 언어) - 요청
-- Oracle, MySQL, MSSQL

--카테고리 별
--SQL
--DDL (Data Definition Language)        : DBMS 상의 객체를 생성 / 수정 / 삭제 / 조회하는 명령 집합
--                                          create / drop / alter
--DML (Data Manipulation Language)      : DBMS Table에 데이터를 생성 / 수정 / 삭제 / 조회하는 명령 집합
--                                          insert / update / delete / select
--DCL (Data Control Language)           : DBMS 계정의 권한을 통제하는 명령 집합
--                                          grant / revoke
--TCL (Transaction Control Language)    : Transaction 통제 명령 집합
--                                          commit / rollback / savepoint

create user kh identified by kh;
grant connect,resource to kh;