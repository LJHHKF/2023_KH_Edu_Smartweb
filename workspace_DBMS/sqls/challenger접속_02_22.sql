--[Basic SELCET]
--1. 춘 기술 대학교의 학과 이름과 계열을 표시하시오. 단, 출력 헤더는 "확과명", "계열"으로 표시하도록 한다.
select
    DEPARTMENT_NAME as "학과명",
    CATEGORY as "계열"
from TB_DEPARTMENT;

--2. 학과의 학과 정원을 다음과 같은 형태로 화면에 출력한다.
--(PDF 참고. 학과별 정원, 각 ~~학과의 정원은 ~~명 입니다.)
select
    (DEPARTMENT_NAME ||'의 정원은'||CAPACITY||'명 입니다.') as "학과별 정원"
from TB_DEPARTMENT;

--3. "국어국문학과"에 다니는 여학생 중 현재 휴학중인 여학생을 찾아달라는 요청이 들어왔다. 누구인가?
--(국문학과의 '학과코드'는 학과 테이블(TB_DEPARTMENT)을 조회해서 찾아 내도록 하자)
select STUDENT_NAME
from TB_STUDENT
where
    DEPARTMENT_NO =
        (select DEPARTMENT_NO 
        from TB_DEPARTMENT 
        where DEPARTMENT_NAME = '국어국문학과')
    and ABSENCE_YN = 'Y';

--4. 도서관에서 대출 도서 장기 연체자들을 찾아 이름을 게시하고자 한다.
--그 대상자들의 학번이 다음과 같을 때 대상자들을 찾는 적절한 SQL 구문을 작성하시오.
--A513079, A513090, A513091, A513110, A513119
select STUDENT_NAME
from TB_STUDENT
where STUDENT_NO in ('A513079', 'A513090', 'A513091', 'A513110', 'A513119');

--5. 입학정원이 20명 이상, 30명 이하인 학과들의 학과 이름과 계열을 출력하시오.
select
    DEPARTMENT_NAME, CATEGORY
from TB_DEPARTMENT
where CAPACITY between 20 and 30;

--6. 춘 기술대학교는 총장을 제외하고 모든 교수들이 소속 학과를 가지고 있다.
--그럼 춘 기술대학교 총장의 이름을 알아낼 수 있는 SQL 문장을 작성하시오.
select PROFESSOR_NAME
from TB_PROFESSOR
where DEPARTMENT_NO is null;

--7.혹시 전산상의 착오로 학과가 지정되어 있지 않은 학생이 있는지 확인하고자 한다.
--어떠한 SQL 문장을 사용하면 될 것인지 작성하시오.
select
    STUDENT_NO,
    STUDENT_NAME
from TB_STUDENT s
where s.DEPARTMENT_NO not in
    (select d.DEPARTMENT_NO
    from TB_DEPARTMENT d);

--8.수강신청을 하려고 한다. 선수과목 여부를 확인해야 하는데,
--선수과목이 존재하는 과목들은 어떤 과목인지 과목번호를 조회해보시오.
select CLASS_NO
from TB_CLASS
where PREATTENDING_CLASS_NO is not null;

--9. 춘 대학에는 어떤 계열(CATEGORY)들이 있는지 조회해보시오.
select distinct(CATEGORY)
from TB_DEPARTMENT;

--10. 02학번 전주 거주자들의 모임을 만들려고 한다.
--휴학한 사람들은 제외한 재학중인 학생들의 학번, 이름, 주민번호를 출력하는 구문을 작성하시오.
select
    STUDENT_NO,
    STUDENT_NAME,
    STUDENT_SSN
from TB_STUDENT
where
    ABSENCE_YN = 'N'
    and extract(year from ENTRANCE_DATE) = extract(year from to_date('02', 'YY'))
    and STUDENT_ADDRESS like '전주시%';

---------------------------------------------------------------------

--[Additional SELECT - 함수]
--1. 영어영문학과(학과코드 002) 학생들의 학번과 이름, 입학 년도를
--입학 년도가 빠른 순으로 표시하는 SQL 문장을 작성하시오.
select
    STUDENT_NO as "학번",
    STUDENT_NAME as "이름",
    ENTRANCE_DATE as "입학 년도"
from TB_STUDENT s
where
    s.DEPARTMENT_NO =
        (select d.DEPARTMENT_NO
        from TB_DEPARTMENT d 
        where d.DEPARTMENT_NAME = '영어영문학과')
order by ENTRANCE_DATE asc;

--2.춘 기술대학교의 교수 중 이름이 세 글자가 아닌 교수가 한 명 있다고 한다.
--그 교수의 이름과 주민번호를 화면에 출력하는 SQL 문장을 작성해 보자.
--(이 때 올바르게 작성한 SQL 문장의 결과 값이 예상과 다르게 나올 수 있다. 원인이 무엇일지 생각해볼 것.)
select
    PROFESSOR_NAME,
    PROFESSOR_SSN
from TB_PROFESSOR
where length(PROFESSOR_NAME) != 3;

--3.춘 기술대학교의 남자 교수들의 이름과 나이를 출력하는 SQL 문장을 작성하시오.
--단, 이떄 나이가 적은 사람에서 많은 사람 순서로 화면에 출력되도록 만드시오.
--(단, 교수 중 2000년 이후 출생자는 없으며 출력 헤더는 "교수이름", "나이"로 한다. 나이는 '만'으로 계산한다.)
select
    PROFESSOR_NAME as "교수이름",
    floor((sysdate - to_date('19'||substr(PROFESSOR_SSN, 1, 6), 'YYYYMMDD'))/365)||'세' as "나이"
from TB_PROFESSOR
where substr(PROFESSOR_SSN,8,1) = '1'
order by "나이" asc;

--4. 교수들의 이름 중 성을 제외한 이름만 출력하는 SQL 문장을 작성하시오.
--출력 헤더는 "이름"이 찍히도록 한다. (성이 2자인 경우의 교수는 없다고 가정하시오)
select
    substr(PROFESSOR_NAME, 2, 2) as "이름"
from TB_PROFESSOR;

--5. 춘 기술대학교의 재수생 입학자를 구하려고 한다. 어떻게 찾아낼 것인가?
--이 때, 19살에 입학하면 재수를 하지 않은 것으로 간주한다.
select
    STUDENT_NO,
    STUDENT_NAME
from TB_STUDENT
where
    19 <= (ENTRANCE_DATE - to_date('19'||substr(STUDENT_SSN, 1, 6), 'YYYYMMDD'))/365 ;
    --만으로 계산된 것.
    --결과값이 예시의 204행이 아니라 249행이라 뭔가 잘못된건가 싶으나 일단 넘김.

--6. 2020년 크리스마스는 무슨 요일인가?
select to_char(to_date('20201225', 'YYYYMMDD'), 'DAY') from dual;

--7. TO_DATE('99/10/11', 'YY/MM/DD'), TO_DATE('49/10/11', 'YY/MM/DD')은 각각 몇년 몇월 몇일을 의미할까?
--또 TO_DATE('99/10/11', 'RR/MM/DD'), TO_DATE('49/10/11', 'RR/MM/DD')은 각각 몇 년 몇월 몇일을 의미할까?
select to_char(to_date('99/10/11', 'YY/MM/DD'), 'YYYYMMDD') from dual;
--구글링 & 테스트 결과
--a. TO_DATE('99/10/11', 'YY/MM/DD') = 2099.10.11
--b. TO_DATE('49/10/11', 'YY/MM/DD') = 2049.10.11
--c. TO_DATE('99/10/11', 'RR/MM/DD') = 1999.10.11
--d. TO_DATE('49/10/11', 'RR/MM/DD') = 2049.10.11

--8. 춘 기술대학교의 2000년도 이후 입학자들은 학번이 A로 시작하게 되어있다.
--2000년도 이전 학번을 받은 학생들의 학번과 이름을 보여주는 SQL 문장을 작성하시오.
select
    STUDENT_NO,
    STUDENT_NAME
from TB_STUDENT
where STUDENT_NO not like 'A%';

--9. 학번이 A517178 인 한아름 학생의 학점 총 평점을 구하는 SQL문을 작성하시오.
--단, 이때 출력 화면의 헤더는 "평점"이라고 찍히게 하고, 점수는 반올림하여 소수점 이하 한 자리까지만 표시한다.
--(아마 총 평점이란게 평균평점 얘기인듯 함.)
select round(avg(POINT),1) as "평점"
from TB_GRADE 
where STUDENT_NO = 'A517178';
--또는
select round(avg(POINT),1) as "평점"
from TB_GRADE g
where
    g.STUDENT_NO =
        (select s.STUDENT_NO
        from TB_STUDENT s
        where s.STUDENT_NO = 'A517178' and s.STUDENT_NAME = '한아름');

--10. 학과별 학생수를 구하여 "학과번호", "학생수(명)" 의 형태로 헤더를 만들어 결과값이 출력되도록 하시오
select
    DEPARTMENT_NO as "학과번호",
    count(*) as "학생수(명)"
from TB_STUDENT
group by DEPARTMENT_NO
order by "학과번호"; -- 정렬은 임의 추가

--11. 지도 교수를 배정받지 못한 학생의 수는 몇 명 정도 되는 알아내는 SQL문을 작성하시오.
select count(*)
from TB_STUDENT
where COACH_PROFESSOR_NO is null;

--12. 학번이 A112113 인 김고운 학생의 년도 별 평점을 구하는 SQL문을 작성하시오.
--단, 이때 출력 화면의 헤더는 "년도", "년도 별 평점"이라고 찍히게 하고,
--점수는 반올림하여 소수점 이하 한 자리까지만 표시한다.
select
    substr(TERM_NO, 1, 4) as "년도",
    round(avg(POINT),1) as "년도 별 평점"
from TB_GRADE
where STUDENT_NO = 'A112113' 
group by substr(TERM_NO, 1, 4);

--13. 학과 별 휴학생 수를 파악하고자 한다.
--학과 번호와 휴학생 수를 표시하는 SQL 문장을 작성하시오.
select
    DEPARTMENT_NO as "학과 코드명",
    (select count(*)
    from TB_STUDENT s2 
    where ABSENCE_YN = 'Y' and s1.DEPARTMENT_NO = s2.DEPARTMENT_NO)
        as "휴학생 수"
from TB_STUDENT s1
group by DEPARTMENT_NO
order by "학과 코드명";
--0까지 포함해서 출력하기 위해서 서브 쿼리 이용.

--14. 춘 대학교에 다니는 동명이인 학생들의 이름을 찾고자 한다. 어떤 SQL문장을 사용하면 가능하겠는가?
select
    STUDENT_NAME as "동일이름",
    (select count(*)
    from TB_STUDENT s2
    where s1.STUDENT_NAME = s2.STUDENT_NAME)
        as "동명인 수"
from TB_STUDENT s1
group by STUDENT_NAME
having 2 <= (select count(*)
    from TB_STUDENT s2
    where s1.STUDENT_NAME = s2.STUDENT_NAME);

--15.학번이 A112113 인 김고운 학생의 년도, 학기 별 평점과 년도 별 누적 평점, 총 평점을 구하는 SQL문을 작성하시오.
--(단, 평점은 소수점 1자리까지만 반올림하여 표시한다.)
select
    substr(TERM_NO, 1, 4) as "년도",
    substr(TERM_NO, 5, 2) as "학기",
    round(avg(POINT),1) as "년도 별 평점"
from TB_GRADE
where STUDENT_NO = 'A112113' 
group by substr(TERM_NO, 1, 4), substr(TERM_NO, 5, 2)
union
select
    substr(TERM_NO, 1, 4) as "년도",
    null as "학기",
    round(avg(POINT),1) as "년도 별 평점"
from TB_GRADE
where STUDENT_NO = 'A112113'
group by substr(TERM_NO, 1, 4)
order by "년도"; -- order by 임의 추가.

--------------------------------------------------------

--[Additional SELECT - Option]
--1. 학생 이름과 주소지를 표시하시오.
--단, 출력 헤더는 "학생 이름", "주소지"로 하고, 정렬은 이름으로 오름차순 표시하도록 한다.
select
    STUDENT_NAME as "학생 이름",
    STUDENT_ADDRESS as "주소지"
from TB_STUDENT
order by "학생 이름";

--2.휴학 중인 학생들의 이름과 주민번호를 나이가 적은 순서로 화면에 출력하시오.
select
    STUDENT_NAME,
    STUDENT_SSN
from TB_STUDENT
where ABSENCE_YN = 'Y';

--3. 주소지가 강원도나 경기도인 학생들 중 1900년대 학번을 가진 학생들의
--이름과 학번, 주소를 이름의 오름차순으로 화면에 출력하시오.
--단, 출력 헤더에는 "학생이름", "학번", "거주지 주소"가 출력되도록 한다.
select
    STUDENT_NAME as "학생이름",
    STUDENT_NO as "학번",
    STUDENT_ADDRESS as "거주지 주소"
from TB_STUDENT
where
    (STUDENT_ADDRESS like '강원도%' or STUDENT_ADDRESS like '경기도%') --강원도는 '강원'만 1개 있음.
    and STUDENT_NO not like 'A%'; -- 2000년도부터는 A가 붙고 안붙고가 다른 문제에 있었음. 그것 참고.

--4. 현재 법학과 교수 중 가장 나이가 많은 사람부터 이름을 확인할 수 있는 SQL 문장을 작성하시오.
--(법학과의 '학과코드'는 학과 테이블(TB_DEPARTMENT)을 조회해서 찾아내도록 하자)
select
    PROFESSOR_NAME,
    PROFESSOR_SSN
from TB_PROFESSOR p
where
    p.DEPARTMENT_NO =
        (select d.DEPARTMENT_NO
        from TB_DEPARTMENT d
        where DEPARTMENT_NAME = '법학과')
order by substr(PROFESSOR_SSN,1,6) asc;

--5. 2004년 2학기에 'C3118100' 과목을 수강한 학생들의 학점을 조회하려고 한다.
--학점이 높은 학생부터 표시하고, 학점이 같으면 학번이 낮은 학생부터 표시하는 구문을 작성해보시오.
select
    STUDENT_NO,
    POINT
from TB_GRADE
where CLASS_NO = 'C3118100'
order by POINT desc, STUDENT_NO asc;

--6.학생 번호, 학생 이름, 학과 이름을 학생 이름으로 오름차순 정렬하여 출력하는 SQL문을 작성하시오.
select
    STUDENT_NO,
    STUDENT_NAME,
    DEPARTMENT_NAME
from TB_STUDENT s
inner join TB_DEPARTMENT d
    on s.DEPARTMENT_NO = d.DEPARTMENT_NO
order by STUDENT_NAME asc;

--7. 춘 기술대학교의 과목 이름과 과목의 학과 이름을 출력하는 SQL 문장을 작성하시오.
select
    CLASS_NAME,
    DEPARTMENT_NAME
from TB_CLASS c
inner join TB_DEPARTMENT d
    on c.DEPARTMENT_NO = d.DEPARTMENT_NO;

--8.과목별 교수 이름을 찾으려고 한다. 과목 이름과 교수 이름을 출력하는 SQL 문을 작성하시오.
select
    c.CLASS_NAME,
    p.PROFESSOR_NAME
from TB_CLASS_PROFESSOR cp
inner join TB_CLASS c
    on cp.CLASS_NO = c.CLASS_NO
inner join TB_PROFESSOR p
    on cp.PROFESSOR_NO = p.PROFESSOR_NO;

--9.8번의 결과 중 '인문사회' 계열에 속한 과목의 교수 이름을 찾으려고 한다.
--이에 해당하는 과목 이름과 교수 이름을 출력하는 SQL문을 작성하시오.
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
        where d.CATEGORY = '인문사회');

--10. '음악학과' 학생들의 평점을 구하려고 한다.
--음악학과 학생들의 "학번", "학생이름", "전체 평점"을 출력하는 SQL 문장을 작성하시오.
--(단 평점은 소수점 1자리까지만 반올림하여 표시한다.)
select
    s.STUDENT_NO as "학번",
    s.STUDENT_NAME as "학생이름",
    round((select avg(g.POINT) from TB_GRADE g where s.STUDENT_NO = g.STUDENT_NO),1) as "전체 평점"
from TB_STUDENT s
where
    s.DEPARTMENT_NO =
        (select d.DEPARTMENT_NO
        from TB_DEPARTMENT d
        where d.DEPARTMENT_NAME = '음악학과');

--11. 학번이 'A313047' 인 학생이 학교에 나오고 있지 않다.
--지도 교수에게 내용을 전달하기 위한 학과 이름, 학생 이름과 지도 교수 이름이 필요하다. 이 때 사용할 SQL문을 작성하시오.
--단, 출력헤더는 "학과이름", "학생이름", "지도교수이름"으로 출력되도록 한다.
select
    (select d.DEPARTMENT_NAME
    from TB_DEPARTMENT d
    where s.DEPARTMENT_NO = d.DEPARTMENT_NO)
        as "학과이름",
    s.STUDENT_NAME 
        as "학생이름",
    (select p.PROFESSOR_NAME
    from TB_PROFESSOR P
    where s.COACH_PROFESSOR_NO = p.PROFESSOR_NO)
        as "지도교수이름"
from TB_STUDENT s
where s.STUDENT_NO = 'A313047';

--12. 2007 년도에 '인간관계론' 과목을 수강한 학생을 찾아 학생이름과 수강학기를 표시하는 SQL 문장을 작성하시오.
select
    s.STUDENT_NAME,
    g.TERM_NO as "TERM_NAME"
from TB_GRADE g
inner join TB_CLASS c
    on g.CLASS_NO = c.CLASS_NO
inner join TB_STUDENT s
    on g.STUDENT_NO = s.STUDENT_NO
where
    c.CLASS_NAME = '인간관계론'
    and substr(g.TERM_NO,1,4) = '2007';

--13. 예체능 계열 과목 중 과목 담당교수를 한 명도 배정받지 못한 과목을 찾아
--그 과목 이름과 학과 이름을 출력하는 SQL 문장을 작성하시오.
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
    and d.CATEGORY = '예체능';

--14. 춘 기술대학교 서반아어학과 학생들의 지도교수를 게시하고자 한다.
--학생이름과 지도교수 이름을 찾고 만일 지도 교수가 없는 학생일 경우 "지도교수 미지정"으로 표시하도록 하는 SQL 문을 작성하시오.
--단, 출력헤더는 "학생이름", "지도교수"로 표시하며
--고학번 학생이 먼저 표시되도록 한다.
select
    s.STUDENT_NAME as "학생이름",
    nvl((select p.PROFESSOR_NAME 
    from TB_PROFESSOR p
    where s.COACH_PROFESSOR_NO = p.PROFESSOR_NO), '지도교수 미지정')
        as "지도교수"
from TB_STUDENT s;

--Additional 15는 아직 못 풂. 기능 덜 배움.
--15. 휴학생이 아닌 학생 중 평점이 4.0 이상인 학생을 찾아
--그 학생의 학번, 이름, 학과이름, 평점을 출력하는 SQL 문을 작성하시오.

--16.환경조경학과 전공과목들의 과목 별 평점을 파악할 수 있는 SQL 문을 작성하시오.
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
        where d.DEPARTMENT_NAME = '환경조경학과');
--예시선 6개, 여기선 9개인데 틀린거 같진 않음. department_no 은 034로 통일된 결과임.

--17.춘 기술대학교에 다니고 있는 최경희 학생과 같은 과 학생들의 이름과 주소를 출력하는 SQL 문을 작성하시오.
select
    s1.STUDENT_NAME,
    s1.STUDENT_ADDRESS
from TB_STUDENT s1
where
    s1.DEPARTMENT_NO =
        (select s2.DEPARTMENT_NO
        from TB_STUDENT s2
        where s2.STUDENT_NAME = '최경희');

--18. 국어국문학과에서 총 평점이 가장 높은 학생의 이름과 학번을 표시하는 SQL문을 작성하시오.
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
--            where d2.DEPARTMENT_NAME = '국어국문학과')
--        ));

--19. 춘 기술대학교의 ~


------------------------------------------------
--[DDL]
--1. 계열 정보를 저장할 카테고리 테이블을 만들려고 한다. 다음과 같은 테이블을 작성하시오.
--PDF 참고
create table TB_CATEGORY(
    NAME varchar2(10),
    USE_YN char(1) default('Y')
);
drop table TB_CATEGORY;

--2. 과목 구분을 저장할 테이블을 만들려고 한다. 다음과 같은 테이블을 작성하시오.
create table TB_CLASS_TYPE(
    NO varchar2(5) primary key,
    NAME varchar2(10)
);

--3.TB_CATEGORY 테이블의 NAME 컬럼에 PRIMARY KEY를 생성하시오.
--(KEY 이름을 생성하지 않아도 무방함. 만일 KEY 이를 지정하고자 한다면 이름은 본인이 알아서 적당한 이름을 사용한다.)
alter table TB_CATEGORY add primary key(NAME);

--4. TB_CLASS_TYPE 테이블의 NAME 컬럼에 NULL 값이 들어가지 않도록 속성을 변경하시오.
alter table TB_CLASS_TYPE modify NAME default 'none' not null;

--5. 두 테이블에서 칼럼 명이 NO인 것은 기존 타입을 유지하면서 크기는 10으로,
--칼럼명이 NAME인 것은 마찬가지로 기존 타입을 유지하면서 크기 20으로 변경하시오.
alter table TB_CATEGORY modify NAME varchar2(20);
alter table TB_CLASS_TYPE modify NAME varchar2(20);
alter table TB_CLASS_TYPE modify NO varchar2(10);

--6. 두 테이블의 NO 칼럼과 NAME 칼럼의 이름을 각 각 'TB_'를 제외한 테이블 이름이 앞에 붙은 형태로 변경한다.
alter table TB_CATEGORY rename column NAME to CATEGORY_NAME;
alter table TB_CLASS_TYPE rename column NO to CLASS_TYPE_NO;
alter table TB_CLASS_TYPE rename column NAME to CLASS_TYPE_NAME;

--7. TB_CATEGORY 테이블과 TB_CLASS_TYPE 테이블의 PRIMARY KEY 이름을 다음과 같이 변경하시오.
--Primary Key의 이름은 'PK_ + 컬럼이름'으로 지정하시오.
alter table TB_CATEGORY rename column CATEGORY_NAME to PK_CATEGORY_NAME;
alter table TB_CLASS_TYPE rename column CLASS_TYPE_NO to PK_CLASS_TYPE_NO;

--8. 다음과 같은 INSERT 문을 수행한다.
insert into TB_CATEGORY values ('공학', 'Y');
insert into TB_CATEGORY values ('자연과학', 'Y');
insert into TB_CATEGORY values ('의학', 'Y');
insert into TB_CATEGORY values ('예체능', 'Y');
insert into TB_CATEGORY values ('인문사회', 'Y');
COMMIT;

--9. TB_DEPARTMENT 의 CATEGORY 컬럼이 TB_CATEGORY 테이블의 CATEGORY_NAME 컬럼을
--부모 값으로 참조하도록 FOREIGN KEY를 지정하시오.
--이 때 KEY 이름은 FK_테이블이름_컬럼이름으로 지정한다. (ex. FK_DEPARTMENT_CATEGORY)
alter table TB_DEPARTMENT 
    add constraint FK_DEPARTMENT_CATEGORY 
    foreign key(CATEGORY) references TB_CATEGORY(PK_CATEGORY_NAME);

--10. 춘 기술대학교 학생들의 정보만이 포함되어 있는 학생 일반정보 VIEW를 만들고자 한다.
--아래 내용을 참고하여 적절한 SQL 문을 작성하시오. (PDF 참고)
create view VW_학생일반정보
as
select
    STUDENT_NO,
    STUDENT_NAME,
    STUDENT_ADDRESS
from TB_STUDENT;
--권한 부족 나옴. 관리자계정서 view 권한도 줘야 함.

--11. 춘 기술대학교는 1년에 두 번씩 학과별로 학생과 지도교수가 지도 면담을 진행한다.
--이를 위해 사용할 학생이름, 학과이름, 담당교수이름으로 구성되어 있는 VIEW를 만드시오.
--이때 지도 교수가 없는 학생이 있을 수 있음을 고려하시오.
--(단, 이 VIEW는 단순 SELECT만을 할 경우 학과별로 정렬되어 화면에 보여지게 만드시오) --아직 안배운듯함

--11~15는 View 만들기 연습. 일단 건너뜀.

-------------------------------------------------------------------------------
--[DML]
--1. 과목유형 테이블(TB_CLASS_TYPE)에 아래와 같은 데이터를 입력하시오.
--(PDF 참고)
select * from TB_CLASS_TYPE;
insert into TB_CLASS_TYPE values ('01', '전공필수');
insert into TB_CLASS_TYPE values ('02', '전공선택');
insert into TB_CLASS_TYPE values ('03', '교양필수');
insert into TB_CLASS_TYPE values ('04', '교양선택');
insert into TB_CLASS_TYPE values ('05', '논문지도');

--2. 춘 기술대학교 학생들의 정보가 포함되어 있는 학생일반정보 테이블을 만들고자 한다.
--아래 내용을 참고하여 적절한 SQL문을 작성하시오. (서브쿼리를 이용하시오.)
create table TB_학생일반정보
as
select STUDENT_NO, STUDENT_NAME, STUDENT_ADDRESS
from TB_STUDENT;

--3.국어국문학과 학생들의 정보만이 포함되어 있는 학과정보 테이블을 만들고자 한다.
--아래 내용을 참고하여 적절한 SQL 문을 작성하시오.
create table TB_국어국문학과
as
select
    STUDENT_NO as "학번",
    STUDENT_NAME as "학생이름",
    substr('19'||STUDENT_SSN, 1, 2) as "출생년도",
    (select p.PROFESSOR_NAME
    from TB_PROFESSOR p
    where s.COACH_PROFESSOR_NO = p.PROFESSOR_NO)
        as "지도교수이름"
from TB_STUDENT s;

--4. 현 학과들의 정원을 10% 증가시키게 되었다. 이에 사용할 SQL문을 작성하시오.
--(단, 반올림을 사용하여 소수점 자릿수는 생기지 않도록 한다)
update TB_DEPARTMENT set CAPACITY = around(CAPACITY * 1.1);
--실행은 안해봄.

--5. 학번 A413042인 박건우 학생의 주소가 '서울시 종로구 숭인동 181-21' 로 변경되었다고 한다.
--주소지를 정정하기 위해 사용할 SQL 문을 작성하시오.
update TB_STUDENT set STUDENT_ADDRESS = '서울시 종로구 숭인동 181-21'
    where STUDENT_NO = 'A413042' and STUDENT_NAME = '박건우';
--실행해봄.

--6.주민등록번호 보호법에 따라 학생정보 테이블에서 주민번호 뒷자리를 저장하지 않기로 결정하였다.
--이 내용을 반영할 적절한 SQL 문장을 작성하시오.

--update 연습

--7. 의학과 김명훈 학생은 2005년 1학기에 자신이 수강한 '피부생리학' 점수가 잘못되었다는 것을 발견하고는 정정을 요청하였다.
--담당 교수의 확인 받은 결과 해당 과목의 학점을 3.5로 변경키로 결정되었다. 적절한 SQL 문을 작성하시오.

--update 연습

--8. 성적 테이블(TB_GRADE)에서 휴학생들의 성적항목을 제거하시오.
--delete 연습