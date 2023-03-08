--작성된 테이블 및 시퀀스 코드는 수정하지 말고, alter나 drop 등을 사용해야 함.
--기존 코드들
--Brands Table 생성
create table BRANDS(
    BRAND_ID number primary key,
    BRAND_NAME varchar2(100) NOT NULL
);
--Products 테이블 생성
create table PRODUCTS(
    PRODUCT_NO number primary key,
    PRODUCT_NAME varchar2(50) NOT NULL,
    PRODUCT_PRICE number NOT NULL,
    BRAND_CODE number references BRANDS,
    SOLD_OUT char(1) default 'N' check(SOLD_OUT IN('Y','N'))
);

--SEQ_BRAND_ID 시퀀스 생성
create sequence SEQ_BRAND_ID
    start with 100
    increment by 100
    maxvalue 500
    nocycle;
--SEQ_PRODUCT_NO 시퀀스 생성
create sequence SEQ_PRODUCT_NO
    start with 1
    increment by 1
    maxvalue 10000
    nocycle;

--BRANDS 테이블 데이터 삽입
insert into BRANDS values (SEQ_BRAND_ID.nextval, '삼성');
--이하 문제점 5. 현재는 동작 X.
insert into BRANDS values (SEQ_BRAND_ID.currval, '애플');
--PRODUCTS 테이블 데이터 삽입
--문제점 2를 먼저 해결하지 않으면 아래 데이터 삽입 코드들은 동작하지 않음. (열 수 부족)
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '갤럭시S8', 800000, 100, 'S8', 'Y');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '갤럭시S9', 900000, 100, 'S9', 'N');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '갤럭시S10', 1000000, 100, 'S10', 'N');
--문제점 5를 먼저 해결 하지 않으면 아래 두 코드는 동작하지 않음. (참조키 부모 값 부재)
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '아이폰9S', 900000, 200, '9S', 'N');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '아이폰10S', 1000000, 200, '10S', 'N');

--결과 조회
--문제점 6 _ 3개만 조회하고 있음. 4개 (3번째로 BRAND_NAME)이 출력되어야 함. (시험서 누락함)
select PRODUCT_NAME, PRODUCT_PRICE, SOLD_OUT
from PRODUCTS
join BRANDS
    on (BRAND_ID = BRAND_CODE);

--------------------------------------------------
--해결책 코드들
--1. PRODUCTS.PRODUCT_NAME 크기 수정
alter table PRODUCTS modify PRODUCT_NAME varchar(20);

--2. PRODUCTS 테이블에 SERIAL_NO 컬럼을 추가
alter table PRODUCTS add SERIAL_NO varchar(100) unique;
----및 PRODUCTS 테이블에 대한 insert into 구문들을 수정. (특정 위치에 컬럼을 넣는 법을 모름)
--[수정전](PRODUCT_NO, PRODUCT_NAME, PRODUCT_PRICE, BRAND_CODE, SERIAL_NO, SOLD_OUT)
--[수정후](PRODUCT_NO, PRODUCT_NAME, PRODUCT_PRICE, BRAND_CODE, SOLD_OUT, SERIAL_NO)
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '갤럭시S8', 800000, 100, 'Y', 'S8');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '갤럭시S9', 900000, 100, 'N', 'S9');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '갤럭시S10', 1000000, 100, 'N', 'S10');
--문제점 5를 먼저 해결 하지 않으면 아래 두 코드는 동작하지 않음.
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '아이폰9S', 900000, 200, 'N', '9S');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '아이폰10S', 1000000, 200, 'N', '10S');

--3.  SEQ_BRAND_ID 시퀀스 수정
--3-1. 최대값 수정
--& 3-2. nocache 설정
alter sequence SEQ_BRAND_ID maxvalue 1000 nocache;

--4. SEQ_PRODUCT_NO 시퀀스 수정 (nocache 추가)
alter sequence SEQ_PRODUCT_NO nocache;

--5. Brands Table 삽입 오류 수정
insert into BRANDS values (SEQ_BRAND_ID.nextval, '애플');