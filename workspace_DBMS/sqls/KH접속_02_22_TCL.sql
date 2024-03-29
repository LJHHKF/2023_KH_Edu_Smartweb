--------------------------------------------------------
--TCL
--Transaction Control Language

--person -> SQL Developer -> DBMS -> Transaction -> DB file

--select는 단순히 보여주는 쿼리이므로 따로 Transaction에 저장되진 않음.
--다른 DML 명령들은 바로 적용되지 않고 저장됨. (DML만 저장됨)

--rollback: Transaction에 저장된 모든 Query를 취소하고 특정 시점(가장 최근에 Commit이 수행된 시점)으로 복원하는 명령
--commit : Transaction에 저장된 모든 Query를 Database 파일에 실제로 반영하는 명령

--Transcation이 필요한 이유
    --1.안전한 쿼리 운용
    --2.작업의 원자성 - 개발자가 이용하는 성질
    --3.데이터 격리성 - 개발자가 주의해야 하는 성질
    
delete from cafe;
commit;
rollback;

insert into cafe values(cafe_seq.nextval, 'Americano', 2000, 'Y');
insert into cafe values(cafe_seq.nextval, 'Cafe Mocha', 4000, 'Y');

select * from cafe;

delete from cafe where id = 1004;

drop sequence cafe_seq;
create sequence cafe_seq start with 1001 increment by 1 maxvalue 2000 nocache nocycle;