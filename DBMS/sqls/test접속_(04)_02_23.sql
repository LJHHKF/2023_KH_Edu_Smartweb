--�ۼ��� ���̺� �� ������ �ڵ�� �������� ����, alter�� drop ���� ����ؾ� ��.
--���� �ڵ��
--Brands Table ����
create table BRANDS(
    BRAND_ID number primary key,
    BRAND_NAME varchar2(100) NOT NULL
);
--Products ���̺� ����
create table PRODUCTS(
    PRODUCT_NO number primary key,
    PRODUCT_NAME varchar2(50) NOT NULL,
    PRODUCT_PRICE number NOT NULL,
    BRAND_CODE number references BRANDS,
    SOLD_OUT char(1) default 'N' check(SOLD_OUT IN('Y','N'))
);

--SEQ_BRAND_ID ������ ����
create sequence SEQ_BRAND_ID
    start with 100
    increment by 100
    maxvalue 500
    nocycle;
--SEQ_PRODUCT_NO ������ ����
create sequence SEQ_PRODUCT_NO
    start with 1
    increment by 1
    maxvalue 10000
    nocycle;

--BRANDS ���̺� ������ ����
insert into BRANDS values (SEQ_BRAND_ID.nextval, '�Ｚ');
--���� ������ 5. ����� ���� X.
insert into BRANDS values (SEQ_BRAND_ID.currval, '����');
--PRODUCTS ���̺� ������ ����
--������ 2�� ���� �ذ����� ������ �Ʒ� ������ ���� �ڵ���� �������� ����. (�� �� ����)
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������S8', 800000, 100, 'S8', 'Y');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������S9', 900000, 100, 'S9', 'N');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������S10', 1000000, 100, 'S10', 'N');
--������ 5�� ���� �ذ� ���� ������ �Ʒ� �� �ڵ�� �������� ����. (����Ű �θ� �� ����)
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������9S', 900000, 200, '9S', 'N');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������10S', 1000000, 200, '10S', 'N');

--��� ��ȸ
--������ 6 _ 3���� ��ȸ�ϰ� ����. 4�� (3��°�� BRAND_NAME)�� ��µǾ�� ��. (���輭 ������)
select PRODUCT_NAME, PRODUCT_PRICE, SOLD_OUT
from PRODUCTS
join BRANDS
    on (BRAND_ID = BRAND_CODE);

--------------------------------------------------
--�ذ�å �ڵ��
--1. PRODUCTS.PRODUCT_NAME ũ�� ����
alter table PRODUCTS modify PRODUCT_NAME varchar(20);

--2. PRODUCTS ���̺� SERIAL_NO �÷��� �߰�
alter table PRODUCTS add SERIAL_NO varchar(100) unique;
----�� PRODUCTS ���̺� ���� insert into �������� ����. (Ư�� ��ġ�� �÷��� �ִ� ���� ��)
--[������](PRODUCT_NO, PRODUCT_NAME, PRODUCT_PRICE, BRAND_CODE, SERIAL_NO, SOLD_OUT)
--[������](PRODUCT_NO, PRODUCT_NAME, PRODUCT_PRICE, BRAND_CODE, SOLD_OUT, SERIAL_NO)
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������S8', 800000, 100, 'Y', 'S8');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������S9', 900000, 100, 'N', 'S9');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������S10', 1000000, 100, 'N', 'S10');
--������ 5�� ���� �ذ� ���� ������ �Ʒ� �� �ڵ�� �������� ����.
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������9S', 900000, 200, 'N', '9S');
insert into PRODUCTS values (SEQ_PRODUCT_NO.nextval, '������10S', 1000000, 200, 'N', '10S');

--3.  SEQ_BRAND_ID ������ ����
--3-1. �ִ밪 ����
--& 3-2. nocache ����
alter sequence SEQ_BRAND_ID maxvalue 1000 nocache;

--4. SEQ_PRODUCT_NO ������ ���� (nocache �߰�)
alter sequence SEQ_PRODUCT_NO nocache;

--5. Brands Table ���� ���� ����
insert into BRANDS values (SEQ_BRAND_ID.nextval, '����');