-- 주석
-- select 문으로 특정 데이터 출력
-- 기본 문법
-- select * | [조회할 컬럼명1, 조회할 컬럼명2, 조회할 컬럼명3 ...] 
-- from 테이블명;


-- countries테이블의 모든 컬럼 조회   
select * from countries;

-- 부서테이블의 모든 컬럼 조회
select * from departments;

-- 사원테이블의 모든 컬럼 조회
select * from employees;

-- 사원테이블의 사원 이름만 조회
select first_name from employees;

-- 부서테이블의 부서명만 조회
select department_name from departments;

-- 지역테이블의 도로명 주소만 조회
select street_address from locations;

-- 사원테이블에서 사원명과 급여를 조회
SELECT first_name, salary from employees;

-- 사원테이블에서 사번, 이름, 입사일을 조회
select employee_id, first_name, hire_date  from employees;

-- (1)
-- 컬럼명에 별칭(alias)를 지어주기
-- 컬럼명에 별칭을 지으면 컬럼을 기술한 뒤, 컬럼명 뒤에 as 라는 키워드를 쓴다.
select first_name as name from employees;

-- 사번과 이름으로 별칭을 지어서 출력하기
select employee_id as 사번, first_name as 이름 from employees;

-- as라는 키워드를 생략해도 된다. 
select employee_id 사번, first_name 이름 from employees;

-- 별칭에 공백이나 특수문자를 포함하는 경우 별칭을 "별 칭"(큰따옴표)로 묶는다.
select first_name "이 름" from employees;

-- distinct 키워드는 중복된 데이터를 한번씩만 출력하게 한다.
select distinct job_id from employees;
select distinct job_id, first_name from employees; -- 이렇게는 사용하지 말자.

-- (2) where절을 사용해서 조건절을 사용할 수 있다. 
-- where절의 조건식에 사용되는 연산자 : >, <, >=, <=, =, !=
-- != 과 <> 과 ^= 가 모두 not equal로 같다.
-- 문자열이나 날자를 표현할 경우 ''(작은 따옴표)를 이용한다.
select * from employees where employee_id >= 200; -- 사원번호가 200이상인 사원을 출력

-- 사원테이블에서 급여가 5000 이상인 사원의 사번, 이름, 급여 출력
select employee_id, first_name, salary from employees where salary >= 5000;

-- 사원테이블에서 이름이 adam이라는 사원의 사번과 이름, 입사일을 조회
select employee_id, first_name, hire_date from employees where first_name = 'Adam'; -- 문자열은 대소문자 구분함.

-- 지역테이블에서 지역번호가 1800번 이하인 지역의 모든 컬럼을 조회
select * from locations where location_id <= 1800;

-- 사원테이블에서 입사일이 2002년 이전에 입사한 사원들의 사번, 이름, 급여, 입사일을 출력
select employee_id, first_name, salary, hire_date from employees where hire_date < '02/01/01';

-- 조건연산자를 연결할 때 논리연산자(and, or, not)를 사용할 수 있다.
-- 사번이 130번 보다 작거나 급여가 5000 보다 큰 사원들의 사번, 급여를 출력
select employee_id, salary from employees where employee_id < 130 or salary > 5000;

-- 급여가 5000 이상이고 부서번호가 30번 보다 작은 사원들의 사번, 급여, 부서번호를 출력
select employee_id, salary, department_id from employees where salary >= 5000 and department_id < 30;

-- 부서번호가 100번이 아닌모든 사원들의 모든 컬럼을 출력
select * from employees where department_id != 100;
select * from employees where department_id <> 100; 
select * from employees where department_id ^= 100; -- != 과 <> 과 ^= 가 같다.

-- (4) between A and B 연산자 : A이상이고 B이하
-- 급여가 5000 이상이고 급여가 7000이하인 사원의 이름, 급여 출력
select first_name, salary from employees where salary >= 5000 and salary <= 7000;
select first_name, salary from employees where salary between 5000 and 7000;

-- 입사 년도가 2003년 에서 2005년 사이인 사원의 모든 정보를 조회
select * from employees where hire_date between '03/01/01' and '05/12/31';

-- (5) in (A, B, C, ...) 연산자 : A 또는 B 또는 C
-- 부서번호가 10번 또는 부서번호가 50번 또는 부서번호가 100번인 사원들의 모든 정보 출력
-- (or)
select * from employees where department_id = 10 or department_id = 50 or department_id = 100;
-- (in)
select * from employees where department_id in (10, 50, 100);

-- (5) 패턴을 이용하여 검색하는 like 연산자
-- 1) 컬럼명 like 패턴
-- 2) 패턴은 아래의 2가지 (와일드카드)를 이용할 수 있다. 
-- '%' : 문자가 없거나 1개 이상의 문자가 어떤 값이 오든 상관 없다. 
-- '_' : 하나의 문자가 어떤 값이 오든 상관 없다. 

-- 이름이 s로 시작하는 모든 사원의 정보를 출력
select * from employees where first_name like 'S%';

-- 이름이 n으로 끝나는 모든 사원들의 정보를 출력
select * from employees where first_name like '%n';

-- 직무가 AN으로 끝나는 모든 사람들의 정보를 출력
select * from employees where job_id like '%AN';

-- 이름이 끝에서 2번째 글자가 a인 사람들의 모든 정보 출력
select * from employees where first_name like '%a_';

-- 이름의 세번째 글자가 r인 사람들의 모든 정보 출력
select * from employees where first_name like '__r%';

-- 직무에 _가 포함된 사람들의 모든 정보 출력
select * from employees where job_id like '%_%';

-- (6) null을 위한 연산자 (null : 데이터가 없다.)
-- 커미션을 받는 모든 사원들의 모든 정보를 출력
select * from employees where commission_pct != null; -- 적용 안됨.
-- is null (null과 같다.), is not null(null과 같지 않다.)
select * from employees where commission_pct is null; 
select * from employees where commission_pct is not null; 

-- (7) 정렬 하기 위해서는 order by 절을 사용
-- order by 컬럼명 [정렬기준]
-- default : asc

-- 사원들의 급여를 내림차순으로 정렬해서 모든 사원의 정보를 출력
select * from employees order by salary; 
select * from employees order by salary desc; 

-- 정렬을 comma(,)로 구분하여 여러개를 사용할 수 있다.
-- 부서번호가 50번인 사원들의 모든 정보를 출력하되 이름, 급여 오름차순으로 정렬하고, 급여가 같은 경우 이름은 내림차순으로 정렬
select * from employees where department_id = 50 order by salary, first_name desc; 







