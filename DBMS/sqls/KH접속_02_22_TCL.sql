--------------------------------------------------------
--TCL
--Transaction Control Language

--person -> SQL Developer -> DBMS -> Transaction -> DB file

--select�� �ܼ��� �����ִ� �����̹Ƿ� ���� Transaction�� ������� ����.
--�ٸ� DML ��ɵ��� �ٷ� ������� �ʰ� �����. (DML�� �����)

--rollback: Transaction�� ����� ��� Query�� ����ϰ� Ư�� ����(���� �ֱٿ� Commit�� ����� ����)���� �����ϴ� ���
--commit : Transaction�� ����� ��� Query�� Database ���Ͽ� ������ �ݿ��ϴ� ���

--Transcation�� �ʿ��� ����
    --1.������ ���� ���
    --2.�۾��� ���ڼ� - �����ڰ� �̿��ϴ� ����
    --3.������ �ݸ��� - �����ڰ� �����ؾ� �ϴ� ����
    
delete from cafe;
commit;
rollback;

insert into cafe values(cafe_seq.nextval, 'Americano', 2000, 'Y');
insert into cafe values(cafe_seq.nextval, 'Cafe Mocha', 4000, 'Y');

select * from cafe;

delete from cafe where id = 1004;

drop sequence cafe_seq;
create sequence cafe_seq start with 1001 increment by 1 maxvalue 2000 nocache nocycle;