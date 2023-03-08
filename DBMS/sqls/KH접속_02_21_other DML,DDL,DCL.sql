---------------------------------------------------------
--DDL : Create - Oracle 객체를 생성하는 SQL
select * from employee;

-- create table <테이블이름> (
--  컬럼명 자료형 제약조건,
--  컬럼명 자료형 제약조건,
--  ...
--);
--메뉴 이름 등이 겹칠 것을 고려해서, 항상 ID(identification, 식별자, 고유번호)를 같이 매겨줄 것.
--제약 조건은 일단 생략하고 제작해 봄.=
    --varchar(바이트 길이) 선언 시, 최대 값 길이를 적어줘야 하므로 들어갈 값이 어느정도 길이의 값인지 고려해야 함.
        --varchar은 가변적인(variable) char란 의미. 데이터가 최대값보다 작으면 그만큼 남은 공간을 회수함.
        --그냥 char은 정적 char. 남는 공간 회수 따위 없음.
create table cafe_menu(
    id number,
    name varchar(20),
    price number
);

desc cafe_menu;
select * from cafe_menu;

--DML : insert - table 객체에 데이터를 입력하는 SQL
--insert into cafe_menu (<컬럼명...>) values(<컬럼명 순서대로의 데이터...>)

insert into cafe_menu (id,name) values (1001, 'Americano');
insert into cafe_menu (id,price) values (1002, 3000);
insert into cafe_menu (id,name,price) values (1003, 'Cafe Mocha', 4000);
--컬럼명을 모두 채워넣을 경우, (<컬럼명...>) 생략 가능.
insert into cafe_menu values(1004, 'Orange Juice', 4500);

--DML : update - table 객체에 저장된 데이터를 수정하는 SQL
update cafe_menu set price = 2000 where id = 1001;
--누락된 name 값도 Cafe Latte로 갱신
update cafe_menu set name = 'Cafe Latte' where id = 1002;

insert into cafe_menu (id) values (1005);
update cafe_menu set name = 'Vanila Latte', price = 3500 where id = 1005;

-- DML : delete - table 객체에 저장된 데이터를 삭제하는 SQL
delete from cafe_menu where id = 1004;

--DML : insert(C), select(R), update(U) delete(D)

--DDL : drop - oracle 내에 존재하는 객체를 삭제하는 SQL
drop table cafe_menu;

--create
    -- not null 제약 조건 - 컬럼에 null 값 허용 X
    -- primary key 제약 조건 - 특정 컬럼을 각 행을 구분하는 식별자로 지정 (null 방지, 중복 방지)
    -- unique 제약 조건 - 컬럼에 중복 방지를 적용 (null 허용, 중복 방지)
    -- check 제약 조건 - 컬럼에 입력이 허용되는 값을 지정함.
    -- foreing key 제약 조건 - 테이블 간 상호 데이터 무결성을 검증.
create table cafe_menu(
    id number primary key,
    name varchar(20) unique not null,
    price number not null,
    isIce char(1) check (isIce in ('Y','N')) not null
);

insert into cafe_menu values(1001, 'Americano',2000, 'Y');
insert into cafe_menu values(1002, 'Cafe Latte', 3000,'N');
select * from cafe_menu;


create table product(
    id number primary key,
    pname varchar(30) not null,
    price number not null
);

insert into product values(1001, 'Airpot', 200000);
insert into product values(1002, 'galaxyS23', 2000000);
insert into product values(1003, 'lg_gram17', 500000);

select * from product;
delete from product where id = 1002;
delete from product where id = 1003;

-- sid (판매기록ID), pid(판매된 항목의 ID), sdate(판매된 날짜)

--on delete cascade
--on delete set null
create table sales_history(
    sid number primary key,
    pid references product(id) on delete set null,
    sdate timestamp not null
);
drop table sales_history;

insert into sales_history values(10001, 1001, sysdate);
insert into sales_history values(10002, 1003, sysdate);
insert into sales_history values(10003, 1002, sysdate);
insert into sales_history values(10004, 1002, sysdate);

select * from sales_history;

-----------------------------------------------------------------------
-- Sequence 객체 : 사용자가 지정한 패턴에 따라 자동 증가 또는 감소하는 값을 관리하는 객체

create sequence product_seq
start with 1002
increment by 1
nomaxvalue nocache;

insert into product values(product_seq.nextval, 'IPad6', '1500000');
select * from product;

---------------------------------------------------------------------------
--DDL : Create, Drop, Alter
--DDL : Alter(수정) : 오라클 내 객체의 설정을 수정하는 SQL

select * from cafe_menu;
select * from cafe;
-- cafe_menu -> cafe
alter table cafe_menu rename to cafe;

--테이블 컬럼 추가 (C)
alter table cafe add (menu_size varchar(10));          --default 'none' not null);
--테이블 컬럼 삭제 (D)
alter table cafe drop column menu_size;
--테이블 컬럼 자료형 변경 (U)
alter table cafe modify name varchar(30);
--컬럼 이름 변경 (U2)
alter table cafe rename column menu_size to scale;

--------------------------------------------------------------------------
--alter 를 통한 제약조건
--oracle - dictionary (오라클 내장 테이블)

select * from user_tables;
select * from user_constraints where table_name = 'CAFE';


alter table cafe drop constraint cafe_id_pk;

alter table cafe add constraint cafe_id_pk primary key(id);
alter table cafe add constraint cafe_name_uk unique(name);
alter table cafe add constraint cafe_isice_chk check(isice in ('Y','N'));

drop table cafe;
--Q.처음 칼럼을 생성할 때 제약 조건에 이름을 붙여줄 수는 없나요?
--A. 네. 가능해요.
create table cafe(
    id number constraint cafe_id_pk primary key,
    name varchar(30) constraint cafe_name_uk unique
                        constraint cafe_name_nn not null,
    price number constraint cafe_price_nn not null,
    isice char(1) constraint cafe_isice_chk_nn check(isice in ('Y','N'))
);

------------------------------------------------------------------------
--alter sequence
create sequence cafe_seq start with 1001 increment by 1 maxvalue 2000 nocache nocycle;
alter sequence cafe_seq nomaxvalue;
alter sequence cafe_seq increment by 10;
alter sequence cafe_seq cycle;



-------------------------------------------------------------------------
--복습 정리
--DML : insert, update, delete, select
--DDL : create, drop, alter

--새로 배울것
--DCL : grant, revoke
--TCL 
------------------------------------------------------------------
--DCL
--view 객체를 통해 연습할 것.
--권한은 테이블 단위로 주어짐. 특정 컬럼을 볼 수 있느냐, 없느냐가 아님.

create view employee_view
as
select emp_id, emp_name, email, phone from employee; 

select * from employee_view; -- view
select * from employee_dev; -- table
--명령어는 비슷하지만, 개념은 완전히 다름을 알 것. 

--grant select on employee_view to dev;

--update employee set emp_name='선동이' where emp_id = 200;
--commit;

select * from user_tab_privs;
revoke select on employee_view from dev;