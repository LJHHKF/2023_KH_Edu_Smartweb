--[Basic SELCET]
--1. �� ��� ���б��� �а� �̸��� �迭�� ǥ���Ͻÿ�. ��, ��� ����� "Ȯ����", "�迭"���� ǥ���ϵ��� �Ѵ�.
select
    DEPARTMENT_NAME as "�а���",
    CATEGORY as "�迭"
from TB_DEPARTMENT;

--2. �а��� �а� ������ ������ ���� ���·� ȭ�鿡 ����Ѵ�.
--(PDF ����. �а��� ����, �� ~~�а��� ������ ~~�� �Դϴ�.)
select
    (DEPARTMENT_NAME ||'�� ������'||CAPACITY||'�� �Դϴ�.') as "�а��� ����"
from TB_DEPARTMENT;

--3. "������а�"�� �ٴϴ� ���л� �� ���� �������� ���л��� ã�ƴ޶�� ��û�� ���Դ�. �����ΰ�?
--(�����а��� '�а��ڵ�'�� �а� ���̺�(TB_DEPARTMENT)�� ��ȸ�ؼ� ã�� ������ ����)
select STUDENT_NAME
from TB_STUDENT
where
    DEPARTMENT_NO =
        (select DEPARTMENT_NO 
        from TB_DEPARTMENT 
        where DEPARTMENT_NAME = '������а�')
    and ABSENCE_YN = 'Y';

--4. ���������� ���� ���� ��� ��ü�ڵ��� ã�� �̸��� �Խ��ϰ��� �Ѵ�.
--�� ����ڵ��� �й��� ������ ���� �� ����ڵ��� ã�� ������ SQL ������ �ۼ��Ͻÿ�.
--A513079, A513090, A513091, A513110, A513119
select STUDENT_NAME
from TB_STUDENT
where STUDENT_NO in ('A513079', 'A513090', 'A513091', 'A513110', 'A513119');

--5. ���������� 20�� �̻�, 30�� ������ �а����� �а� �̸��� �迭�� ����Ͻÿ�.
select
    DEPARTMENT_NAME, CATEGORY
from TB_DEPARTMENT
where CAPACITY between 20 and 30;

--6. �� ������б��� ������ �����ϰ� ��� �������� �Ҽ� �а��� ������ �ִ�.
--�׷� �� ������б� ������ �̸��� �˾Ƴ� �� �ִ� SQL ������ �ۼ��Ͻÿ�.
select PROFESSOR_NAME
from TB_PROFESSOR
where DEPARTMENT_NO is null;

--7.Ȥ�� ������� ������ �а��� �����Ǿ� ���� ���� �л��� �ִ��� Ȯ���ϰ��� �Ѵ�.
--��� SQL ������ ����ϸ� �� ������ �ۼ��Ͻÿ�.
select
    STUDENT_NO,
    STUDENT_NAME
from TB_STUDENT s
where s.DEPARTMENT_NO not in
    (select d.DEPARTMENT_NO
    from TB_DEPARTMENT d);

--8.������û�� �Ϸ��� �Ѵ�. �������� ���θ� Ȯ���ؾ� �ϴµ�,
--���������� �����ϴ� ������� � �������� �����ȣ�� ��ȸ�غ��ÿ�.
select CLASS_NO
from TB_CLASS
where PREATTENDING_CLASS_NO is not null;

--9. �� ���п��� � �迭(CATEGORY)���� �ִ��� ��ȸ�غ��ÿ�.
select distinct(CATEGORY)
from TB_DEPARTMENT;

--10. 02�й� ���� �����ڵ��� ������ ������� �Ѵ�.
--������ ������� ������ �������� �л����� �й�, �̸�, �ֹι�ȣ�� ����ϴ� ������ �ۼ��Ͻÿ�.
select
    STUDENT_NO,
    STUDENT_NAME,
    STUDENT_SSN
from TB_STUDENT
where
    ABSENCE_YN = 'N'
    and extract(year from ENTRANCE_DATE) = extract(year from to_date('02', 'YY'))
    and STUDENT_ADDRESS like '���ֽ�%';

---------------------------------------------------------------------

--[Additional SELECT - �Լ�]
--1. ������а�(�а��ڵ� 002) �л����� �й��� �̸�, ���� �⵵��
--���� �⵵�� ���� ������ ǥ���ϴ� SQL ������ �ۼ��Ͻÿ�.
select
    STUDENT_NO as "�й�",
    STUDENT_NAME as "�̸�",
    ENTRANCE_DATE as "���� �⵵"
from TB_STUDENT s
where
    s.DEPARTMENT_NO =
        (select d.DEPARTMENT_NO
        from TB_DEPARTMENT d 
        where d.DEPARTMENT_NAME = '������а�')
order by ENTRANCE_DATE asc;

--2.�� ������б��� ���� �� �̸��� �� ���ڰ� �ƴ� ������ �� �� �ִٰ� �Ѵ�.
--�� ������ �̸��� �ֹι�ȣ�� ȭ�鿡 ����ϴ� SQL ������ �ۼ��� ����.
--(�� �� �ùٸ��� �ۼ��� SQL ������ ��� ���� ����� �ٸ��� ���� �� �ִ�. ������ �������� �����غ� ��.)
select
    PROFESSOR_NAME,
    PROFESSOR_SSN
from TB_PROFESSOR
where length(PROFESSOR_NAME) != 3;

--3.�� ������б��� ���� �������� �̸��� ���̸� ����ϴ� SQL ������ �ۼ��Ͻÿ�.
--��, �̋� ���̰� ���� ������� ���� ��� ������ ȭ�鿡 ��µǵ��� ����ÿ�.
--(��, ���� �� 2000�� ���� ����ڴ� ������ ��� ����� "�����̸�", "����"�� �Ѵ�. ���̴� '��'���� ����Ѵ�.)
select
    PROFESSOR_NAME as "�����̸�",
    floor((sysdate - to_date('19'||substr(PROFESSOR_SSN, 1, 6), 'YYYYMMDD'))/365)||'��' as "����"
from TB_PROFESSOR
where substr(PROFESSOR_SSN,8,1) = '1'
order by "����" asc;

--4. �������� �̸� �� ���� ������ �̸��� ����ϴ� SQL ������ �ۼ��Ͻÿ�.
--��� ����� "�̸�"�� �������� �Ѵ�. (���� 2���� ����� ������ ���ٰ� �����Ͻÿ�)
select
    substr(PROFESSOR_NAME, 2, 2) as "�̸�"
from TB_PROFESSOR;

--5. �� ������б��� ����� �����ڸ� ���Ϸ��� �Ѵ�. ��� ã�Ƴ� ���ΰ�?
--�� ��, 19�쿡 �����ϸ� ����� ���� ���� ������ �����Ѵ�.
select
    STUDENT_NO,
    STUDENT_NAME
from TB_STUDENT
where
    19 <= (ENTRANCE_DATE - to_date('19'||substr(STUDENT_SSN, 1, 6), 'YYYYMMDD'))/365 ;
    --������ ���� ��.
    --������� ������ 204���� �ƴ϶� 249���̶� ���� �߸��Ȱǰ� ������ �ϴ� �ѱ�.

--6. 2020�� ũ���������� ���� �����ΰ�?
select to_char(to_date('20201225', 'YYYYMMDD'), 'DAY') from dual;

--7. TO_DATE('99/10/11', 'YY/MM/DD'), TO_DATE('49/10/11', 'YY/MM/DD')�� ���� ��� ��� ������ �ǹ��ұ�?
--�� TO_DATE('99/10/11', 'RR/MM/DD'), TO_DATE('49/10/11', 'RR/MM/DD')�� ���� �� �� ��� ������ �ǹ��ұ�?
select to_char(to_date('99/10/11', 'YY/MM/DD'), 'YYYYMMDD') from dual;
--���۸� & �׽�Ʈ ���
--a. TO_DATE('99/10/11', 'YY/MM/DD') = 2099.10.11
--b. TO_DATE('49/10/11', 'YY/MM/DD') = 2049.10.11
--c. TO_DATE('99/10/11', 'RR/MM/DD') = 1999.10.11
--d. TO_DATE('49/10/11', 'RR/MM/DD') = 2049.10.11

--8. �� ������б��� 2000�⵵ ���� �����ڵ��� �й��� A�� �����ϰ� �Ǿ��ִ�.
--2000�⵵ ���� �й��� ���� �л����� �й��� �̸��� �����ִ� SQL ������ �ۼ��Ͻÿ�.
select
    STUDENT_NO,
    STUDENT_NAME
from TB_STUDENT
where STUDENT_NO not like 'A%';

--9. �й��� A517178 �� �ѾƸ� �л��� ���� �� ������ ���ϴ� SQL���� �ۼ��Ͻÿ�.
--��, �̶� ��� ȭ���� ����� "����"�̶�� ������ �ϰ�, ������ �ݿø��Ͽ� �Ҽ��� ���� �� �ڸ������� ǥ���Ѵ�.
--(�Ƹ� �� �����̶��� ������� ����ε� ��.)
select round(avg(POINT),1) as "����"
from TB_GRADE 
where STUDENT_NO = 'A517178';
--�Ǵ�
select round(avg(POINT),1) as "����"
from TB_GRADE g
where
    g.STUDENT_NO =
        (select s.STUDENT_NO
        from TB_STUDENT s
        where s.STUDENT_NO = 'A517178' and s.STUDENT_NAME = '�ѾƸ�');

--10. �а��� �л����� ���Ͽ� "�а���ȣ", "�л���(��)" �� ���·� ����� ����� ������� ��µǵ��� �Ͻÿ�
select
    DEPARTMENT_NO as "�а���ȣ",
    count(*) as "�л���(��)"
from TB_STUDENT
group by DEPARTMENT_NO
order by "�а���ȣ"; -- ������ ���� �߰�

--11. ���� ������ �������� ���� �л��� ���� �� �� ���� �Ǵ� �˾Ƴ��� SQL���� �ۼ��Ͻÿ�.
select count(*)
from TB_STUDENT
where COACH_PROFESSOR_NO is null;

--12. �й��� A112113 �� ���� �л��� �⵵ �� ������ ���ϴ� SQL���� �ۼ��Ͻÿ�.
--��, �̶� ��� ȭ���� ����� "�⵵", "�⵵ �� ����"�̶�� ������ �ϰ�,
--������ �ݿø��Ͽ� �Ҽ��� ���� �� �ڸ������� ǥ���Ѵ�.
select
    substr(TERM_NO, 1, 4) as "�⵵",
    round(avg(POINT),1) as "�⵵ �� ����"
from TB_GRADE
where STUDENT_NO = 'A112113' 
group by substr(TERM_NO, 1, 4);

--13. �а� �� ���л� ���� �ľ��ϰ��� �Ѵ�.
--�а� ��ȣ�� ���л� ���� ǥ���ϴ� SQL ������ �ۼ��Ͻÿ�.
select
    DEPARTMENT_NO as "�а� �ڵ��",
    (select count(*)
    from TB_STUDENT s2 
    where ABSENCE_YN = 'Y' and s1.DEPARTMENT_NO = s2.DEPARTMENT_NO)
        as "���л� ��"
from TB_STUDENT s1
group by DEPARTMENT_NO
order by "�а� �ڵ��";
--0���� �����ؼ� ����ϱ� ���ؼ� ���� ���� �̿�.

--14. �� ���б��� �ٴϴ� �������� �л����� �̸��� ã���� �Ѵ�. � SQL������ ����ϸ� �����ϰڴ°�?
select
    STUDENT_NAME as "�����̸�",
    (select count(*)
    from TB_STUDENT s2
    where s1.STUDENT_NAME = s2.STUDENT_NAME)
        as "������ ��"
from TB_STUDENT s1
group by STUDENT_NAME
having 2 <= (select count(*)
    from TB_STUDENT s2
    where s1.STUDENT_NAME = s2.STUDENT_NAME);

--15.�й��� A112113 �� ���� �л��� �⵵, �б� �� ������ �⵵ �� ���� ����, �� ������ ���ϴ� SQL���� �ۼ��Ͻÿ�.
--(��, ������ �Ҽ��� 1�ڸ������� �ݿø��Ͽ� ǥ���Ѵ�.)
select
    substr(TERM_NO, 1, 4) as "�⵵",
    substr(TERM_NO, 5, 2) as "�б�",
    round(avg(POINT),1) as "�⵵ �� ����"
from TB_GRADE
where STUDENT_NO = 'A112113' 
group by substr(TERM_NO, 1, 4), substr(TERM_NO, 5, 2)
union
select
    substr(TERM_NO, 1, 4) as "�⵵",
    null as "�б�",
    round(avg(POINT),1) as "�⵵ �� ����"
from TB_GRADE
where STUDENT_NO = 'A112113'
group by substr(TERM_NO, 1, 4)
order by "�⵵"; -- order by ���� �߰�.

--------------------------------------------------------

--[Additional SELECT - Option]
--1. �л� �̸��� �ּ����� ǥ���Ͻÿ�.
--��, ��� ����� "�л� �̸�", "�ּ���"�� �ϰ�, ������ �̸����� �������� ǥ���ϵ��� �Ѵ�.
select
    STUDENT_NAME as "�л� �̸�",
    STUDENT_ADDRESS as "�ּ���"
from TB_STUDENT
order by "�л� �̸�";

--2.���� ���� �л����� �̸��� �ֹι�ȣ�� ���̰� ���� ������ ȭ�鿡 ����Ͻÿ�.
select
    STUDENT_NAME,
    STUDENT_SSN
from TB_STUDENT
where ABSENCE_YN = 'Y';

--3. �ּ����� �������� ��⵵�� �л��� �� 1900��� �й��� ���� �л�����
--�̸��� �й�, �ּҸ� �̸��� ������������ ȭ�鿡 ����Ͻÿ�.
--��, ��� ������� "�л��̸�", "�й�", "������ �ּ�"�� ��µǵ��� �Ѵ�.
select
    STUDENT_NAME as "�л��̸�",
    STUDENT_NO as "�й�",
    STUDENT_ADDRESS as "������ �ּ�"
from TB_STUDENT
where
    (STUDENT_ADDRESS like '������%' or STUDENT_ADDRESS like '��⵵%') --�������� '����'�� 1�� ����.
    and STUDENT_NO not like 'A%'; -- 2000�⵵���ʹ� A�� �ٰ� �Ⱥٰ� �ٸ� ������ �־���. �װ� ����.

--4. ���� ���а� ���� �� ���� ���̰� ���� ������� �̸��� Ȯ���� �� �ִ� SQL ������ �ۼ��Ͻÿ�.
--(���а��� '�а��ڵ�'�� �а� ���̺�(TB_DEPARTMENT)�� ��ȸ�ؼ� ã�Ƴ����� ����)
select
    PROFESSOR_NAME,
    PROFESSOR_SSN
from TB_PROFESSOR p
where
    p.DEPARTMENT_NO =
        (select d.DEPARTMENT_NO
        from TB_DEPARTMENT d
        where DEPARTMENT_NAME = '���а�')
order by substr(PROFESSOR_SSN,1,6) asc;

--5. 2004�� 2�б⿡ 'C3118100' ������ ������ �л����� ������ ��ȸ�Ϸ��� �Ѵ�.
--������ ���� �л����� ǥ���ϰ�, ������ ������ �й��� ���� �л����� ǥ���ϴ� ������ �ۼ��غ��ÿ�.
select
    STUDENT_NO,
    POINT
from TB_GRADE
where CLASS_NO = 'C3118100'
order by POINT desc, STUDENT_NO asc;

--6.�л� ��ȣ, �л� �̸�, �а� �̸��� �л� �̸����� �������� �����Ͽ� ����ϴ� SQL���� �ۼ��Ͻÿ�.
select
    STUDENT_NO,
    STUDENT_NAME,
    DEPARTMENT_NAME
from TB_STUDENT s
inner join TB_DEPARTMENT d
    on s.DEPARTMENT_NO = d.DEPARTMENT_NO
order by STUDENT_NAME asc;

--7. �� ������б��� ���� �̸��� ������ �а� �̸��� ����ϴ� SQL ������ �ۼ��Ͻÿ�.
select
    CLASS_NAME,
    DEPARTMENT_NAME
from TB_CLASS c
inner join TB_DEPARTMENT d
    on c.DEPARTMENT_NO = d.DEPARTMENT_NO;

--8.���� ���� �̸��� ã������ �Ѵ�. ���� �̸��� ���� �̸��� ����ϴ� SQL ���� �ۼ��Ͻÿ�.
select
    c.CLASS_NAME,
    p.PROFESSOR_NAME
from TB_CLASS_PROFESSOR cp
inner join TB_CLASS c
    on cp.CLASS_NO = c.CLASS_NO
inner join TB_PROFESSOR p
    on cp.PROFESSOR_NO = p.PROFESSOR_NO;

--9.8���� ��� �� '�ι���ȸ' �迭�� ���� ������ ���� �̸��� ã������ �Ѵ�.
--�̿� �ش��ϴ� ���� �̸��� ���� �̸��� ����ϴ� SQL���� �ۼ��Ͻÿ�.
select
    c.CLASS_NAME,
    p.PROFESSOR_NAME
from TB_CLASS_PROFESSOR cp
inner join TB_CLASS c
    on cp.CLASS_NO = c.CLASS_NO
inner join TB_PROFESSOR p
    on cp.PROFESSOR_NO = p.PROFESSOR_NO
where
    c.DEPARTMENT_NO in
        (select d.DEPARTMENT_NO
        from TB_DEPARTMENT d
        where d.CATEGORY = '�ι���ȸ');

--10. '�����а�' �л����� ������ ���Ϸ��� �Ѵ�.
--�����а� �л����� "�й�", "�л��̸�", "��ü ����"�� ����ϴ� SQL ������ �ۼ��Ͻÿ�.
--(�� ������ �Ҽ��� 1�ڸ������� �ݿø��Ͽ� ǥ���Ѵ�.)
select
    s.STUDENT_NO as "�й�",
    s.STUDENT_NAME as "�л��̸�",
    round((select avg(g.POINT) from TB_GRADE g where s.STUDENT_NO = g.STUDENT_NO),1) as "��ü ����"
from TB_STUDENT s
where
    s.DEPARTMENT_NO =
        (select d.DEPARTMENT_NO
        from TB_DEPARTMENT d
        where d.DEPARTMENT_NAME = '�����а�');

--11. �й��� 'A313047' �� �л��� �б��� ������ ���� �ʴ�.
--���� �������� ������ �����ϱ� ���� �а� �̸�, �л� �̸��� ���� ���� �̸��� �ʿ��ϴ�. �� �� ����� SQL���� �ۼ��Ͻÿ�.
--��, �������� "�а��̸�", "�л��̸�", "���������̸�"���� ��µǵ��� �Ѵ�.
select
    (select d.DEPARTMENT_NAME
    from TB_DEPARTMENT d
    where s.DEPARTMENT_NO = d.DEPARTMENT_NO)
        as "�а��̸�",
    s.STUDENT_NAME 
        as "�л��̸�",
    (select p.PROFESSOR_NAME
    from TB_PROFESSOR P
    where s.COACH_PROFESSOR_NO = p.PROFESSOR_NO)
        as "���������̸�"
from TB_STUDENT s
where s.STUDENT_NO = 'A313047';

--12. 2007 �⵵�� '�ΰ������' ������ ������ �л��� ã�� �л��̸��� �����б⸦ ǥ���ϴ� SQL ������ �ۼ��Ͻÿ�.
select
    s.STUDENT_NAME,
    g.TERM_NO as "TERM_NAME"
from TB_GRADE g
inner join TB_CLASS c
    on g.CLASS_NO = c.CLASS_NO
inner join TB_STUDENT s
    on g.STUDENT_NO = s.STUDENT_NO
where
    c.CLASS_NAME = '�ΰ������'
    and substr(g.TERM_NO,1,4) = '2007';

--13. ��ü�� �迭 ���� �� ���� ��米���� �� �� �������� ���� ������ ã��
--�� ���� �̸��� �а� �̸��� ����ϴ� SQL ������ �ۼ��Ͻÿ�.
select
    c.CLASS_NAME,
    d.DEPARTMENT_NAME
from TB_CLASS c
inner join TB_DEPARTMENT d
    on c.DEPARTMENT_NO = d.DEPARTMENT_NO
left outer join TB_CLASS_PROFESSOR cp
    on c.CLASS_NO = cp.CLASS_NO
where
    cp.PROFESSOR_NO is null
    and d.CATEGORY = '��ü��';

--14. �� ������б� ���ݾƾ��а� �л����� ���������� �Խ��ϰ��� �Ѵ�.
--�л��̸��� �������� �̸��� ã�� ���� ���� ������ ���� �л��� ��� "�������� ������"���� ǥ���ϵ��� �ϴ� SQL ���� �ۼ��Ͻÿ�.
--��, �������� "�л��̸�", "��������"�� ǥ���ϸ�
--���й� �л��� ���� ǥ�õǵ��� �Ѵ�.
select
    s.STUDENT_NAME as "�л��̸�",
    nvl((select p.PROFESSOR_NAME 
    from TB_PROFESSOR p
    where s.COACH_PROFESSOR_NO = p.PROFESSOR_NO), '�������� ������')
        as "��������"
from TB_STUDENT s;

--Additional 15�� ���� �� ǯ. ��� �� ���.
--15. ���л��� �ƴ� �л� �� ������ 4.0 �̻��� �л��� ã��
--�� �л��� �й�, �̸�, �а��̸�, ������ ����ϴ� SQL ���� �ۼ��Ͻÿ�.

--16.ȯ�������а� ����������� ���� �� ������ �ľ��� �� �ִ� SQL ���� �ۼ��Ͻÿ�.
select
    CLASS_NO,
    CLASS_NAME,
    (select avg(POINT) from TB_GRADE g where c.CLASS_NO = g.CLASS_NO)
        as "AVG(POINT)"
from TB_CLASS c
where
    c.DEPARTMENT_NO =
        (select d.DEPARTMENT_NO
        from TB_DEPARTMENT d
        where d.DEPARTMENT_NAME = 'ȯ�������а�');
--���ü� 6��, ���⼱ 9���ε� Ʋ���� ���� ����. department_no �� 034�� ���ϵ� �����.

--17.�� ������б��� �ٴϰ� �ִ� �ְ��� �л��� ���� �� �л����� �̸��� �ּҸ� ����ϴ� SQL ���� �ۼ��Ͻÿ�.
select
    s1.STUDENT_NAME,
    s1.STUDENT_ADDRESS
from TB_STUDENT s1
where
    s1.DEPARTMENT_NO =
        (select s2.DEPARTMENT_NO
        from TB_STUDENT s2
        where s2.STUDENT_NAME = '�ְ���');

--18. ������а����� �� ������ ���� ���� �л��� �̸��� �й��� ǥ���ϴ� SQL���� �ۼ��Ͻÿ�.
--select
--    s.STUDENT_NO,
--    s.STUDENT_NAME
--from TB_STUDENT s
--where
--    (select avg(g1.POINT)
--    from TB_GRADE g1
--    where s.STUDENT_NO = g1.STUDENT_NO)
--        =
--    max((select avg(g2.POINT)
--        from TB_GRADE g2
--        where s.department_no =
--            (select d2.department_no
--            from TB_DEPARTMENT d2
--            where d2.DEPARTMENT_NAME = '������а�')
--        ));

--19. �� ������б��� ~


------------------------------------------------
--[DDL]
--1. �迭 ������ ������ ī�װ� ���̺��� ������� �Ѵ�. ������ ���� ���̺��� �ۼ��Ͻÿ�.
--PDF ����
create table TB_CATEGORY(
    NAME varchar2(10),
    USE_YN char(1) default('Y')
);
drop table TB_CATEGORY;

--2. ���� ������ ������ ���̺��� ������� �Ѵ�. ������ ���� ���̺��� �ۼ��Ͻÿ�.
create table TB_CLASS_TYPE(
    NO varchar2(5) primary key,
    NAME varchar2(10)
);

--3.TB_CATEGORY ���̺��� NAME �÷��� PRIMARY KEY�� �����Ͻÿ�.
--(KEY �̸��� �������� �ʾƵ� ������. ���� KEY �̸� �����ϰ��� �Ѵٸ� �̸��� ������ �˾Ƽ� ������ �̸��� ����Ѵ�.)
alter table TB_CATEGORY add primary key(NAME);

--4. TB_CLASS_TYPE ���̺��� NAME �÷��� NULL ���� ���� �ʵ��� �Ӽ��� �����Ͻÿ�.
alter table TB_CLASS_TYPE modify NAME default 'none' not null;

--5. �� ���̺��� Į�� ���� NO�� ���� ���� Ÿ���� �����ϸ鼭 ũ��� 10����,
--Į������ NAME�� ���� ���������� ���� Ÿ���� �����ϸ鼭 ũ�� 20���� �����Ͻÿ�.
alter table TB_CATEGORY modify NAME varchar2(20);
alter table TB_CLASS_TYPE modify NAME varchar2(20);
alter table TB_CLASS_TYPE modify NO varchar2(10);

--6. �� ���̺��� NO Į���� NAME Į���� �̸��� �� �� 'TB_'�� ������ ���̺� �̸��� �տ� ���� ���·� �����Ѵ�.
alter table TB_CATEGORY rename column NAME to CATEGORY_NAME;
alter table TB_CLASS_TYPE rename column NO to CLASS_TYPE_NO;
alter table TB_CLASS_TYPE rename column NAME to CLASS_TYPE_NAME;

--7. TB_CATEGORY ���̺�� TB_CLASS_TYPE ���̺��� PRIMARY KEY �̸��� ������ ���� �����Ͻÿ�.
--Primary Key�� �̸��� 'PK_ + �÷��̸�'���� �����Ͻÿ�.
alter table TB_CATEGORY rename column CATEGORY_NAME to PK_CATEGORY_NAME;
alter table TB_CLASS_TYPE rename column CLASS_TYPE_NO to PK_CLASS_TYPE_NO;

--8. ������ ���� INSERT ���� �����Ѵ�.
insert into TB_CATEGORY values ('����', 'Y');
insert into TB_CATEGORY values ('�ڿ�����', 'Y');
insert into TB_CATEGORY values ('����', 'Y');
insert into TB_CATEGORY values ('��ü��', 'Y');
insert into TB_CATEGORY values ('�ι���ȸ', 'Y');
COMMIT;

--9. TB_DEPARTMENT �� CATEGORY �÷��� TB_CATEGORY ���̺��� CATEGORY_NAME �÷���
--�θ� ������ �����ϵ��� FOREIGN KEY�� �����Ͻÿ�.
--�� �� KEY �̸��� FK_���̺��̸�_�÷��̸����� �����Ѵ�. (ex. FK_DEPARTMENT_CATEGORY)
alter table TB_DEPARTMENT 
    add constraint FK_DEPARTMENT_CATEGORY 
    foreign key(CATEGORY) references TB_CATEGORY(PK_CATEGORY_NAME);

--10. �� ������б� �л����� �������� ���ԵǾ� �ִ� �л� �Ϲ����� VIEW�� ������� �Ѵ�.
--�Ʒ� ������ �����Ͽ� ������ SQL ���� �ۼ��Ͻÿ�. (PDF ����)
create view VW_�л��Ϲ�����
as
select
    STUDENT_NO,
    STUDENT_NAME,
    STUDENT_ADDRESS
from TB_STUDENT;
--���� ���� ����. �����ڰ����� view ���ѵ� ��� ��.

--11. �� ������б��� 1�⿡ �� ���� �а����� �л��� ���������� ���� ����� �����Ѵ�.
--�̸� ���� ����� �л��̸�, �а��̸�, ��米���̸����� �����Ǿ� �ִ� VIEW�� ����ÿ�.
--�̶� ���� ������ ���� �л��� ���� �� ������ ����Ͻÿ�.
--(��, �� VIEW�� �ܼ� SELECT���� �� ��� �а����� ���ĵǾ� ȭ�鿡 �������� ����ÿ�) --���� �ȹ�����

--11~15�� View ����� ����. �ϴ� �ǳʶ�.

-------------------------------------------------------------------------------
--[DML]
--1. �������� ���̺�(TB_CLASS_TYPE)�� �Ʒ��� ���� �����͸� �Է��Ͻÿ�.
--(PDF ����)
select * from TB_CLASS_TYPE;
insert into TB_CLASS_TYPE values ('01', '�����ʼ�');
insert into TB_CLASS_TYPE values ('02', '��������');
insert into TB_CLASS_TYPE values ('03', '�����ʼ�');
insert into TB_CLASS_TYPE values ('04', '���缱��');
insert into TB_CLASS_TYPE values ('05', '������');

--2. �� ������б� �л����� ������ ���ԵǾ� �ִ� �л��Ϲ����� ���̺��� ������� �Ѵ�.
--�Ʒ� ������ �����Ͽ� ������ SQL���� �ۼ��Ͻÿ�. (���������� �̿��Ͻÿ�.)
create table TB_�л��Ϲ�����
as
select STUDENT_NO, STUDENT_NAME, STUDENT_ADDRESS
from TB_STUDENT;

--3.������а� �л����� �������� ���ԵǾ� �ִ� �а����� ���̺��� ������� �Ѵ�.
--�Ʒ� ������ �����Ͽ� ������ SQL ���� �ۼ��Ͻÿ�.
create table TB_������а�
as
select
    STUDENT_NO as "�й�",
    STUDENT_NAME as "�л��̸�",
    substr('19'||STUDENT_SSN, 1, 2) as "����⵵",
    (select p.PROFESSOR_NAME
    from TB_PROFESSOR p
    where s.COACH_PROFESSOR_NO = p.PROFESSOR_NO)
        as "���������̸�"
from TB_STUDENT s;

--4. �� �а����� ������ 10% ������Ű�� �Ǿ���. �̿� ����� SQL���� �ۼ��Ͻÿ�.
--(��, �ݿø��� ����Ͽ� �Ҽ��� �ڸ����� ������ �ʵ��� �Ѵ�)
update TB_DEPARTMENT set CAPACITY = around(CAPACITY * 1.1);
--������ ���غ�.

--5. �й� A413042�� �ڰǿ� �л��� �ּҰ� '����� ���α� ���ε� 181-21' �� ����Ǿ��ٰ� �Ѵ�.
--�ּ����� �����ϱ� ���� ����� SQL ���� �ۼ��Ͻÿ�.
update TB_STUDENT set STUDENT_ADDRESS = '����� ���α� ���ε� 181-21'
    where STUDENT_NO = 'A413042' and STUDENT_NAME = '�ڰǿ�';
--�����غ�.

--6.�ֹε�Ϲ�ȣ ��ȣ���� ���� �л����� ���̺��� �ֹι�ȣ ���ڸ��� �������� �ʱ�� �����Ͽ���.
--�� ������ �ݿ��� ������ SQL ������ �ۼ��Ͻÿ�.

--update ����

--7. ���а� ����� �л��� 2005�� 1�б⿡ �ڽ��� ������ '�Ǻλ�����' ������ �߸��Ǿ��ٴ� ���� �߰��ϰ�� ������ ��û�Ͽ���.
--��� ������ Ȯ�� ���� ��� �ش� ������ ������ 3.5�� ����Ű�� �����Ǿ���. ������ SQL ���� �ۼ��Ͻÿ�.

--update ����

--8. ���� ���̺�(TB_GRADE)���� ���л����� �����׸��� �����Ͻÿ�.
--delete ����