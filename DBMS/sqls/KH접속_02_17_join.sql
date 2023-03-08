--Review
--group by & having
--from -> where -> group by -> having -> select -> oreder by

--JOIN
--�ϳ� �̻��� ���̺�κ��� Ư�� ������ �����ϴ� �����͸� �����Ͽ� ����� �����ϴ� ����
--JOIN���� ��ø for���̴�.



select * from department;
select * from job;


-- ���� ����

--> ANSI ǥ�� (�� ���� �� �� ������ Oracle ���� ���� ���� ����)
--Cross Join 
select * from department cross join job;
--Inner Join
select * from employee inner join department on dept_code = dept_id;
select * from employee e inner join job j on e.job_code = j.job_code;
select
    emp_id,
    emp_name,
    job_code,
    job_name
from employee
inner join job using (job_code) -- ���� ���� ���� ���� �� ������ ��Ī�� ����� �� ����.
where job_name = '����';

--> Oracle ����
-- JOIN ���� �� - ũ�ν� ���� ( Cross Join - Cartesian Product �� ������. )
select * from department, job;
-- JOIN ���� �� - Inner JOIN : Join�� ������ ���� ���̺� �������� Ư�� ������ �����ϴ� ���ڵ常�� �����Ͽ� ��� ����
select "e".emp_id, "e".emp_name, "d".dept_title
from employee "e", department "d"
where "e".dept_code = "d".dept_id;


--------------------------
--Quiz
--1. ��������ο� ���� ������� �̸�, �μ��ڵ�, �޿��� ����ϼ���.
select
    "e".emp_name as "�̸�",
    "e".dept_code as "�μ��ڵ�",
    "e".salary as "�޿�"
from employee "e"
inner join department "d"
on "e".dept_code = "d".dept_id
where "d".dept_title = '���������';

--2. �ؿܿ���n�� �������� ���, �����, �μ���, ������ ����غ�����.
select * from department;
select
    e.emp_id as "���",
    e.emp_name as "�����",
    d.dept_title as "�μ���",
    (e.salary * 12) as "����"
from employee e
inner join department d
on e.dept_code = d.dept_id
where d.dept_title like ('�ؿܿ���%');

----------------------------------------------
--outer join : join�� �����ϴ� �� ���̺��� ���� ���̺��� ��� ���� ���� ����ϰ� ����� ����
select
    nvl(emp_name, '�����ο�����'),
    nvl(dept_code, '�μ��ڵ����'),
    nvl(dept_title, '�μ������')
from employee
full outer join department 
on dept_code = dept_id 
order by 2;
-------------------------------------------------------
-- self join : ���� ���̺�/�ڱ� �ڽ��� JOIN�ϴ� ����
select * from job j1 cross join job j2;
select emp_id, emp_name, manager_id from employee; --�̷� ��Ȳ�� ����ϱ� ���ؼ� �ʿ�. 

select
    e2.emp_id as "���",
    e2.emp_name as "�����",
    e2.manager_id as "�Ŵ��� ���",
    e1.emp_name as "�Ŵ�����"
from employee e1
inner join employee e2
on e2.manager_id = e1.emp_id;
--order by e1.emp_id;

----------------------------------------------
--Quiz
--1. ���������� ���� �μ����� ���ϴ� �������� �̸�, �μ��ڵ�, �����ڵ带 ������ּ���. (self join Ȱ��)
select
    e2.emp_name as "������",
    e2.dept_code as "�μ��ڵ�",
    e2.job_code as "�����ڵ�"
from employee e1
inner join employee e2
on e1.emp_name = '������'
where e1.dept_code = e2.dept_code and e2.emp_name != '������';
--�Ǵ�
select
    e2.emp_name,
    e2.dept_code,
    e2.job_code
from employee e1
inner join employee e2 on e1.dept_code = e2.dept_code
where e1.emp_name = '������'
order by 2;

-----------------------------------------------------
--���� JOIN : 3�� �̻��� ���̺��� JOIN�Ͽ� ���ǹ��� �����͸� �����ϴ� �۾�.

--������, �μ���, ���޸��� ������ּ���.
select
    e.emp_name,
    d.dept_title,
    j.job_name
from employee e
-- 1
left outer join department d on e.dept_code = d.dept_id 
-- 2. 1�� ��������� ���� �� �� job_code�� ���ٸ�.
inner join job j on e.job_code = j.job_code
order by e.job_code;

---------------------------------------------
--Quiz
--1. �����, �μ���, �ٹ� ���� �ڵ�(National code)�� ����ϼ���.
select * from department;
select * from location;
select * from national;

select
    e.emp_name as "�����",
    d.dept_title as "�μ���",
    n.national_name as "�ٹ� ����"
from employee e
inner join department d
    on e.dept_code = d.dept_id
inner join location l
    on d.location_id = l.local_code
inner join national n
    on l.national_code = n.national_code;
    
----------------------------------------------------

--JOIN ������ �߰����� ����
-- 1. 2020�� 12�� 25���� ���� �������� ��ȸ�ϼ���.
select to_char(to_date('20201225', 'YYYYMMDD'),'DAY') from dual;

-- 2. �ֹι�ȣ�� 1970��� ���̸鼭 ������ �����̰�, ���� ������ �������� �����, �ֹι�ȣ, �μ���, ���޸��� ��ȸ�ϼ���.
select
    e.emp_name as "�����",
    e.emp_no as "�ֹι�ȣ",
    d.dept_title as "�μ���",
    j.job_name as "���޸�"
from employee e
inner join department d
    on e.dept_code = d.dept_id
inner join job j
    on e.job_code = j.job_code
where
    substr(e.emp_no,1,1) = '7' and
    substr(e.emp_no,8,1) = '2' and
    e.emp_name like '��%';

-- 3. �̸��� "��"�ڰ� ���� �������� ���, �����, �μ����� ��ȸ�ϼ���.
select
    e.emp_id as "���",
    e.emp_name as "�����",
    d.dept_title as "�μ���"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
where
    e.emp_name like '%��%';

-- 4. �μ��� �޿� ����� 300���� ������ �μ��� �μ���� �޿� ����� ����ϼ���
-- > �޿� ����� 10000�� �ڸ� �Ʒ��� ������ ������ּ���. 
-- > ���� �ѱ� ���� ��ȭ ǥ������� ������ּ���. (��3,520,000)
select
    nvl(d.dept_title,'�μ�����') as "�μ���",
    to_char(trunc(avg(salary),-4),'L999,999,999,999') as "�޿� ���"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
group by nvl(d.dept_title,'�μ�����')
having
    avg(salary) <= 3000000;

-- 5. �ؿܿ����ο� �ٹ��ϴ� �����, ���޸�, �μ��ڵ�, �μ����� ��ȸ�ϼ���.
select
    e.emp_name as "�����",
    j.job_name as "���޸�",
    e.dept_code as "�μ��ڵ�",
    d.dept_title as "�μ���"
from employee e
inner join department d
    on e.dept_code = d.dept_id
inner join job j
    on e.job_code = j.job_code
where
    d.dept_title like '�ؿܿ���%'
order by e.dept_code; -- order by �� �������� ���� �߰�.

-- 6. ���ʽ�����Ʈ�� �޴� �������� �����, ���ʽ�����Ʈ, �μ���, �ٹ��������� ��ȸ�ϼ���.
select
    e.emp_name as "�����",
    (e.salary * e.bonus) as "���ʽ�����Ʈ",
    d.dept_title as "�μ���",
    l.local_name as "�ٹ�������"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
inner join location l
    on d.location_id = l.local_code
where bonus is not null
order by d.dept_title; -- order by�� �������� ���� �߰�

-- 7. �μ��ڵ尡 D2�� �������� �����, ���޸�, �μ���, �ٹ��������� ��ȸ�ϼ���.
select
    e.emp_name as "�����",
    j.job_name as "���޸�",
    d.dept_title as "�μ���",
    l.local_name as "�ٹ�������"
from employee e
inner join job j
    on e.job_code = j.job_code
inner join department d
    on e.dept_code = d.dept_id
inner join location l
    on d.location_id = l.local_code
where
    e.dept_code = 'D2';

-- 8. �ѱ�(KO)�� �Ϻ�(JP)�� �ٹ��ϴ� �������� �����, �μ���, �ٹ�������, �������� ��ȸ�ϼ���.
select
    e.emp_name as "�����",
    d.dept_title as "�μ���",
    l.local_name as "�ٹ�������",
    n.national_name as "������"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
inner join location l
    on d.location_id = l.local_code
inner join national n
    on l.national_code = n.national_code
where
    n.national_code = 'KO' or
    n.national_code = 'JP'
order by d.dept_title; -- order by�� �������� ���� �߰�

-- 9. ��� ��� �� �������� �Ŵ����� �ΰ� �ִ� ������ �̸�, ������ �μ���, �Ŵ����� �̸��� ����ϼ���.
select
    e2.emp_name as "�����",
    d.dept_title as "�μ���",
    e1.emp_name as "�Ŵ�����"
from employee e1
inner join employee e2
    on e2.manager_id = e1.emp_id
inner join department d
    on e2.dept_code = d.dept_id
where e1.emp_name = '������'
order by d.dept_title; -- order by�� �������� ���� �߰�

-- 10. ���ʽ�����Ʈ�� ���� ������ �߿��� ������ ����� ����� �������� �����, ���޸�, �޿��� ��ȸ�ϼ��� (join�� in ���)
select
    e.emp_name as "�����",
    j.job_name as "���޸�",
    to_char(e.salary, 'L999,999,999,999') as "�޿�"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
inner join job j
    on e.job_code = j.job_code
where
    e.bonus is null and
    j.job_name in ('����', '���');

-- 11. �������� ������ ����� ������ ���� ��ȸ�ϼ���.
select
    count(distinct(e2.emp_name)) as "�������� ������ ��",
    count(distinct(e1.emp_name)) as "����� ������ ��",
    count(distinct(e3.emp_name)) as "�� ������"
from employee e1
inner join employee e2
    on e1.ent_yn = 'Y'
inner join employee e3
    on e2.ent_yn = 'N';
--�Ǵ� (Ÿ�� Ǯ�� ����)
select
    decode(ent_yn, 'Y', '����', 'N', '����') as "��������",
    count(*) as "�ο���"
from employee
group by decode(ent_yn, 'Y', '����', 'N', '����');
--�Ǵ� (����� ȭ�� ����)
select
    (select count(*) from employee where ent_yn = 'Y') as "������ ��", 
    (select count(*) from employee where ent_yn = 'N') as "������ ��"
from dual;
