--group by : 그룹화 된 데이터에 대한 정보를 다루는 문법

select dept_code as "부서코드", sum(salary) as "부서 당 급여 합계"
from employee
group by dept_code 
order by dept_code asc;

-- from -> where -> group by -> having -> select -> order by

-- Quiz
-- 1.  직급 별 급여 평균을 출력해주세요. (직급 별 오름차순으로 정렬하여 출력)
-- null 값은 Intern 으로 표현
select 
    nvl(job_code, 'Intern') as "직급",
    to_char(avg(salary), 'L999,999,999,999') as "직급 당 급여 평균"
from employee
group by nvl(job_code, 'Intern')
order by job_code asc;

select decode(substr(emp_no,8,1), '1', '남', '2', '여') as "성별"
from employee
group by decode(substr(emp_no,8,1), '1', '남', '2', '여');

--성별 별 인원수를 출력해보세요.
select
    decode(substr(emp_no,8,1), '1', '남', '2', '여') as "성별",
    count(*)||'명' as "성별 별 인원수"
from employee
group by decode(substr(emp_no,8,1), '1', '남', '2', '여');

--성별 별 급여 평균도 출력해보세요.
--J1과 J2를 뺀 나머지 인원에서
select
    decode(substr(emp_no,8,1), '1', '남', '2', '여') as "성별",
    to_char(avg(salary), 'L999,999,999,999') as "급여 평균"
from employee
where job_code in ('J3', 'J4', 'J5', 'J6', 'J7')
group by decode(substr(emp_no,8,1), '1', '남', '2', '여');

--나이대 별 급여 평균
--급여 평균은 100000의 자리까지 반올림 할 것
--급여 평균 출력은 로컬 통화 모양 (ex> \2,542,030) 형태로 출력
select
    substr(emp_no,1,1)||'0년생' as "나이대",
    to_char(round(avg(salary),-5), 'L999,999,999,999') as "급여 평균"
from employee
group by substr(emp_no,1,1)||'0년생'
order by 1;
--또는
select
    case
        when substr(emp_no,1,2) between 60 and 69 then '60년대생'
        when substr(emp_no,1,2) between 70 and 79 then '70년대생'
        when substr(emp_no,1,2) between 80 and 89 then '80년대생'
        when substr(emp_no,1,2) between 90 and 99 then '90년대생'
    end as "나이대",
    to_char(round(avg(salary),-5), 'L999,999,999,999') as "급여 평균"
from employee
group by
    case
        when substr(emp_no,1,2) between 60 and 69 then '60년대생'
        when substr(emp_no,1,2) between 70 and 79 then '70년대생'
        when substr(emp_no,1,2) between 80 and 89 then '80년대생'
        when substr(emp_no,1,2) between 90 and 99 then '90년대생'
    end
order by 1;
--또는
select
    decode(substr(emp_no,1,1),'6','60년대생', '7','70년대생', '8','80년대생') as "나이대",
    to_char(round(avg(salary),-5), 'L999,999,999,999') as "급여 평균"
from employee
group by decode(substr(emp_no,1,1),'6','60년대생', '7','70년대생', '8','80년대생')
order by 1;

--order by dept_code, salary
--부서코드로 정렬한 다음, 그 결과 중에서 급여 기준으로 다시금 정렬해주세요.

--group by dept_code, job_code
--부서 코드 기준으로 헤쳐 모인 다음, 그 안에서 다시 직급 별로 헤쳐 모여.
-- == 같은 부서 내, 같은 직급 별로 헤쳐 모이세요.

--부서 내 직급 별 인원수 파악
select
    dept_code as "부서 코드",
    job_code as "직급 코드",
    count(*) as "인원수"
from employee
group by dept_code, job_code
order by 1, 2;

--Quiz
--1. 부서 내 성별 별 인원 수 파악
select
    nvl(dept_code, '부서없음') as "부서코드",
    decode(substr(emp_no, 8, 1), '1', '남', '2', '여', 'null') as "성별",
    count(*) as "인원수"
from employee
group by
    nvl(dept_code, '부서없음'),
    decode(substr(emp_no, 8, 1), '1', '남', '2', '여', 'null')
order by 1;

--2. 부서 별 급여 평균이 300만원 이상인 부서의 부서코드 및 급여평균을 출력하세요.
--  group by 로 인해 그룹화된 데이터에 대해 조건을 비교할 때는 having 절을 사용.
select
    nvl(dept_code, '부서없음') as "부서코드",
    floor(avg(salary)) as "급여평균"
from employee
group by nvl(dept_code, '부서없음')
having avg(salary) >= 3000000
order by 1;

--Quiz
--1. 인원이 3명 미만인 직급 코드의 인원을 충원하려고 함.
--인원이 3명이 안되는 직급코드에 대하여 직급코드와 인원수를 출력하세요.
select
    job_code as "직급코드",
    count(*) as "인원수"
from employee
group by job_code
having count(*) < 3
order by job_code;