--function() == method()

--nvl(검사데이터, 검사데이터가 null일때 치환 될 값)

--length() : 특정 데이터의 길이를 측정하여 반환
select length('한글') from dual;
--lengthb() : 특정 데이터의 byte를 측정하여 반환
select lengthb('한글') from dual;

--instr() : 문자열 안에서 특정 문자의 위치 값을 찾는 함수(java의 indexOf)
select instr('Hello World Hi High', 'H', 1, 2) from dual;
-- 'Hello World Hi High' 문자열 안에서 H를 검색하되 1번 글자에서부터 오른쪽으로 검색해가며 1번으로 만나는 'H'의 위치

--substr() : 문자열 내에서 지정한 위치의 문자열을 잘라내어 반환하는 함수 (Java의 subString)
select substr('Hello World Hi High', 1, 3) from dual;
select substr('안녕 세계 안녕 높이', 4, 2) from dual;

--Quiz
--1. 직원 명단에서 이름의 성만 추출하여 출력해봅시다.
select distinct substr(emp_name, 1, 1) as "성" from employee;

--2. 직원명단에서 사원번호, 사원명, 주민번호, 연봉을 출력하세요.
--  단, 주민번호 뒷 6자리는 * 기호로 출력하세요.
--  남자직원의 정보만 출력하세요. (like 사용 금지)
select
    emp_id,
    emp_name,
    (substr(emp_no,1,8) || '******') as "EMP_NO", -- || 는 Java서의 '+ (String Append)'의 의미임.
    (salary * 12) as "연봉"
from employee
where substr(emp_no, 8, 1) = 1;

--abs() : 값의 변위(절대값)를 반환하는 함수
--  따라서 이하 두 예시는 동일 값 반환.
select abs(2) from dual;
select abs(-2) from dual;

--mod() : Java에서 치면 '%' 연산. -> 나머지 연산 함수
select mod(10,3) from dual;
select mod(10,2) from dual;
select mod(10,4) from dual;

--round() : 반올림 함수
select round(126.456) from dual;
select round(126.456, 2) from dual; -- 126.46

--floor() : 소수점 버림 함수
--trunc() : 특정 위치에서 버림 함수.
select trunc(126.456, 1) from dual;

--ceil() : 올림 함수
--올림은 별도의 소수점 지정 함수 없음. 응용해서 올려야 함.
select ceil(126.456) from dual; -- 127;

--------------------------------------------------
--sysdate : 조회하는 순간의 시간 값을 Date 타입으로 반환.
select sysdate from dual;
--months_between : 두 날짜사이의 개월 수를 반환하는 함수.
select emp_name, floor(months_between(sysdate, hire_date)) as "근속개월수" from employee;
--add_months : 특정 날짜에 개월수를 더하는 함수
select add_months(sysdate, 4) from dual;

--next_day : 지정날짜를 기준으로 가장 가까운 요일 date값을 반환
select next_day(sysdate, '월요일') from dual;

--last_day : 지정날짜가 속한 달의 마지막 날 date 값을 반환.
select last_day(sysdate) from dual;

--Quiz
--다음 달 마지막 날짜는 몇일일까요?
select last_day(add_months(sysdate,1)) from dual;

--extract : 날짜값으로부터 연, 월, 일을 추출하는 함수
select extract(year from sysdate) from dual;
select extract(month from sysdate) from dual;
select extract(day from sysdate) from dual;

--Quiz
--1. 사번, 사원명, 입사년도(0000년) 형식으로 출력해보세요.
select
    emp_id,
    emp_name,
    extract(year from hire_date)||'년' as "입사년도"
from employee;

--2. Employee 테이블에서 사원의 이름, hire_date, 년차를 출력하세요.
--년차 출력은 소수점일 경우 올림으로 하여 출력하세요. (28.144 -> 29년차)
--(출력 시 정렬은 입사일 기준으로 오름차순)
select
    emp_name,
    hire_date,
    ceil((sysdate - hire_date)/365) as "년차"
from employee
order by hire_date asc;

-----------------------------------------------------------
--to_char(~,[출력모양]) : 날짜값 또는 숫자값을 문자열로 변환하는 함수.
select to_char(sysdate, 'YYYY"년" MM"월" DD"일" (DY) HH:MI:SS') from dual;
select to_char(1000000, '999,999,999') from dual;
select to_char(1000000, 'L999,999,999') from dual;

--Quiz
--사원명, 고용일(1990/02/06(화)) 형태로 출력해주세요.
select
    emp_name,
    to_char(hire_date, 'YYYY/MM/DD(DY)') as "고용일"
from employee;

--to_date(~,[입력모양]) : 문자열을 날짜형으로 변환하는 함수
select to_date('20180208', 'YYYYMMDD') from dual;

--Quiz
--1945년 8월 15일은 과연 무슨 요일일까.
select to_char(to_date('19450815','YYYYMMDD'),'DAY') from dual;

-----------------------------------------------------
--decode() : 값에 따른 분기점을 만드는 함수. (자바의 삼항연산자, switch)
--( (입력값) , (Case1 조건), (Case1 결과), (Case2 조건), (Case2 결과), …, (else case 결과) )
select decode('Apple', 'Apple', '사과', 'Orange', '오렌지', '포도') from dual;

--Quiz
--사원 번호, 사원명, 성별 (남,여) 형태로 출력하세요.
select
    emp_id,
    emp_name,
    decode(substr(emp_no, 8, 1),
        '1', '남',
        '2', '여',
        '3', '남',
        '4', '여',
        emp_no) as "성별"
from employee;

-------------------------------------------------------------
--case : 조건식에 따른 반환값 분기점 생성 함수
select
    emp_id,
    emp_name,
    case
        when (substr(emp_no,8,1) = '1' or substr(emp_no,8,1) = '3') then '남'
        when (substr(emp_no,8,1) = '2' or substr(emp_no,8,1) = '4') then '여'
        else '??'
    end as "성별"
from employee;

--Quiz
--1. 60년대생 직원들 중에서 60 ~ 64년 까지의 직원들은 '60년생 초반' 이라고 출력.
--              65~69년 까지의 직원들은 '60년생 후반'이라고 출력
--  사번, 이름, 60년생 초/후반
select
    emp_id,
    emp_name,
    case
        when substr(emp_no,2,1) <= 4 then '60년생 초반'
        when (substr(emp_no,2,1) >= 5 and substr(emp_no,2,1) <= 9) then '60년생 후반'
        else '오류'
    end as "나이대"
from employee
where emp_no like '6%';

---- 여기까지는 모두 단일행 함수
-------------------------------------------------------------------
-- 그룹 함수 (SUM, AVG, COUNT, MAX, MIN, ...)

--이 회사의 한달 인건비는 얼마인가? (Salary의 합)
--SUM : 합계 함수
select sum(salary) from employee;

--AVG : 평균 함수
select avg(salary) from employee;

-- COUNT : 행의 개수를 반환하는 함수
select count(*) from employee;

-- MAX : 전체 행에서 가장 큰 값을 반환하는 함수
select max(salary) from employee;
-- MIN : 전체 행에서 가장 작은 값을 반환하는 함수
select min(salary) from employee;

--Quiz
--1. D5 부서 직원들의 급여 평균
select round(avg(salary)) from employee where dept_code = 'D5';
--2. 남직원들의 급여 합계
select sum(salary) from employee where emp_no like '%-1%';

-------------------------------------------------------------
--Quiz
--1. 직원명과 이메일 , 이메일 길이를 출력하세요
--        이름       이메일      이메일길이
--   ex)    홍길동 , hong@kh.or.kr        13
select
    emp_name as "직원명",
    email as "이메일",
    length(email) as "이메일길이"
from employee;

--2. 직원의 이름과 이메일 주소중 아이디 부분만 출력하세요
--   ex) 노옹철   no_hc
--   ex) 정중하   jung_jh
select
    emp_name as "직원명",
    substr(email, 1, instr(email, '@', 1, 1)-1) as "이메일"
from employee;

--3. 60년생의 직원명과 년생, 보너스 율을 출력하세요
--   그때 보너스 값이 null인 경우에는 0% 이라고 출력 되게 만드세요
--       직원명    년생     보너스
--   ex) 선동일     62       30%
--   ex) 송은희     63       0%
select
    emp_name as "직원명",
    substr(emp_no, 1, 2) as "년생",
    nvl (bonus*100, 0)||'%' as "보너스"
from employee
where emp_no like '6%';

--4. '010' 핸드폰 번호를 쓰지 않는 사람의 수를 출력하세요 (뒤에 단위는 명을 붙이세요)
--      인원
--   ex) 3명
select count(*)||'명' as "010이 아닌 사람 수" from employee
    where
        phone not like '010%' or
        phone is null;

-- 강사님. 4번 문제에서 phone 번호가 null인 경우도 '010'번호가 아닌 경우로 세야하겠습니까? 
-- 아니면 값이 있으면서 010이 아닌 경우만 세면 되겠습니까? 
-- "전화번호가 null 인 사람도 010이 아닌 사람으로 카운팅 합시다."


--5. 직원명과 입사년월을 출력하세요 
--   단, 아래와 같이 출력되도록 만들어 보세요
--       직원명         입사년월
--   ex) 전형돈      2012년12월
--   ex) 전지연      1997년 3월
select
    emp_name as "직원명",
    to_char(hire_date, 'YYYY"년"MM"월"') as "입사년월"
from employee;

--6. 직원명과 주민번호를 조회하세요
--   단, 주민번호 9번째 자리부터 끝까지는 '*' 문자로 채워서 출력 하세요
--   ex) 홍길동 771120-1******
select
    emp_name as "직원명",
    substr(emp_no, 1, 8)||'******' as "주민번호"
from employee;

--7. 직원명, 직급코드, 연봉(원)을 조회하세요.
--  단, 연봉은 ￦57,000,000 으로 표시되게 함 (현지 통화 ￦가 적용 되어야 함)
--     연봉은 보너스포인트가 적용된 1년치 급여임
select
    emp_name as "직원명",
    job_code as "직급코드",
    case
        when bonus is not null then to_char((salary * 12) + (salary *12 * bonus), 'L999,999,999,999')
        else to_char((salary * 12), 'L999,999,999,999')
    end as "연봉" 
from employee;

--8. 부서코드가 D5, D9인 직원들 중에서 2004년도에 입사한 직원의 
--   수 조회하세요.
--   [ 사번 사원명 부서코드 입사일 ]
select emp_id as "사번", emp_name as "사원명", dept_code as "부서코드" , hire_date as "입사일" from employee
    where
        (dept_code = 'D5' or dept_code = 'D9') and
        extract(year from hire_date) = 2004;

--9. 직원명, 입사일, 오늘까지의 근무일수 조회하세요. 
--   * 주말도 포함 , 소수점 아래는 버림
select
    emp_name as "직원명",
    hire_date as "입사일",
    floor(sysdate - hire_date) as "근무일수"
from employee;

--10. 모든 직원의 나이 중 가장 많은 나이와 가장 적은 나이를 출력 하세요. (나이만 출력)
--      일까지 모두 포함하면, '621235' 같이 이상한 일수 생일이 있어서 오류남. 따라서 년수로만 계산.
--      모든 직원이 1900년대생이란 가정
--      그렇지 않으면 기본적으로 2000년대 생으로 계산하여, 2060년생 취급으로 음수 나이가 나옴.
select
    max(floor((sysdate - to_date('19'||substr(emp_no,1,2),'YYYY'))/365) + 1) as "최고령", --한국나이 계산용. +1
    min(floor((sysdate - to_date('19'||substr(emp_no,1,2),'YYYY'))/365) + 1) as "최연소"
from employee;

--11.  회사에서 야근을 해야 되는 부서를 발표하여야 합니다.
-- 부서코드가 D5,D6,D9  야근, 그외는 야근없음 으로 출력되도록 하세요
-- 출력 값은 이름,부서코드,야근유무 (부서코드 기준 오름차순 정렬함.)
--   (부서코드가 null인 사람도 야근없음 임)
select
    emp_name as "이름",
    dept_code as "부서코드",
    case
        when (dept_code = 'D5' or dept_code = 'D6' or dept_code = 'D9') then '야근'
        else '야근없음' -- null도 else에 포함됨.
    end as "야근유무"
from employee
order by dept_code asc nulls first; -- nulls first는 결과값 가독 용도로 임의 추가