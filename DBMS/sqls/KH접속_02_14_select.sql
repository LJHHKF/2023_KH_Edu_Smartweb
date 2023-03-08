
desc employee;
-- describe employee; : employee 테이블에 대해 묘사(설명)하라

select emp_id, emp_name, emp_no from employee;
-- employe 테이블에서 emp_id, emp_name, emp_no 컬럼에 해당하는 데이터를 조회하라.

select * from employee;
-- employee 테이블의 모든 컬럼 데이터를 조회하라

select emp_id, emp_name from employee where emp_id = 207;

--Quiz
--1.Employee 테이블에 존재하는 모든 직원의 사번, 이름, 급여를 출력해보세요.
select emp_id, emp_name, salary from Employee;
--2. 직원 중 D9 부서에 속해 있는 직원들의 사번, 이름, 부서코드를 출력해보세요.
--오라클 문자열은 '' 기호로 표현
select emp_id, emp_name, dept_code from employee where dept_code = 'D9';

select * from employee where salary >= 4000000;
--J1과 J2가 Job 테이블에서 어떤 직책인지 알아볼 것.
desc job;
select * from job;

--Quiz
--1. 직원 중 급여를 200만원 이하로 받는 직원의 이름 및 부서코드, 직급코드를 출력해보세요.
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
--1. 급여가 200~300만 사이인 직원의 사번, 이름, 급여, 직급 코드를 출력하세요.
select emp_id, emp_name, salary, job_code
from employee 
where salary >= 2000000 and salary <= 3000000;
--------- 또는
select emp_id, emp_name, salary, job_code
from employee
where salary between 2000000 and 3000000;

--Quiz
--1. Employee 테이블의 직원 중에 job_code 가 J3 이면서, 급여가 350만원 이상인 직원의 이름과 전화번호
select emp_name, phone
from employee
where job_code = 'J3' and salary >= 3500000;

--2. Employee 테이블의 직원 중에 부서코드가 D5 이거나 D6 이거나 D9 인 직원의 이름과 부서코드를 출력해보세요.
select emp_name, dept_code
from employee
where dept_code = 'D5' or dept_code = 'D6' or dept_code = 'D9';
------또는
select emp_name, dept_code
from employee
where dept_code in ('D5', 'D6', 'D9');

select *
from employee
where dept_code is null;
--또는 is not null

--alias
select emp_name 사원명, salary 월급 , salary * 12 as "연봉", '원' as 단위 from employee;

----------------------이하 'sysdate'
--실행되는 시점에서의 현재 시간
--dual : 내장 테이블 - SQL 이용자가 특정 기능을 테스트 할 때 사용할 수 있도록 미리 만들어 둔 허수아비 테이블
--sysdate : sysdate가 호출되는 시점의 날짜 및 시간을 반환하는 oracle 기능/명령
--          date type의 결과를 반환한다.
--          날짜 타입에 대한 수치 연산은 (+,-) 일수 연산이 적용된다.
--          날짜 타입과 날짜 타입을 연산하면 두 날짜 사이의 일차가 반환된다.
select sysdate from dual;

select 'ABC' - 10  from dual;
select 10 -5 from dual;
select sysdate - 1 from dual;

--floor : 실수를 파라미터로 전달받으면 소수점 자리수를 버림하고 정수 자리만 반환하는 메서드
select
    emp_id,
    emp_name,
    hire_date 입사일,
    floor(sysdate - hire_date)||'일'  as 근무일수
from
    employee;

--Quiz
--1. Employee 테이블에서 근속년수가 20년 이상 된 직원의 이름, 월급, 보너스율 을 출력해보세요.
select emp_name, salary, bonus
from employee
where floor((sysdate - hire_date)/365) >= 20;

--2. 모든 직원의 이름, 연봉, 실수령액(연봉 - 연봉 * 3%))을 출력해보세요.
select
    emp_name,
    salary * 12 as "연봉",
    ((salary * 12) - (salary * 12 * 0.03)) as "실수령액"
from employee;

select * from employee
    where hire_date between '90/01/01' and '01/12/31';

--------------------------- like
select * from employee
    where emp_name like '전%';

select * from employee
    where emp_name like '%연';
    
select * from employee
    where emp_name like '%하%';


--Quiz
--1. 모든 직원 중에 전화번호 처음 3자리가 010이 아닌 직원의 이름과 전화번호를 출력해보세요.
select emp_name, phone from employee
    where phone not like '010%';

--2. 모든 직원 중에서 이름에 '이' 자가 들어가는 사람을 모두 찾아 이름을 출력해보세요.
select emp_name from employee
    where emp_name like '%이%';
--3. 모든 직원 중에서
    -- 부서 코드가 D9 또는 D6이고
    -- 월급이 270만원 이상이며
    -- email에 알파벳 s가 포함되어 있고
    -- 고용일이 2001년 1월 1일부터 2005년 12월 31일 사이인 직원의
    -- 이름, 부서코드, 급여, email, 고용일을 출력하세요.
select emp_name, dept_code, salary, email, hire_date
from employee
where
    dept_code in ('D9','D6') and
    salary >= 2700000 and
    email like '%s%' and
    hire_date between '2001/01/01' and '2005/12/31';
    
--Quiz
--1. 직속상사(manager_id)가 없고 부서 배치(dept_code)도 받지 않은 직원의 사번 및 이름을 출력하세요.
select emp_id, emp_name from employee
    where 
        manager_id is null and 
        dept_code is null;
--2. 부서 배치를 받지 않았지만 보너스는 지급되는 직원의 이름 및 보너스를 출력하세요.
select emp_name, bonus from employee
    where
        dept_code is null and
        bonus is not null;
        
---------------------------- order by
--order by : 쿼리 실행 결과에서 특정 조건을 이용하여 데이터를 정렬하는 문법

select emp_id, emp_name, dept_code, salary from employee
order by dept_code desc nulls last, salary asc;

--select 서 n번째로 참조하는 컬럼을 기준으로 정렬하겠다.
--즉 위의 코드와 같은 의미
select emp_id, emp_name, dept_code, salary from employee
order by 3 desc nulls last, 4 asc;

----------------------------------최종 문제
--문제1
--근속년수가 5년 이상, 10년 이하인 직원의 이름, 주민번호, 급여, 입사일을 검색하세요.

select emp_name, emp_no, salary, hire_date from employee
    where floor((sysdate - hire_date) / 365) >= 5 and floor((sysdate - hire_date) / 365) <= 10;

--문제2
--재직 중이 아닌 직원의 이름 부서코드를 검색하세요.(퇴사 여부  ENT_YN)

select emp_name, dept_code from employee where ent_yn = 'Y';

--문제3
--근속년수가 10년 이상인 직원들을 검색하여
--출력 결과는 이름, 급여, 근속년수(소수점X)를, 근속년수 기준으로 오름차순으로 정렬하여 출력하세요.
--단, 급여는 50% 인상된 급여로 출력되도록 하세요.

select
    emp_name,
    (salary * 1.5) as "급여",
    (floor((sysdate - hire_date) / 365)) as "근속년수"
from employee
where
    floor((sysdate - hire_date) / 365) >= 10
order by
    3 asc;

--문제4.
--입사일이 99/01/01 ~ 10/01/01인 사람 중에서 급여가 2000000 원 이하인 사람의
--이름, 주민번호, 이메일, 폰번호, 급여를 검색하세요.

select emp_name, emp_no, email, phone, salary from employee
    where
        (hire_date between '1999/01/01' and '10/01/01') and
        salary <= 2000000;

--문제5 
--급여가 2000000원, 3000000원 인 여직원 중에서 4월 생일자를 검색하여
--이름, 주민번호, 급여, 부서코드를 주민번호 순으로 (내림차순) 출력하세요.
--단, 부서코드가 null인 사람은 부서코드가 '없음'으로 출력하세요. // 이 조건은 지금 못 품. 넘어갈 것.

select emp_name, emp_no, salary, nvl(dept_code, '없음') as "DEPT_CODE" from employee
    where
        (salary between 2000000 and 3000000) and
        emp_no like '_______2%' and
        emp_no like '__04%'
    order by emp_no desc;    
        
--문제6.
--남자 사원 중 보너스가 없는 사원의 오늘까지 근무일을 측정하여
--1000일 마다(소수점 제외)
--급여의 10% 보너스를 계산하여 이름, 특별 보너스 (계산 금액) 결과를 출력하세요. // 복리? 아니면 10%, 20%, 30%? 일단 전자는 지금 모름. 후자로.
--단, 이름 순으로 오름차순 정렬하여 출력하세요.

select
    emp_name,
    (salary * (floor((floor(sysdate - hire_date)/1000)) * 0.1)) as "특별 보너스"
from employee
where
    emp_no like '_______1%' and
    bonus is null;