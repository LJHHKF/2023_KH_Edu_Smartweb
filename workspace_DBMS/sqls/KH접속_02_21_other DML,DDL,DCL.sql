---------------------------------------------------------
--DDL : Create - Oracle ��ü�� �����ϴ� SQL
select * from employee;

-- create table <���̺��̸�> (
--  �÷��� �ڷ��� ��������,
--  �÷��� �ڷ��� ��������,
--  ...
--);
--�޴� �̸� ���� ��ĥ ���� ����ؼ�, �׻� ID(identification, �ĺ���, ������ȣ)�� ���� �Ű��� ��.
--���� ������ �ϴ� �����ϰ� ������ ��.=
    --varchar(����Ʈ ����) ���� ��, �ִ� �� ���̸� ������� �ϹǷ� �� ���� ������� ������ ������ ����ؾ� ��.
        --varchar�� ��������(variable) char�� �ǹ�. �����Ͱ� �ִ밪���� ������ �׸�ŭ ���� ������ ȸ����.
        --�׳� char�� ���� char. ���� ���� ȸ�� ���� ����.
create table cafe_menu(
    id number,
    name varchar(20),
    price number
);

desc cafe_menu;
select * from cafe_menu;

--DML : insert - table ��ü�� �����͸� �Է��ϴ� SQL
--insert into cafe_menu (<�÷���...>) values(<�÷��� ��������� ������...>)

insert into cafe_menu (id,name) values (1001, 'Americano');
insert into cafe_menu (id,price) values (1002, 3000);
insert into cafe_menu (id,name,price) values (1003, 'Cafe Mocha', 4000);
--�÷����� ��� ä������ ���, (<�÷���...>) ���� ����.
insert into cafe_menu values(1004, 'Orange Juice', 4500);

--DML : update - table ��ü�� ����� �����͸� �����ϴ� SQL
update cafe_menu set price = 2000 where id = 1001;
--������ name ���� Cafe Latte�� ����
update cafe_menu set name = 'Cafe Latte' where id = 1002;

insert into cafe_menu (id) values (1005);
update cafe_menu set name = 'Vanila Latte', price = 3500 where id = 1005;

-- DML : delete - table ��ü�� ����� �����͸� �����ϴ� SQL
delete from cafe_menu where id = 1004;

--DML : insert(C), select(R), update(U) delete(D)

--DDL : drop - oracle ���� �����ϴ� ��ü�� �����ϴ� SQL
drop table cafe_menu;

--create
    -- not null ���� ���� - �÷��� null �� ��� X
    -- primary key ���� ���� - Ư�� �÷��� �� ���� �����ϴ� �ĺ��ڷ� ���� (null ����, �ߺ� ����)
    -- unique ���� ���� - �÷��� �ߺ� ������ ���� (null ���, �ߺ� ����)
    -- check ���� ���� - �÷��� �Է��� ���Ǵ� ���� ������.
    -- foreing key ���� ���� - ���̺� �� ��ȣ ������ ���Ἲ�� ����.
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

-- sid (�Ǹű��ID), pid(�Ǹŵ� �׸��� ID), sdate(�Ǹŵ� ��¥)

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
-- Sequence ��ü : ����ڰ� ������ ���Ͽ� ���� �ڵ� ���� �Ǵ� �����ϴ� ���� �����ϴ� ��ü

create sequence product_seq
start with 1002
increment by 1
nomaxvalue nocache;

insert into product values(product_seq.nextval, 'IPad6', '1500000');
select * from product;

---------------------------------------------------------------------------
--DDL : Create, Drop, Alter
--DDL : Alter(����) : ����Ŭ �� ��ü�� ������ �����ϴ� SQL

select * from cafe_menu;
select * from cafe;
-- cafe_menu -> cafe
alter table cafe_menu rename to cafe;

--���̺� �÷� �߰� (C)
alter table cafe add (menu_size varchar(10));          --default 'none' not null);
--���̺� �÷� ���� (D)
alter table cafe drop column menu_size;
--���̺� �÷� �ڷ��� ���� (U)
alter table cafe modify name varchar(30);
--�÷� �̸� ���� (U2)
alter table cafe rename column menu_size to scale;

--------------------------------------------------------------------------
--alter �� ���� ��������
--oracle - dictionary (����Ŭ ���� ���̺�)

select * from user_tables;
select * from user_constraints where table_name = 'CAFE';


alter table cafe drop constraint cafe_id_pk;

alter table cafe add constraint cafe_id_pk primary key(id);
alter table cafe add constraint cafe_name_uk unique(name);
alter table cafe add constraint cafe_isice_chk check(isice in ('Y','N'));

drop table cafe;
--Q.ó�� Į���� ������ �� ���� ���ǿ� �̸��� �ٿ��� ���� ������?
--A. ��. �����ؿ�.
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
--���� ����
--DML : insert, update, delete, select
--DDL : create, drop, alter

--���� ����
--DCL : grant, revoke
--TCL 
------------------------------------------------------------------
--DCL
--view ��ü�� ���� ������ ��.
--������ ���̺� ������ �־���. Ư�� �÷��� �� �� �ִ���, �����İ� �ƴ�.

create view employee_view
as
select emp_id, emp_name, email, phone from employee; 

select * from employee_view; -- view
select * from employee_dev; -- table
--��ɾ�� ���������, ������ ������ �ٸ��� �� ��. 

--grant select on employee_view to dev;

--update employee set emp_name='������' where emp_id = 200;
--commit;

select * from user_tab_privs;
revoke select on employee_view from dev;