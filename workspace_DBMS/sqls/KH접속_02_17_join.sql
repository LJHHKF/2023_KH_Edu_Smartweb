--Review
--group by & having
--from -> where -> group by -> having -> select -> oreder by

--JOIN
--하나 이상의 테이블로부터 특정 조건을 충족하는 데이터를 취합하여 결과를 생성하는 문법
--JOIN문은 중첩 for문이다.



select * from department;
select * from job;


-- 조인 문법

--> ANSI 표준 (각 종류 별 상세 정보는 Oracle 전용 이하 설명서 참고)
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
inner join job using (job_code) -- 조건 쓰는 것을 줄일 수 있으나 별칭을 사용할 수 없음.
where job_name = '과장';

--> Oracle 전용
-- JOIN 종류 중 - 크로스 조인 ( Cross Join - Cartesian Product 를 만들어낸다. )
select * from department, job;
-- JOIN 종류 중 - Inner JOIN : Join에 참여한 여러 테이블 정보에서 특정 조건을 만족하는 레코드만을 선별하여 결과 생성
select "e".emp_id, "e".emp_name, "d".dept_title
from employee "e", department "d"
where "e".dept_code = "d".dept_id;


--------------------------
--Quiz
--1. 기술지원부에 속한 사람들의 이름, 부서코드, 급여를 출력하세요.
select
    "e".emp_name as "이름",
    "e".dept_code as "부서코드",
    "e".salary as "급여"
from employee "e"
inner join department "d"
on "e".dept_code = "d".dept_id
where "d".dept_title = '기술지원부';

--2. 해외영업n부 직원들의 사번, 사원명, 부서명, 연봉을 출력해보세요.
select * from department;
select
    e.emp_id as "사번",
    e.emp_name as "사원명",
    d.dept_title as "부서명",
    (e.salary * 12) as "연봉"
from employee e
inner join department d
on e.dept_code = d.dept_id
where d.dept_title like ('해외영업%');

----------------------------------------------
--outer join : join에 참여하는 두 테이블에서 한쪽 테이블의 모든 행을 강제 출력하게 만드는 문법
select
    nvl(emp_name, '배정인원없음'),
    nvl(dept_code, '부서코드없음'),
    nvl(dept_title, '부서명없음')
from employee
full outer join department 
on dept_code = dept_id 
order by 2;
-------------------------------------------------------
-- self join : 같은 테이블/자기 자신을 JOIN하는 문법
select * from job j1 cross join job j2;
select emp_id, emp_name, manager_id from employee; --이런 상황에 사용하기 위해서 필요. 

select
    e2.emp_id as "사번",
    e2.emp_name as "사원명",
    e2.manager_id as "매니저 사번",
    e1.emp_name as "매니저명"
from employee e1
inner join employee e2
on e2.manager_id = e1.emp_id;
--order by e1.emp_id;

----------------------------------------------
--Quiz
--1. 하이유씨와 같은 부서에서 일하는 직원들의 이름, 부서코드, 직급코드를 출력해주세요. (self join 활용)
select
    e2.emp_name as "직원명",
    e2.dept_code as "부서코드",
    e2.job_code as "직급코드"
from employee e1
inner join employee e2
on e1.emp_name = '하이유'
where e1.dept_code = e2.dept_code and e2.emp_name != '하이유';
--또는
select
    e2.emp_name,
    e2.dept_code,
    e2.job_code
from employee e1
inner join employee e2 on e1.dept_code = e2.dept_code
where e1.emp_name = '하이유'
order by 2;

-----------------------------------------------------
--다중 JOIN : 3개 이상의 테이블을 JOIN하여 유의미한 데이터를 생성하는 작업.

--직원명, 부서명, 직급명을 출력해주세요.
select
    e.emp_name,
    d.dept_title,
    j.job_name
from employee e
-- 1
left outer join department d on e.dept_code = d.dept_id 
-- 2. 1의 결과값으로 남은 것 중 job_code가 같다면.
inner join job j on e.job_code = j.job_code
order by e.job_code;

---------------------------------------------
--Quiz
--1. 사원명, 부서명, 근무 지역 코드(National code)를 출력하세요.
select * from department;
select * from location;
select * from national;

select
    e.emp_name as "사원명",
    d.dept_title as "부서명",
    n.national_name as "근무 지역"
from employee e
inner join department d
    on e.dept_code = d.dept_id
inner join location l
    on d.location_id = l.local_code
inner join national n
    on l.national_code = n.national_code;
    
----------------------------------------------------

--JOIN 까지의 중간정리 문제
-- 1. 2020년 12월 25일이 무슨 요일인지 조회하세요.
select to_char(to_date('20201225', 'YYYYMMDD'),'DAY') from dual;

-- 2. 주민번호가 1970년대 생이면서 성별이 여자이고, 성이 전씨인 직원들의 사원명, 주민번호, 부서명, 직급명을 조회하세요.
select
    e.emp_name as "사원명",
    e.emp_no as "주민번호",
    d.dept_title as "부서명",
    j.job_name as "직급명"
from employee e
inner join department d
    on e.dept_code = d.dept_id
inner join job j
    on e.job_code = j.job_code
where
    substr(e.emp_no,1,1) = '7' and
    substr(e.emp_no,8,1) = '2' and
    e.emp_name like '전%';

-- 3. 이름에 "형"자가 들어가는 직원들의 사번, 사원명, 부서명을 조회하세요.
select
    e.emp_id as "사번",
    e.emp_name as "사원명",
    d.dept_title as "부서명"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
where
    e.emp_name like '%형%';

-- 4. 부서별 급여 평균이 300만원 이하인 부서의 부서명과 급여 평균을 출력하세요
-- > 급여 평균은 10000의 자리 아래로 버리고 출력해주세요. 
-- > 또한 한국 로컬 통화 표기법으로 출력해주세요. (￦3,520,000)
select
    nvl(d.dept_title,'부서없음') as "부서명",
    to_char(trunc(avg(salary),-4),'L999,999,999,999') as "급여 평균"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
group by nvl(d.dept_title,'부서없음')
having
    avg(salary) <= 3000000;

-- 5. 해외영업부에 근무하는 사원명, 직급명, 부서코드, 부서명을 조회하세요.
select
    e.emp_name as "사원명",
    j.job_name as "직급명",
    e.dept_code as "부서코드",
    d.dept_title as "부서명"
from employee e
inner join department d
    on e.dept_code = d.dept_id
inner join job j
    on e.job_code = j.job_code
where
    d.dept_title like '해외영업%'
order by e.dept_code; -- order by 는 가독성용 임의 추가.

-- 6. 보너스포인트를 받는 직원들의 사원명, 보너스포인트, 부서명, 근무지역명을 조회하세요.
select
    e.emp_name as "사원명",
    (e.salary * e.bonus) as "보너스포인트",
    d.dept_title as "부서명",
    l.local_name as "근무지역명"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
inner join location l
    on d.location_id = l.local_code
where bonus is not null
order by d.dept_title; -- order by는 가독성용 임의 추가

-- 7. 부서코드가 D2인 직원들의 사원명, 직급명, 부서명, 근무지역명을 조회하세요.
select
    e.emp_name as "사원명",
    j.job_name as "직급명",
    d.dept_title as "부서명",
    l.local_name as "근무지역명"
from employee e
inner join job j
    on e.job_code = j.job_code
inner join department d
    on e.dept_code = d.dept_id
inner join location l
    on d.location_id = l.local_code
where
    e.dept_code = 'D2';

-- 8. 한국(KO)과 일본(JP)에 근무하는 직원들의 사원명, 부서명, 근무지역명, 국가명을 조회하세요.
select
    e.emp_name as "사원명",
    d.dept_title as "부서명",
    l.local_name as "근무지역명",
    n.national_name as "국가명"
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
order by d.dept_title; -- order by는 가독성용 임의 추가

-- 9. 모든 사원 중 선동일을 매니저로 두고 있는 직원의 이름, 직원의 부서명, 매니저의 이름을 출력하세요.
select
    e2.emp_name as "사원명",
    d.dept_title as "부서명",
    e1.emp_name as "매니저명"
from employee e1
inner join employee e2
    on e2.manager_id = e1.emp_id
inner join department d
    on e2.dept_code = d.dept_id
where e1.emp_name = '선동일'
order by d.dept_title; -- order by는 가독성용 임의 추가

-- 10. 보너스포인트가 없는 직원들 중에서 직급이 차장과 사원인 직원들의 사원명, 직급명, 급여를 조회하세요 (join과 in 사용)
select
    e.emp_name as "사원명",
    j.job_name as "직급명",
    to_char(e.salary, 'L999,999,999,999') as "급여"
from employee e
left outer join department d
    on e.dept_code = d.dept_id
inner join job j
    on e.job_code = j.job_code
where
    e.bonus is null and
    j.job_name in ('차장', '사원');

-- 11. 재직중인 직원과 퇴사한 직원의 수를 조회하세요.
select
    count(distinct(e2.emp_name)) as "재직중인 직원의 수",
    count(distinct(e1.emp_name)) as "퇴사한 직원의 수",
    count(distinct(e3.emp_name)) as "총 직원수"
from employee e1
inner join employee e2
    on e1.ent_yn = 'Y'
inner join employee e3
    on e2.ent_yn = 'N';
--또는 (타인 풀이 참고)
select
    decode(ent_yn, 'Y', '퇴직', 'N', '재직') as "재직유무",
    count(*) as "인원수"
from employee
group by decode(ent_yn, 'Y', '퇴직', 'N', '재직');
--또는 (강사님 화면 참고)
select
    (select count(*) from employee where ent_yn = 'Y') as "퇴직자 수", 
    (select count(*) from employee where ent_yn = 'N') as "재직자 수"
from dual;
