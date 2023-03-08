
--SQL
--DDL
--DML
    -- insert, update, select, delete
    -- select (DQL) : 
    -- select가 DB 파트의 60% 이상이라고 보면 됨.
    -- select 기초 -> function() -> group by -> having -> join -> [union -> subquery]
--DCL
--TCL

---------------------------------------------------------------------
--union
select dept_id,dept_title from department
union
select * from job;

--테이블 생성 [DDL]
create table A(
    data number -- 테이블명(변수명 변수데이터형식 ...)
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

drop table A; -- 테이블 지우기

select * from A
union
select * from B;
--union : 두 테이블 간의 합집합이며 그 중 중복된 행은 1회만 출력한다.

select * from A
union all
select * from B;
-- union all : 두 테이블 간의 중복을 허용하는 합집합

select * from A
intersect
select * from B;
-- intersect : 두 테이블 간의 교집합(중복된 값만)

select * from A
minus
select * from B;
-- minus : 차집합, A-B 집합 (A에서 B와의 교집합 제거)

---------------------------------------------------------
--SubQuery : 쿼리 안의 쿼리
--단일행 단일열 subquery
--다중행 subquery
--다중열 subquery
--다중행 다중열 subquery
--inline view
--상관 subquery

select manager_id from employee where emp_name='전지연'; -- 214 값이 돌아온다.
select emp_name from employee where emp_id = 214;
--전지연 직원의 이름을 통해 매니저 id를 파악하고, 이 id를 통해 매니저의 직원명을 파악.
--self join으로도 해결 가능함. 그러나 문법적으로 어렵고 성능적으로도 좋다고 보기 힘듦.

select
    emp_name
from employee
where
    emp_id = (select manager_id from employee where emp_name = '전지연');

--Quiz
--1. 윤은해 직원의 급여와 동일한 급여를 받는 직원의 이름 및 급여 출력.
select
    emp_name as "사원명",
    salary as "급여"
from employee
where
    (select salary from employee where emp_name = '윤은해') = salary and
    emp_name != '윤은해';

--2. 전 직원 급여 평균보다 급여를 많이 받는 직원의 이름, 직급코드, 급여를 출력하세요.
select
    emp_name as "직원명",
    job_code as "직급코드",
    salary as "급여"
from employee
where
    (select avg(salary) from employee) <= salary;
--단순히 avg(salary) <= salary 로 쓰면 말이 되는 것 같은데,
--'where 절에서는 group 함수를 쓸 수 없다'는 규칙이 있어서 사용할 수 없음.

--3. D1, D2 부서의 직원 중에서 급여가 D5부서의 급여 평균보다
--  많이 받는 직원들의 부서번호, 사번, 사원명, 급여를 출력하세요.
select
    dept_code as "부서번호",
    emp_id as "사번",
    emp_name as "사원명",
    salary as "급여"
from employee
where
    dept_code in ('D1', 'D2') and
    salary >= (select avg(salary) from employee where dept_code = 'D5');

--4.급여가 제일 많은 사람과 제일 적은 사람의, 사번 사원명 급여를 출력해보세요.
select
    emp_id as "사번",
    emp_name as "사원명",
    salary as "급여"
from employee
where
    salary in ((select max(salary) from employee)
                ,(select min(salary) from employee));

-----------------------------------------------------------
--다중행 단일열 서브쿼리
--박나라, 송종기 직원의 부서코드와 동일한 부서에 속한 직원들의 정보
select *
from employee
where
    dept_code in (select dept_code from employee where emp_name in ('박나라','송종기'));

--Quiz
--1. 차태연 또는 전지연 직원의 급여등급 (sal_level)과 같은 급여등급인 직원의 직급명과 사원명을 출력
select
    j.job_name as "직급명",
    e.emp_name as "사원명",
    e.sal_level as "급여등급"
from employee e
inner join job j
    on e.job_code = j.job_code
where
    e.sal_level in (select sal_level from employee where emp_name in ('차태연', '전지연'));

--2. 직급이 대표거나 부사장이 아닌 모든 직원의 사원명, 직급코드를 출력
select
    emp_name as "사원명",
    job_code as "직급코드"
from employee 
where
    job_code not in (select job_code from job where job_name in ('대표', '부사장'));
    
-- '=' 은 in으로 처리가 가능한데, 작다와 크다는 어떻게 할 수 있겠는가?
-- D5 부서의 직원들의 급여보다 많이 받는 직원의 정보를 출력
--현재는 표현 애매. (누구보다든지 간에 / 이들 모두보다 많이)
select emp_name as "사원명", salary as "급여" from employee
    where salary > all(select salary from employee where dept_code='D5');
--또는
select emp_name as "사원명", salary as "급여" from employee
    where salary > (select max(salary) from employee where dept_code='D5');

----------------------------------------------------------------
--다중 열 서브쿼리 == 다중행 다중열 서브쿼리 문법과 동일
--퇴사한 사람과 같은 부서, 같은 직급인 사람들 출력
select dept_code, job_code from employee where ent_yn='Y';
select emp_name, dept_code, job_code from employee
    where (dept_code, job_code) in (select dept_code, job_code from employee where ent_yn='Y');

--Quiz
--1. 생일이 8월 8일인 사원들과 같은 부서코드, 같은 직급코드인 직원들의 사원명과 생일을 출력
select
    dept_code as "부서코드",
    job_code as "직급코드",
    emp_name as "사원명",
    to_char(to_date('19'||substr(emp_no,1,6), 'YYYYMMDD'), 'YYYY"년" MM"월" DD"일"') as "생일"
    --35일 등, 현실에 있을 수 없는 일수 생일이 있어서 필터 안된 때 돌리면 오류남. 이하 주석 코드로 고쳐써야 함.
    --substr(emp_no,1,2)||'년 '||substr(emp_no,3,2)||'월 '||substr(emp_no,5,2)||'일' as "생일"
from employee
where
    (dept_code,job_code) in (select dept_code, job_code from employee
                                where substr(emp_no,3,4) = '0808')
     and substr(emp_no,3,4) != '0808';

--2. 부서별 급여를 가장 많이 받는 직원의, 부서코드, 직원이름 및 급여를 출력해주세요.
--  부서 코드로 정렬
select
    nvl(dept_code, '인턴') as "부서코드",
    emp_name as "직원이름",
    salary as "급여"
from employee
where
    (nvl(dept_code, '인턴'), salary) in
        (select nvl(dept_code, '인턴'), max(salary) from employee
        group by nvl(dept_code, '인턴'))
order by dept_code asc;

----------------------------------------------------------------
--Subquery를 사용하는 또 다른 유형 - select편
select
    emp_name as "사원명",
    (select dept_title from department where dept_code = dept_id) as "부서명"
from employee;

--직원명과 직급명을 출력하세요. (단, Subquery로 푸세요)
select
    e.emp_name as "직원명",
    (select j.job_name from job j where e.job_code = j.job_code) as "직급명"
from employee e;

--inline view
select emp_id, emp_no from (select emp_id,emp_name,emp_no from employee);

--subquery 일단 종료
-------------------------------------------------------------------------
--rank 관련 함수 [사용방법이 독특해서 뒤로 따로 빠진 함수]
select emp_name, salary, rank() over(order by salary desc) as "급여랭킹" from employee;
select emp_name, salary, dense_rank() over(order by salary desc) as "급여랭킹" from employee;
select emp_name, salary, row_number() over(order by salary desc) "급여 랭킹" from employee;

--Quiz
--1. 급여 내림차순으로 행마다 번호를 붙였을 때, 10~15위까지의 데이터만 출력해주세요.
select
    emp_name as "사원명",
    salary as "급여",
    "급여 랭킹"
from (select emp_name, salary, row_number() over(order by salary desc) as "급여 랭킹" from employee)
where "급여 랭킹" between 10 and 15;

------------------------------------------------------------------
--Quiz
--1. 기술지원부에 속한 사람들의 사람의 이름,부서코드,급여를 출력하시오
select
    e.emp_name as "사원명",
    e.dept_code as "부서코드",
    to_char(e.salary, 'L999,999,999,999') as "급여"
from employee e
inner join department d
    on e.dept_code = d.dept_id
where d.dept_title = '기술지원부';

--2. 기술지원부에 속한 사람들 중 가장 연봉이 높은 사람의 이름,부서코드,급여를 출력하시오
-- 출력은 결국 급여라서 임의로 검색도 급여 기준으로 최대치를 찾음.
--연봉 계산 => (급여+(급여*보너스))*12
--단순 급여 계산으론 '장쯔위', 연봉 계산식 적용 시 '이태림'
select
    e.emp_name as "사원명",
    e.dept_code as "부서코드",
    to_char(e.salary, 'L999,999,999,999') as "급여"
from employee e
inner join department d
    on e.dept_code = d.dept_id
where (e.dept_code, (salary + (salary * nvl(bonus, 0)))*12) in
        (select dept_code, max((salary + (salary * nvl(bonus, 0)))*12) from employee group by dept_code
            having dept_code = (select dept_id from department where dept_title = '기술지원부'));

--3. 매니저가 있는 사원중에 월급이 전체사원 평균을 넘고 사번,이름,매니저 이름,월급(만원단위부터)을 구하시오.
--단, JOIN을 이용하시오
select
    e1.emp_id as "사번",
    e1.emp_name as "사원명",
    e2.emp_name as "매니저 이름",
    e1.salary / 10000 ||'만원' as "월급"
    --to_char(round(e1.salary, -4), 'L999,999,999,999') as "월급"
from employee e1
inner join employee e2
    on e1.manager_id = e2.emp_id
where
    e1.manager_id is not null
    and e1.salary > (select avg(salary) from employee);

--4. 각 직급마다 급여 등급이 가장 높은 직원의 이름, 직급코드, 급여, 급여등급 조회
select
    emp_name as "사원명",
    job_code as "직급코드",
    salary as "급여",
    sal_level as "급여등급"
from employee
where
    (job_code, sal_level) in
        (select job_code, 'S'||Max(to_number(substr(sal_level,2,1))) from employee
            group by job_code)
order by job_code;

--5. 가장 보너스를 많이 받는 순으로 사원들을 뽑았을때 4~8위의 순위, 사원명, 급여, 보너스율을 출력
select
    "보너스 랭킹" as "순위",
    emp_name as "사원명",
    salary as "급여",
    nvl(bonus*100, 0)||'%' as "보너스율"
from (select
    emp_name,
    salary,
    bonus,
    row_number() over(order by nvl(bonus,0) desc) as "보너스 랭킹"
        from employee)
where "보너스 랭킹" between 4 and 8;

--6. 부서별 평균 급여가 2200000 이상인 부서명, 평균 급여 조회 단, 평균 급여는 소수점 버림
select
    d.dept_title as "부서명",
    floor(avg(salary)) as "평균 급여"
from employee e
inner join department d
    on e.dept_code = d.dept_id
group by d.dept_title
having avg(salary) >= 2200000;

--7. 직급의 연봉 평균보다 적게 받는 여자사원의 
--사원명,직급코드,부서코드,연봉을 이름 오름차순으로 조회하시오 연봉 계산 => (급여+(급여*보너스))*12
--못 풀었음.
select
    e1.emp_name as "사원명",
    e1.job_code as "직급코드",
    e1.dept_code as "부서코드",
    (e1.salary + (e1.salary * nvl(e1.bonus, 0)))*12 as "연봉"
from employee e1
where
    (e1.salary + (e1.salary * nvl(e1.bonus, 0)))*12 <
        (select avg((e2.salary + (e2.salary * nvl(e2.bonus, 0)))*12) from employee e2
            where e1.job_code = e2.job_code)
    and substr(emp_no,8,1) = '2'
order by emp_name asc;
--이하 교수님 풀이
select
    e1.emp_name 사원명,
    e1.job_code 직급코드,
    e1.dept_code 부서코드,
    연봉
from employee e1
join (select job_code, avg(salary*(1+nvl(bonus, 0))*12) 직급평균연봉
      from employee
      group by job_code) e2
    on e1.job_code = e2.job_code
join (select salary*(1+nvl(bonus, 0))*12 연봉, emp_id from employee) e3
    on e3.emp_id = e1.emp_id
where (substr(emp_no, 8, 1) = 2) 
    and
    (연봉 < 직급평균연봉)
order by 1;