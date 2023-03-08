
--SQL
--DDL
--DML
    -- insert, update, select, delete
    -- select (DQL) : 
    -- select�� DB ��Ʈ�� 60% �̻��̶�� ���� ��.
    -- select ���� -> function() -> group by -> having -> join -> [union -> subquery]
--DCL
--TCL

---------------------------------------------------------------------
--union
select dept_id,dept_title from department
union
select * from job;

--���̺� ���� [DDL]
create table A(
    data number -- ���̺��(������ �������������� ...)
);
insert into A values(1);
insert into A values(2);
insert into A values(3);

create table B(
    data number
);
insert into B values(2);
insert into B values(3);
insert into B values(4);

select * from A;
select * from B;

drop table A; -- ���̺� �����

select * from A
union
select * from B;
--union : �� ���̺� ���� �������̸� �� �� �ߺ��� ���� 1ȸ�� ����Ѵ�.

select * from A
union all
select * from B;
-- union all : �� ���̺� ���� �ߺ��� ����ϴ� ������

select * from A
intersect
select * from B;
-- intersect : �� ���̺� ���� ������(�ߺ��� ����)

select * from A
minus
select * from B;
-- minus : ������, A-B ���� (A���� B���� ������ ����)

---------------------------------------------------------
--SubQuery : ���� ���� ����
--������ ���Ͽ� subquery
--������ subquery
--���߿� subquery
--������ ���߿� subquery
--inline view
--��� subquery

select manager_id from employee where emp_name='������'; -- 214 ���� ���ƿ´�.
select emp_name from employee where emp_id = 214;
--������ ������ �̸��� ���� �Ŵ��� id�� �ľ��ϰ�, �� id�� ���� �Ŵ����� �������� �ľ�.
--self join���ε� �ذ� ������. �׷��� ���������� ��ư� ���������ε� ���ٰ� ���� ����.

select
    emp_name
from employee
where
    emp_id = (select manager_id from employee where emp_name = '������');

--Quiz
--1. ������ ������ �޿��� ������ �޿��� �޴� ������ �̸� �� �޿� ���.
select
    emp_name as "�����",
    salary as "�޿�"
from employee
where
    (select salary from employee where emp_name = '������') = salary and
    emp_name != '������';

--2. �� ���� �޿� ��պ��� �޿��� ���� �޴� ������ �̸�, �����ڵ�, �޿��� ����ϼ���.
select
    emp_name as "������",
    job_code as "�����ڵ�",
    salary as "�޿�"
from employee
where
    (select avg(salary) from employee) <= salary;
--�ܼ��� avg(salary) <= salary �� ���� ���� �Ǵ� �� ������,
--'where �������� group �Լ��� �� �� ����'�� ��Ģ�� �־ ����� �� ����.

--3. D1, D2 �μ��� ���� �߿��� �޿��� D5�μ��� �޿� ��պ���
--  ���� �޴� �������� �μ���ȣ, ���, �����, �޿��� ����ϼ���.
select
    dept_code as "�μ���ȣ",
    emp_id as "���",
    emp_name as "�����",
    salary as "�޿�"
from employee
where
    dept_code in ('D1', 'D2') and
    salary >= (select avg(salary) from employee where dept_code = 'D5');

--4.�޿��� ���� ���� ����� ���� ���� �����, ��� ����� �޿��� ����غ�����.
select
    emp_id as "���",
    emp_name as "�����",
    salary as "�޿�"
from employee
where
    salary in ((select max(salary) from employee)
                ,(select min(salary) from employee));

-----------------------------------------------------------
--������ ���Ͽ� ��������
--�ڳ���, ������ ������ �μ��ڵ�� ������ �μ��� ���� �������� ����
select *
from employee
where
    dept_code in (select dept_code from employee where emp_name in ('�ڳ���','������'));

--Quiz
--1. ���¿� �Ǵ� ������ ������ �޿���� (sal_level)�� ���� �޿������ ������ ���޸�� ������� ���
select
    j.job_name as "���޸�",
    e.emp_name as "�����",
    e.sal_level as "�޿����"
from employee e
inner join job j
    on e.job_code = j.job_code
where
    e.sal_level in (select sal_level from employee where emp_name in ('���¿�', '������'));

--2. ������ ��ǥ�ų� �λ����� �ƴ� ��� ������ �����, �����ڵ带 ���
select
    emp_name as "�����",
    job_code as "�����ڵ�"
from employee 
where
    job_code not in (select job_code from job where job_name in ('��ǥ', '�λ���'));
    
-- '=' �� in���� ó���� �����ѵ�, �۴ٿ� ũ�ٴ� ��� �� �� �ְڴ°�?
-- D5 �μ��� �������� �޿����� ���� �޴� ������ ������ ���
--����� ǥ�� �ָ�. (�������ٵ��� ���� / �̵� ��κ��� ����)
select emp_name as "�����", salary as "�޿�" from employee
    where salary > all(select salary from employee where dept_code='D5');
--�Ǵ�
select emp_name as "�����", salary as "�޿�" from employee
    where salary > (select max(salary) from employee where dept_code='D5');

----------------------------------------------------------------
--���� �� �������� == ������ ���߿� �������� ������ ����
--����� ����� ���� �μ�, ���� ������ ����� ���
select dept_code, job_code from employee where ent_yn='Y';
select emp_name, dept_code, job_code from employee
    where (dept_code, job_code) in (select dept_code, job_code from employee where ent_yn='Y');

--Quiz
--1. ������ 8�� 8���� ������ ���� �μ��ڵ�, ���� �����ڵ��� �������� ������ ������ ���
select
    dept_code as "�μ��ڵ�",
    job_code as "�����ڵ�",
    emp_name as "�����",
    to_char(to_date('19'||substr(emp_no,1,6), 'YYYYMMDD'), 'YYYY"��" MM"��" DD"��"') as "����"
    --35�� ��, ���ǿ� ���� �� ���� �ϼ� ������ �־ ���� �ȵ� �� ������ ������. ���� �ּ� �ڵ�� ���Ľ�� ��.
    --substr(emp_no,1,2)||'�� '||substr(emp_no,3,2)||'�� '||substr(emp_no,5,2)||'��' as "����"
from employee
where
    (dept_code,job_code) in (select dept_code, job_code from employee
                                where substr(emp_no,3,4) = '0808')
     and substr(emp_no,3,4) != '0808';

--2. �μ��� �޿��� ���� ���� �޴� ������, �μ��ڵ�, �����̸� �� �޿��� ������ּ���.
--  �μ� �ڵ�� ����
select
    nvl(dept_code, '����') as "�μ��ڵ�",
    emp_name as "�����̸�",
    salary as "�޿�"
from employee
where
    (nvl(dept_code, '����'), salary) in
        (select nvl(dept_code, '����'), max(salary) from employee
        group by nvl(dept_code, '����'))
order by dept_code asc;

----------------------------------------------------------------
--Subquery�� ����ϴ� �� �ٸ� ���� - select��
select
    emp_name as "�����",
    (select dept_title from department where dept_code = dept_id) as "�μ���"
from employee;

--������� ���޸��� ����ϼ���. (��, Subquery�� Ǫ����)
select
    e.emp_name as "������",
    (select j.job_name from job j where e.job_code = j.job_code) as "���޸�"
from employee e;

--inline view
select emp_id, emp_no from (select emp_id,emp_name,emp_no from employee);

--subquery �ϴ� ����
-------------------------------------------------------------------------
--rank ���� �Լ� [������� ��Ư�ؼ� �ڷ� ���� ���� �Լ�]
select emp_name, salary, rank() over(order by salary desc) as "�޿���ŷ" from employee;
select emp_name, salary, dense_rank() over(order by salary desc) as "�޿���ŷ" from employee;
select emp_name, salary, row_number() over(order by salary desc) "�޿� ��ŷ" from employee;

--Quiz
--1. �޿� ������������ �ึ�� ��ȣ�� �ٿ��� ��, 10~15�������� �����͸� ������ּ���.
select
    emp_name as "�����",
    salary as "�޿�",
    "�޿� ��ŷ"
from (select emp_name, salary, row_number() over(order by salary desc) as "�޿� ��ŷ" from employee)
where "�޿� ��ŷ" between 10 and 15;

------------------------------------------------------------------
--Quiz
--1. ��������ο� ���� ������� ����� �̸�,�μ��ڵ�,�޿��� ����Ͻÿ�
select
    e.emp_name as "�����",
    e.dept_code as "�μ��ڵ�",
    to_char(e.salary, 'L999,999,999,999') as "�޿�"
from employee e
inner join department d
    on e.dept_code = d.dept_id
where d.dept_title = '���������';

--2. ��������ο� ���� ����� �� ���� ������ ���� ����� �̸�,�μ��ڵ�,�޿��� ����Ͻÿ�
-- ����� �ᱹ �޿��� ���Ƿ� �˻��� �޿� �������� �ִ�ġ�� ã��.
--���� ��� => (�޿�+(�޿�*���ʽ�))*12
--�ܼ� �޿� ������� '������', ���� ���� ���� �� '���¸�'
select
    e.emp_name as "�����",
    e.dept_code as "�μ��ڵ�",
    to_char(e.salary, 'L999,999,999,999') as "�޿�"
from employee e
inner join department d
    on e.dept_code = d.dept_id
where (e.dept_code, (salary + (salary * nvl(bonus, 0)))*12) in
        (select dept_code, max((salary + (salary * nvl(bonus, 0)))*12) from employee group by dept_code
            having dept_code = (select dept_id from department where dept_title = '���������'));

--3. �Ŵ����� �ִ� ����߿� ������ ��ü��� ����� �Ѱ� ���,�̸�,�Ŵ��� �̸�,����(������������)�� ���Ͻÿ�.
--��, JOIN�� �̿��Ͻÿ�
select
    e1.emp_id as "���",
    e1.emp_name as "�����",
    e2.emp_name as "�Ŵ��� �̸�",
    e1.salary / 10000 ||'����' as "����"
    --to_char(round(e1.salary, -4), 'L999,999,999,999') as "����"
from employee e1
inner join employee e2
    on e1.manager_id = e2.emp_id
where
    e1.manager_id is not null
    and e1.salary > (select avg(salary) from employee);

--4. �� ���޸��� �޿� ����� ���� ���� ������ �̸�, �����ڵ�, �޿�, �޿���� ��ȸ
select
    emp_name as "�����",
    job_code as "�����ڵ�",
    salary as "�޿�",
    sal_level as "�޿����"
from employee
where
    (job_code, sal_level) in
        (select job_code, 'S'||Max(to_number(substr(sal_level,2,1))) from employee
            group by job_code)
order by job_code;

--5. ���� ���ʽ��� ���� �޴� ������ ������� �̾����� 4~8���� ����, �����, �޿�, ���ʽ����� ���
select
    "���ʽ� ��ŷ" as "����",
    emp_name as "�����",
    salary as "�޿�",
    nvl(bonus*100, 0)||'%' as "���ʽ���"
from (select
    emp_name,
    salary,
    bonus,
    row_number() over(order by nvl(bonus,0) desc) as "���ʽ� ��ŷ"
        from employee)
where "���ʽ� ��ŷ" between 4 and 8;

--6. �μ��� ��� �޿��� 2200000 �̻��� �μ���, ��� �޿� ��ȸ ��, ��� �޿��� �Ҽ��� ����
select
    d.dept_title as "�μ���",
    floor(avg(salary)) as "��� �޿�"
from employee e
inner join department d
    on e.dept_code = d.dept_id
group by d.dept_title
having avg(salary) >= 2200000;

--7. ������ ���� ��պ��� ���� �޴� ���ڻ���� 
--�����,�����ڵ�,�μ��ڵ�,������ �̸� ������������ ��ȸ�Ͻÿ� ���� ��� => (�޿�+(�޿�*���ʽ�))*12
--�� Ǯ����.
select
    e1.emp_name as "�����",
    e1.job_code as "�����ڵ�",
    e1.dept_code as "�μ��ڵ�",
    (e1.salary + (e1.salary * nvl(e1.bonus, 0)))*12 as "����"
from employee e1
where
    (e1.salary + (e1.salary * nvl(e1.bonus, 0)))*12 <
        (select avg((e2.salary + (e2.salary * nvl(e2.bonus, 0)))*12) from employee e2
            where e1.job_code = e2.job_code)
    and substr(emp_no,8,1) = '2'
order by emp_name asc;
--���� ������ Ǯ��
select
    e1.emp_name �����,
    e1.job_code �����ڵ�,
    e1.dept_code �μ��ڵ�,
    ����
from employee e1
join (select job_code, avg(salary*(1+nvl(bonus, 0))*12) ������տ���
      from employee
      group by job_code) e2
    on e1.job_code = e2.job_code
join (select salary*(1+nvl(bonus, 0))*12 ����, emp_id from employee) e3
    on e3.emp_id = e1.emp_id
where (substr(emp_no, 8, 1) = 2) 
    and
    (���� < ������տ���)
order by 1;