--group by : �׷�ȭ �� �����Ϳ� ���� ������ �ٷ�� ����

select dept_code as "�μ��ڵ�", sum(salary) as "�μ� �� �޿� �հ�"
from employee
group by dept_code 
order by dept_code asc;

-- from -> where -> group by -> having -> select -> order by

-- Quiz
-- 1.  ���� �� �޿� ����� ������ּ���. (���� �� ������������ �����Ͽ� ���)
-- null ���� Intern ���� ǥ��
select 
    nvl(job_code, 'Intern') as "����",
    to_char(avg(salary), 'L999,999,999,999') as "���� �� �޿� ���"
from employee
group by nvl(job_code, 'Intern')
order by job_code asc;

select decode(substr(emp_no,8,1), '1', '��', '2', '��') as "����"
from employee
group by decode(substr(emp_no,8,1), '1', '��', '2', '��');

--���� �� �ο����� ����غ�����.
select
    decode(substr(emp_no,8,1), '1', '��', '2', '��') as "����",
    count(*)||'��' as "���� �� �ο���"
from employee
group by decode(substr(emp_no,8,1), '1', '��', '2', '��');

--���� �� �޿� ��յ� ����غ�����.
--J1�� J2�� �� ������ �ο�����
select
    decode(substr(emp_no,8,1), '1', '��', '2', '��') as "����",
    to_char(avg(salary), 'L999,999,999,999') as "�޿� ���"
from employee
where job_code in ('J3', 'J4', 'J5', 'J6', 'J7')
group by decode(substr(emp_no,8,1), '1', '��', '2', '��');

--���̴� �� �޿� ���
--�޿� ����� 100000�� �ڸ����� �ݿø� �� ��
--�޿� ��� ����� ���� ��ȭ ��� (ex> \2,542,030) ���·� ���
select
    substr(emp_no,1,1)||'0���' as "���̴�",
    to_char(round(avg(salary),-5), 'L999,999,999,999') as "�޿� ���"
from employee
group by substr(emp_no,1,1)||'0���'
order by 1;
--�Ǵ�
select
    case
        when substr(emp_no,1,2) between 60 and 69 then '60����'
        when substr(emp_no,1,2) between 70 and 79 then '70����'
        when substr(emp_no,1,2) between 80 and 89 then '80����'
        when substr(emp_no,1,2) between 90 and 99 then '90����'
    end as "���̴�",
    to_char(round(avg(salary),-5), 'L999,999,999,999') as "�޿� ���"
from employee
group by
    case
        when substr(emp_no,1,2) between 60 and 69 then '60����'
        when substr(emp_no,1,2) between 70 and 79 then '70����'
        when substr(emp_no,1,2) between 80 and 89 then '80����'
        when substr(emp_no,1,2) between 90 and 99 then '90����'
    end
order by 1;
--�Ǵ�
select
    decode(substr(emp_no,1,1),'6','60����', '7','70����', '8','80����') as "���̴�",
    to_char(round(avg(salary),-5), 'L999,999,999,999') as "�޿� ���"
from employee
group by decode(substr(emp_no,1,1),'6','60����', '7','70����', '8','80����')
order by 1;

--order by dept_code, salary
--�μ��ڵ�� ������ ����, �� ��� �߿��� �޿� �������� �ٽñ� �������ּ���.

--group by dept_code, job_code
--�μ� �ڵ� �������� ���� ���� ����, �� �ȿ��� �ٽ� ���� ���� ���� ��.
-- == ���� �μ� ��, ���� ���� ���� ���� ���̼���.

--�μ� �� ���� �� �ο��� �ľ�
select
    dept_code as "�μ� �ڵ�",
    job_code as "���� �ڵ�",
    count(*) as "�ο���"
from employee
group by dept_code, job_code
order by 1, 2;

--Quiz
--1. �μ� �� ���� �� �ο� �� �ľ�
select
    nvl(dept_code, '�μ�����') as "�μ��ڵ�",
    decode(substr(emp_no, 8, 1), '1', '��', '2', '��', 'null') as "����",
    count(*) as "�ο���"
from employee
group by
    nvl(dept_code, '�μ�����'),
    decode(substr(emp_no, 8, 1), '1', '��', '2', '��', 'null')
order by 1;

--2. �μ� �� �޿� ����� 300���� �̻��� �μ��� �μ��ڵ� �� �޿������ ����ϼ���.
--  group by �� ���� �׷�ȭ�� �����Ϳ� ���� ������ ���� ���� having ���� ���.
select
    nvl(dept_code, '�μ�����') as "�μ��ڵ�",
    floor(avg(salary)) as "�޿����"
from employee
group by nvl(dept_code, '�μ�����')
having avg(salary) >= 3000000
order by 1;

--Quiz
--1. �ο��� 3�� �̸��� ���� �ڵ��� �ο��� ����Ϸ��� ��.
--�ο��� 3���� �ȵǴ� �����ڵ忡 ���Ͽ� �����ڵ�� �ο����� ����ϼ���.
select
    job_code as "�����ڵ�",
    count(*) as "�ο���"
from employee
group by job_code
having count(*) < 3
order by job_code;