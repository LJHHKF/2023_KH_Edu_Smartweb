--function() == method()

--nvl(�˻絥����, �˻絥���Ͱ� null�϶� ġȯ �� ��)

--length() : Ư�� �������� ���̸� �����Ͽ� ��ȯ
select length('�ѱ�') from dual;
--lengthb() : Ư�� �������� byte�� �����Ͽ� ��ȯ
select lengthb('�ѱ�') from dual;

--instr() : ���ڿ� �ȿ��� Ư�� ������ ��ġ ���� ã�� �Լ�(java�� indexOf)
select instr('Hello World Hi High', 'H', 1, 2) from dual;
-- 'Hello World Hi High' ���ڿ� �ȿ��� H�� �˻��ϵ� 1�� ���ڿ������� ���������� �˻��ذ��� 1������ ������ 'H'�� ��ġ

--substr() : ���ڿ� ������ ������ ��ġ�� ���ڿ��� �߶󳻾� ��ȯ�ϴ� �Լ� (Java�� subString)
select substr('Hello World Hi High', 1, 3) from dual;
select substr('�ȳ� ���� �ȳ� ����', 4, 2) from dual;

--Quiz
--1. ���� ��ܿ��� �̸��� ���� �����Ͽ� ����غ��ô�.
select distinct substr(emp_name, 1, 1) as "��" from employee;

--2. ������ܿ��� �����ȣ, �����, �ֹι�ȣ, ������ ����ϼ���.
--  ��, �ֹι�ȣ �� 6�ڸ��� * ��ȣ�� ����ϼ���.
--  ���������� ������ ����ϼ���. (like ��� ����)
select
    emp_id,
    emp_name,
    (substr(emp_no,1,8) || '******') as "EMP_NO", -- || �� Java���� '+ (String Append)'�� �ǹ���.
    (salary * 12) as "����"
from employee
where substr(emp_no, 8, 1) = 1;

--abs() : ���� ����(���밪)�� ��ȯ�ϴ� �Լ�
--  ���� ���� �� ���ô� ���� �� ��ȯ.
select abs(2) from dual;
select abs(-2) from dual;

--mod() : Java���� ġ�� '%' ����. -> ������ ���� �Լ�
select mod(10,3) from dual;
select mod(10,2) from dual;
select mod(10,4) from dual;

--round() : �ݿø� �Լ�
select round(126.456) from dual;
select round(126.456, 2) from dual; -- 126.46

--floor() : �Ҽ��� ���� �Լ�
--trunc() : Ư�� ��ġ���� ���� �Լ�.
select trunc(126.456, 1) from dual;

--ceil() : �ø� �Լ�
--�ø��� ������ �Ҽ��� ���� �Լ� ����. �����ؼ� �÷��� ��.
select ceil(126.456) from dual; -- 127;

--------------------------------------------------
--sysdate : ��ȸ�ϴ� ������ �ð� ���� Date Ÿ������ ��ȯ.
select sysdate from dual;
--months_between : �� ��¥������ ���� ���� ��ȯ�ϴ� �Լ�.
select emp_name, floor(months_between(sysdate, hire_date)) as "�ټӰ�����" from employee;
--add_months : Ư�� ��¥�� �������� ���ϴ� �Լ�
select add_months(sysdate, 4) from dual;

--next_day : ������¥�� �������� ���� ����� ���� date���� ��ȯ
select next_day(sysdate, '������') from dual;

--last_day : ������¥�� ���� ���� ������ �� date ���� ��ȯ.
select last_day(sysdate) from dual;

--Quiz
--���� �� ������ ��¥�� �����ϱ��?
select last_day(add_months(sysdate,1)) from dual;

--extract : ��¥�����κ��� ��, ��, ���� �����ϴ� �Լ�
select extract(year from sysdate) from dual;
select extract(month from sysdate) from dual;
select extract(day from sysdate) from dual;

--Quiz
--1. ���, �����, �Ի�⵵(0000��) �������� ����غ�����.
select
    emp_id,
    emp_name,
    extract(year from hire_date)||'��' as "�Ի�⵵"
from employee;

--2. Employee ���̺��� ����� �̸�, hire_date, ������ ����ϼ���.
--���� ����� �Ҽ����� ��� �ø����� �Ͽ� ����ϼ���. (28.144 -> 29����)
--(��� �� ������ �Ի��� �������� ��������)
select
    emp_name,
    hire_date,
    ceil((sysdate - hire_date)/365) as "����"
from employee
order by hire_date asc;

-----------------------------------------------------------
--to_char(~,[��¸��]) : ��¥�� �Ǵ� ���ڰ��� ���ڿ��� ��ȯ�ϴ� �Լ�.
select to_char(sysdate, 'YYYY"��" MM"��" DD"��" (DY) HH:MI:SS') from dual;
select to_char(1000000, '999,999,999') from dual;
select to_char(1000000, 'L999,999,999') from dual;

--Quiz
--�����, �����(1990/02/06(ȭ)) ���·� ������ּ���.
select
    emp_name,
    to_char(hire_date, 'YYYY/MM/DD(DY)') as "�����"
from employee;

--to_date(~,[�Է¸��]) : ���ڿ��� ��¥������ ��ȯ�ϴ� �Լ�
select to_date('20180208', 'YYYYMMDD') from dual;

--Quiz
--1945�� 8�� 15���� ���� ���� �����ϱ�.
select to_char(to_date('19450815','YYYYMMDD'),'DAY') from dual;

-----------------------------------------------------
--decode() : ���� ���� �б����� ����� �Լ�. (�ڹ��� ���׿�����, switch)
--( (�Է°�) , (Case1 ����), (Case1 ���), (Case2 ����), (Case2 ���), ��, (else case ���) )
select decode('Apple', 'Apple', '���', 'Orange', '������', '����') from dual;

--Quiz
--��� ��ȣ, �����, ���� (��,��) ���·� ����ϼ���.
select
    emp_id,
    emp_name,
    decode(substr(emp_no, 8, 1),
        '1', '��',
        '2', '��',
        '3', '��',
        '4', '��',
        emp_no) as "����"
from employee;

-------------------------------------------------------------
--case : ���ǽĿ� ���� ��ȯ�� �б��� ���� �Լ�
select
    emp_id,
    emp_name,
    case
        when (substr(emp_no,8,1) = '1' or substr(emp_no,8,1) = '3') then '��'
        when (substr(emp_no,8,1) = '2' or substr(emp_no,8,1) = '4') then '��'
        else '??'
    end as "����"
from employee;

--Quiz
--1. 60���� ������ �߿��� 60 ~ 64�� ������ �������� '60��� �ʹ�' �̶�� ���.
--              65~69�� ������ �������� '60��� �Ĺ�'�̶�� ���
--  ���, �̸�, 60��� ��/�Ĺ�
select
    emp_id,
    emp_name,
    case
        when substr(emp_no,2,1) <= 4 then '60��� �ʹ�'
        when (substr(emp_no,2,1) >= 5 and substr(emp_no,2,1) <= 9) then '60��� �Ĺ�'
        else '����'
    end as "���̴�"
from employee
where emp_no like '6%';

---- ��������� ��� ������ �Լ�
-------------------------------------------------------------------
-- �׷� �Լ� (SUM, AVG, COUNT, MAX, MIN, ...)

--�� ȸ���� �Ѵ� �ΰǺ�� ���ΰ�? (Salary�� ��)
--SUM : �հ� �Լ�
select sum(salary) from employee;

--AVG : ��� �Լ�
select avg(salary) from employee;

-- COUNT : ���� ������ ��ȯ�ϴ� �Լ�
select count(*) from employee;

-- MAX : ��ü �࿡�� ���� ū ���� ��ȯ�ϴ� �Լ�
select max(salary) from employee;
-- MIN : ��ü �࿡�� ���� ���� ���� ��ȯ�ϴ� �Լ�
select min(salary) from employee;

--Quiz
--1. D5 �μ� �������� �޿� ���
select round(avg(salary)) from employee where dept_code = 'D5';
--2. ���������� �޿� �հ�
select sum(salary) from employee where emp_no like '%-1%';

-------------------------------------------------------------
--Quiz
--1. ������� �̸��� , �̸��� ���̸� ����ϼ���
--        �̸�       �̸���      �̸��ϱ���
--   ex)    ȫ�浿 , hong@kh.or.kr        13
select
    emp_name as "������",
    email as "�̸���",
    length(email) as "�̸��ϱ���"
from employee;

--2. ������ �̸��� �̸��� �ּ��� ���̵� �κи� ����ϼ���
--   ex) ���ö   no_hc
--   ex) ������   jung_jh
select
    emp_name as "������",
    substr(email, 1, instr(email, '@', 1, 1)-1) as "�̸���"
from employee;

--3. 60����� ������� ���, ���ʽ� ���� ����ϼ���
--   �׶� ���ʽ� ���� null�� ��쿡�� 0% �̶�� ��� �ǰ� ���弼��
--       ������    ���     ���ʽ�
--   ex) ������     62       30%
--   ex) ������     63       0%
select
    emp_name as "������",
    substr(emp_no, 1, 2) as "���",
    nvl (bonus*100, 0)||'%' as "���ʽ�"
from employee
where emp_no like '6%';

--4. '010' �ڵ��� ��ȣ�� ���� �ʴ� ����� ���� ����ϼ��� (�ڿ� ������ ���� ���̼���)
--      �ο�
--   ex) 3��
select count(*)||'��' as "010�� �ƴ� ��� ��" from employee
    where
        phone not like '010%' or
        phone is null;

-- �����. 4�� �������� phone ��ȣ�� null�� ��쵵 '010'��ȣ�� �ƴ� ���� �����ϰڽ��ϱ�? 
-- �ƴϸ� ���� �����鼭 010�� �ƴ� ��츸 ���� �ǰڽ��ϱ�? 
-- "��ȭ��ȣ�� null �� ����� 010�� �ƴ� ������� ī���� �սô�."


--5. ������� �Ի����� ����ϼ��� 
--   ��, �Ʒ��� ���� ��µǵ��� ����� ������
--       ������         �Ի���
--   ex) ������      2012��12��
--   ex) ������      1997�� 3��
select
    emp_name as "������",
    to_char(hire_date, 'YYYY"��"MM"��"') as "�Ի���"
from employee;

--6. ������� �ֹι�ȣ�� ��ȸ�ϼ���
--   ��, �ֹι�ȣ 9��° �ڸ����� �������� '*' ���ڷ� ä���� ��� �ϼ���
--   ex) ȫ�浿 771120-1******
select
    emp_name as "������",
    substr(emp_no, 1, 8)||'******' as "�ֹι�ȣ"
from employee;

--7. ������, �����ڵ�, ����(��)�� ��ȸ�ϼ���.
--  ��, ������ ��57,000,000 ���� ǥ�õǰ� �� (���� ��ȭ �ܰ� ���� �Ǿ�� ��)
--     ������ ���ʽ�����Ʈ�� ����� 1��ġ �޿���
select
    emp_name as "������",
    job_code as "�����ڵ�",
    case
        when bonus is not null then to_char((salary * 12) + (salary *12 * bonus), 'L999,999,999,999')
        else to_char((salary * 12), 'L999,999,999,999')
    end as "����" 
from employee;

--8. �μ��ڵ尡 D5, D9�� ������ �߿��� 2004�⵵�� �Ի��� ������ 
--   �� ��ȸ�ϼ���.
--   [ ��� ����� �μ��ڵ� �Ի��� ]
select emp_id as "���", emp_name as "�����", dept_code as "�μ��ڵ�" , hire_date as "�Ի���" from employee
    where
        (dept_code = 'D5' or dept_code = 'D9') and
        extract(year from hire_date) = 2004;

--9. ������, �Ի���, ���ñ����� �ٹ��ϼ� ��ȸ�ϼ���. 
--   * �ָ��� ���� , �Ҽ��� �Ʒ��� ����
select
    emp_name as "������",
    hire_date as "�Ի���",
    floor(sysdate - hire_date) as "�ٹ��ϼ�"
from employee;

--10. ��� ������ ���� �� ���� ���� ���̿� ���� ���� ���̸� ��� �ϼ���. (���̸� ���)
--      �ϱ��� ��� �����ϸ�, '621235' ���� �̻��� �ϼ� ������ �־ ������. ���� ����θ� ���.
--      ��� ������ 1900�����̶� ����
--      �׷��� ������ �⺻������ 2000��� ������ ����Ͽ�, 2060��� ������� ���� ���̰� ����.
select
    max(floor((sysdate - to_date('19'||substr(emp_no,1,2),'YYYY'))/365) + 1) as "�ְ��", --�ѱ����� ����. +1
    min(floor((sysdate - to_date('19'||substr(emp_no,1,2),'YYYY'))/365) + 1) as "�ֿ���"
from employee;

--11.  ȸ�翡�� �߱��� �ؾ� �Ǵ� �μ��� ��ǥ�Ͽ��� �մϴ�.
-- �μ��ڵ尡 D5,D6,D9  �߱�, �׿ܴ� �߱پ��� ���� ��µǵ��� �ϼ���
-- ��� ���� �̸�,�μ��ڵ�,�߱����� (�μ��ڵ� ���� �������� ������.)
--   (�μ��ڵ尡 null�� ����� �߱پ��� ��)
select
    emp_name as "�̸�",
    dept_code as "�μ��ڵ�",
    case
        when (dept_code = 'D5' or dept_code = 'D6' or dept_code = 'D9') then '�߱�'
        else '�߱پ���' -- null�� else�� ���Ե�.
    end as "�߱�����"
from employee
order by dept_code asc nulls first; -- nulls first�� ����� ���� �뵵�� ���� �߰�