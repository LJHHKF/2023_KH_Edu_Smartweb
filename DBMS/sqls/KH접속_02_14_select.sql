
desc employee;
-- describe employee; : employee ���̺� ���� ����(����)�϶�

select emp_id, emp_name, emp_no from employee;
-- employe ���̺��� emp_id, emp_name, emp_no �÷��� �ش��ϴ� �����͸� ��ȸ�϶�.

select * from employee;
-- employee ���̺��� ��� �÷� �����͸� ��ȸ�϶�

select emp_id, emp_name from employee where emp_id = 207;

--Quiz
--1.Employee ���̺� �����ϴ� ��� ������ ���, �̸�, �޿��� ����غ�����.
select emp_id, emp_name, salary from Employee;
--2. ���� �� D9 �μ��� ���� �ִ� �������� ���, �̸�, �μ��ڵ带 ����غ�����.
--����Ŭ ���ڿ��� '' ��ȣ�� ǥ��
select emp_id, emp_name, dept_code from employee where dept_code = 'D9';

select * from employee where salary >= 4000000;
--J1�� J2�� Job ���̺��� � ��å���� �˾ƺ� ��.
desc job;
select * from job;

--Quiz
--1. ���� �� �޿��� 200���� ���Ϸ� �޴� ������ �̸� �� �μ��ڵ�, �����ڵ带 ����غ�����.
select 
    emp_name,
    dept_code,
    job_code
from
    employee
where
    salary <= 2000000;

-- and, or
select * from employee;
select * from employee where dept_code = 'D6' and salary <= 3000000;
select * from employee where dept_code = 'D6' or salary <= 3000000;

--Quiz
--1. �޿��� 200~300�� ������ ������ ���, �̸�, �޿�, ���� �ڵ带 ����ϼ���.
select emp_id, emp_name, salary, job_code
from employee 
where salary >= 2000000 and salary <= 3000000;
--------- �Ǵ�
select emp_id, emp_name, salary, job_code
from employee
where salary between 2000000 and 3000000;

--Quiz
--1. Employee ���̺��� ���� �߿� job_code �� J3 �̸鼭, �޿��� 350���� �̻��� ������ �̸��� ��ȭ��ȣ
select emp_name, phone
from employee
where job_code = 'J3' and salary >= 3500000;

--2. Employee ���̺��� ���� �߿� �μ��ڵ尡 D5 �̰ų� D6 �̰ų� D9 �� ������ �̸��� �μ��ڵ带 ����غ�����.
select emp_name, dept_code
from employee
where dept_code = 'D5' or dept_code = 'D6' or dept_code = 'D9';
------�Ǵ�
select emp_name, dept_code
from employee
where dept_code in ('D5', 'D6', 'D9');

select *
from employee
where dept_code is null;
--�Ǵ� is not null

--alias
select emp_name �����, salary ���� , salary * 12 as "����", '��' as ���� from employee;

----------------------���� 'sysdate'
--����Ǵ� ���������� ���� �ð�
--dual : ���� ���̺� - SQL �̿��ڰ� Ư�� ����� �׽�Ʈ �� �� ����� �� �ֵ��� �̸� ����� �� ����ƺ� ���̺�
--sysdate : sysdate�� ȣ��Ǵ� ������ ��¥ �� �ð��� ��ȯ�ϴ� oracle ���/���
--          date type�� ����� ��ȯ�Ѵ�.
--          ��¥ Ÿ�Կ� ���� ��ġ ������ (+,-) �ϼ� ������ ����ȴ�.
--          ��¥ Ÿ�԰� ��¥ Ÿ���� �����ϸ� �� ��¥ ������ ������ ��ȯ�ȴ�.
select sysdate from dual;

select 'ABC' - 10  from dual;
select 10 -5 from dual;
select sysdate - 1 from dual;

--floor : �Ǽ��� �Ķ���ͷ� ���޹����� �Ҽ��� �ڸ����� �����ϰ� ���� �ڸ��� ��ȯ�ϴ� �޼���
select
    emp_id,
    emp_name,
    hire_date �Ի���,
    floor(sysdate - hire_date)||'��'  as �ٹ��ϼ�
from
    employee;

--Quiz
--1. Employee ���̺��� �ټӳ���� 20�� �̻� �� ������ �̸�, ����, ���ʽ��� �� ����غ�����.
select emp_name, salary, bonus
from employee
where floor((sysdate - hire_date)/365) >= 20;

--2. ��� ������ �̸�, ����, �Ǽ��ɾ�(���� - ���� * 3%))�� ����غ�����.
select
    emp_name,
    salary * 12 as "����",
    ((salary * 12) - (salary * 12 * 0.03)) as "�Ǽ��ɾ�"
from employee;

select * from employee
    where hire_date between '90/01/01' and '01/12/31';

--------------------------- like
select * from employee
    where emp_name like '��%';

select * from employee
    where emp_name like '%��';
    
select * from employee
    where emp_name like '%��%';


--Quiz
--1. ��� ���� �߿� ��ȭ��ȣ ó�� 3�ڸ��� 010�� �ƴ� ������ �̸��� ��ȭ��ȣ�� ����غ�����.
select emp_name, phone from employee
    where phone not like '010%';

--2. ��� ���� �߿��� �̸��� '��' �ڰ� ���� ����� ��� ã�� �̸��� ����غ�����.
select emp_name from employee
    where emp_name like '%��%';
--3. ��� ���� �߿���
    -- �μ� �ڵ尡 D9 �Ǵ� D6�̰�
    -- ������ 270���� �̻��̸�
    -- email�� ���ĺ� s�� ���ԵǾ� �ְ�
    -- ������� 2001�� 1�� 1�Ϻ��� 2005�� 12�� 31�� ������ ������
    -- �̸�, �μ��ڵ�, �޿�, email, ������� ����ϼ���.
select emp_name, dept_code, salary, email, hire_date
from employee
where
    dept_code in ('D9','D6') and
    salary >= 2700000 and
    email like '%s%' and
    hire_date between '2001/01/01' and '2005/12/31';
    
--Quiz
--1. ���ӻ��(manager_id)�� ���� �μ� ��ġ(dept_code)�� ���� ���� ������ ��� �� �̸��� ����ϼ���.
select emp_id, emp_name from employee
    where 
        manager_id is null and 
        dept_code is null;
--2. �μ� ��ġ�� ���� �ʾ����� ���ʽ��� ���޵Ǵ� ������ �̸� �� ���ʽ��� ����ϼ���.
select emp_name, bonus from employee
    where
        dept_code is null and
        bonus is not null;
        
---------------------------- order by
--order by : ���� ���� ������� Ư�� ������ �̿��Ͽ� �����͸� �����ϴ� ����

select emp_id, emp_name, dept_code, salary from employee
order by dept_code desc nulls last, salary asc;

--select �� n��°�� �����ϴ� �÷��� �������� �����ϰڴ�.
--�� ���� �ڵ�� ���� �ǹ�
select emp_id, emp_name, dept_code, salary from employee
order by 3 desc nulls last, 4 asc;

----------------------------------���� ����
--����1
--�ټӳ���� 5�� �̻�, 10�� ������ ������ �̸�, �ֹι�ȣ, �޿�, �Ի����� �˻��ϼ���.

select emp_name, emp_no, salary, hire_date from employee
    where floor((sysdate - hire_date) / 365) >= 5 and floor((sysdate - hire_date) / 365) <= 10;

--����2
--���� ���� �ƴ� ������ �̸� �μ��ڵ带 �˻��ϼ���.(��� ����  ENT_YN)

select emp_name, dept_code from employee where ent_yn = 'Y';

--����3
--�ټӳ���� 10�� �̻��� �������� �˻��Ͽ�
--��� ����� �̸�, �޿�, �ټӳ��(�Ҽ���X)��, �ټӳ�� �������� ������������ �����Ͽ� ����ϼ���.
--��, �޿��� 50% �λ�� �޿��� ��µǵ��� �ϼ���.

select
    emp_name,
    (salary * 1.5) as "�޿�",
    (floor((sysdate - hire_date) / 365)) as "�ټӳ��"
from employee
where
    floor((sysdate - hire_date) / 365) >= 10
order by
    3 asc;

--����4.
--�Ի����� 99/01/01 ~ 10/01/01�� ��� �߿��� �޿��� 2000000 �� ������ �����
--�̸�, �ֹι�ȣ, �̸���, ����ȣ, �޿��� �˻��ϼ���.

select emp_name, emp_no, email, phone, salary from employee
    where
        (hire_date between '1999/01/01' and '10/01/01') and
        salary <= 2000000;

--����5 
--�޿��� 2000000��, 3000000�� �� ������ �߿��� 4�� �����ڸ� �˻��Ͽ�
--�̸�, �ֹι�ȣ, �޿�, �μ��ڵ带 �ֹι�ȣ ������ (��������) ����ϼ���.
--��, �μ��ڵ尡 null�� ����� �μ��ڵ尡 '����'���� ����ϼ���. // �� ������ ���� �� ǰ. �Ѿ ��.

select emp_name, emp_no, salary, nvl(dept_code, '����') as "DEPT_CODE" from employee
    where
        (salary between 2000000 and 3000000) and
        emp_no like '_______2%' and
        emp_no like '__04%'
    order by emp_no desc;    
        
--����6.
--���� ��� �� ���ʽ��� ���� ����� ���ñ��� �ٹ����� �����Ͽ�
--1000�� ����(�Ҽ��� ����)
--�޿��� 10% ���ʽ��� ����Ͽ� �̸�, Ư�� ���ʽ� (��� �ݾ�) ����� ����ϼ���. // ����? �ƴϸ� 10%, 20%, 30%? �ϴ� ���ڴ� ���� ��. ���ڷ�.
--��, �̸� ������ �������� �����Ͽ� ����ϼ���.

select
    emp_name,
    (salary * (floor((floor(sysdate - hire_date)/1000)) * 0.1)) as "Ư�� ���ʽ�"
from employee
where
    emp_no like '_______1%' and
    bonus is null;