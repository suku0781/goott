select 5 + 3 from dual;
desc dual;
select * from dual;
desc employees;
--------------------------------------------------
-- (1) 문자 함수
--------------------------------------------------
-- 1) lower() : 소문자 변환
select 'DataBase', lower('DataBase') from dual;

-- 이름이 lex인 사원의 모든 정보 출력
select * from employees where lower(first_name) = 'lex';

-- 2) upper() : 대문자 변환
select 'DataBase', upper('DataBase') from dual;

-- 이름이 LEX인 사원의 모든 정보 출력
select * from employees where upper(first_name) = 'LEX';

-- 직무가 'it_prog'인 사람의 모든 정보를 출력
select * from employees where lower(job_id) = 'it_prog';
select * from employees where job_id = upper('it_prog');

-- 3) initcap() : 첫글자만 대문자로 변환 (나머지는 소문자)
select 'DataBase', initcap('DataBase') from dual;

-- 이름이 LEX인 사원의 모든 정보 출력
select * from employees where first_name = initcap('LEX');

-- 4) 문자열을 붙이는 concat함수 (문자열을 2개만 연결할 수 있다.)
-- || 를 사용하면 2개 이상 문자열을 붙일 수 있다.
select concat('data','base') from dual;
select concat('data','base', 'management') from dual; -- 안됨.

select initcap('data')||initcap('base')||initcap('management')||initcap('system') from dual;

-- 모든 사원의 이름과 성을 합해서 이름, 성으로 출력 컬럼 명을 fullName으로 바꾸기
select (last_name || ',' || first_name) fullName from employees ;

-- 5) length() : 문자열 길이를 구하는 함수
select length('database') 길이 from dual;
select length('데이터베이스') 길이 from dual;

-- 이름이 6글자 이하인 사람들의 이름을 소문자로 출력
select lower(first_name) from employees where length(first_name) <= 6;

-- 6) substr() : 문자열의 일부를 추출하는 함수
-- substr(대상, 시작위치, 추출할 갯수)
select substr(initcap('database'), 1, 3) from dual; 
select upper(substr(initcap('database'), -4, 3)) from dual; -- (-)번째는 문자열 끝에서 부터

-- 입사년도가 2005년인 사원들의 모든 정보를 출력. substr()을 사용할것
select * from employees where substr(hire_Date, 0,2) = 05 order by hire_date;

-- 이름의 마지막 글자가 el로 끝나는 사원의 모든 정보를 출력
select * from employees where first_name like '%el';
select * from employees where substr(first_name, -2, 2) = 'el';

-- 7) instr() : 특정 문자의 위치를 구하는 함수
-- instr(대상문자열, 찾을 문자열)
 select instr('database', 'b') from dual; -- 1부터 시작함. 'b' 위치를 반환.
 select instr('database', 'a') from dual;
 select instr('database', 'a', 3) from dual; -- 3번째 이후 부터 찾아라. 
 
 -- 이름에 세번째 자리가 i인 사원의 모든 정보를 출력
 select * from employees where instr(first_name, 'i') = 3;
 select * from employees where substr(first_name, 3, 1) = 'i'; 
 select * from employees where first_name like '__i%';
 
 -- 8) trim() : 특정 문자를 잘라주는 함수
 select trim('a' from 'aaaaDataBaseaaa') from dual;
 
 select trim(' ' from '     DataBase   ') from dual;
 
 -- 8-1) trim([삭제 옵션] 삭제할 문자 from 대상문자열)
 -- 삭제 옵션 : leading(왼쪽 문자를 지움), trailing(오른쪽 문자를 지움), both(양쪽 문자를 지움)
 select '[' || trim(leading '_' from '__database__') || ']' "reading trim" from dual; -- [database__]
 select '[' || trim(trailing'_' from '__database__') || ']' "reading trim" from dual; -- [__database]
 select '[' || trim(both'_' from '__database__') || ']' "reading trim" from dual; -- [database]
 
 -- 8-2) ltrim(), rtrim()
 select '00011000' before, ltrim('00011000', '0') after from dual;
 select '00011000' before, rtrim('00011000', '0') after from dual;
 
 select 1111 from dual; -- number type일 경우 오른쪽으로 정렬됨
 select '1111' from dual; -- string type일 경우 왼쪽으로 정렬됨
 
 -- 9) replace(대상문자열, 찾는문자, [대체문자]) 
 select 01012345678 from dual; -- 숫자로 할 경우 앞의 0이 사라짐.
 select '010-1234-5678' before, replace('010-1234-5678', '-') after from dual; -- 만약 대체문자를 안줬을 경우 기본값은 ''이다.
 
 -- 10) lpad, rpad
 -- lpad(대상문자열, 데이터 자릿수, 채울 문자)
 select lpad('database', 10, '*') from dual; -- 왼쪽 빈공간 채움
 select rpad('database', 10, '*') from dual; -- 오른쪽 빈공간 채움
 
--------------------------------------------------
-- (2) 숫자 함수
--------------------------------------------------
-- 1) abs() : 절대값을 구해주는 함수
select abs(-15) from dual;

-- 2) floor() : 소수점 아래를 버리는 함수
select floor(3.141592) from dual;

-- 3) ceil() : 소수점 아래를 버리고 올림하는 함수
select ceil(3.141592) from dual; 

-- 4) round() : 특정 자릿수를 지정해서 반올림하는 함수
select round(3.141592, 2) from dual; 
select round(9.899846, 2) from dual; 
select round(5819.899846, -2) from dual; -- 표시할 자릿수가 음수가 되면 정수부분에서 반올림됨.
select round(5819.899846, 0) from dual; -- floor와 유사하지만, 반올림되어서 소수점을 자름.
select ceil(5819.899846) from dual;

-- 5) trunc() : 특정 자릿수에서 반올림 하지 않고 잘라내는 함수
select trunc(5819.899846, 2) from dual;
select trunc(5819.899846, 4) from dual;
select trunc(5819.899846, -2) from dual;

-- 6) mod() : 나머지 값을 반환하는 함수
-- mod(a, b) a를 b로 나눈 나머지값을 반환
select mod(34, 3) from dual;

-- 7) sign() : 양수, 음수, 0을 구분
select sign(34) from dual; -- 양수일 경우 1을 반환
select sign(-34) from dual; -- 음수일 경우 -1을 반환
select sign(0) from dual; -- 0일 경우 0을 반환

-- 8) power() : 거듭제곱 반환
-- power(a, b) a^b 를 반환
select power(2, 3) from dual;
select power(5, 5) from dual;

-- 9) sqrt() : 제곱근 반환
-- power(a, b) a^b 를 반환
select sqrt(9) from dual;
select sqrt(10) from dual;

-- 사원 연봉을 구하려 할 때 
-- 연봉 = (월급 * 12) + (커미션 + 월급 * 12) 
-- 위의 방식으로 연봉을 구한다고 할 때 연봉을 구해서 소수점이하 2자리 까지만 출력. 
-- 연봉 칼럼은 컬럼명은 annual salary 라고 출력
select round((salary*12) + (commission_pct + salary * 12), 2) "annual salary", first_name "name" from employees;
select employee_id, first_name, round((1 + commission_pct) * (salary * 12), 2) "annual salary" from employees;

--------------------------------------------------
-- (3) 날짜 함수
--------------------------------------------------
-- 1) 현재 날짜를 반환하는 sysdate
select sysdate from dual;
select sysdate+1 내일 from dual; -- 1일 후 날짜 출력

-- 사원들이 입사일로부터 현재까지 입사한지 며칠이 지났는지 구하기
select first_name, floor(sysdate - hire_date) || '일' "입사일로부터 며칠" from employees order by floor(sysdate - hire_date) desc ;

-- 2) months_between : 두 날짜 사이 간격을 개월수 단위로 계산해주는 함수
select first_name, hire_date, floor(months_between(sysdate, hire_date))||'개월' "근무개월" from employees;

-- 3) add_months : 개월수를 더하는 함수
select add_months(sysdate, 3) from dual;

-- 4) next_day : 해당 요일에 가장 가까운 날짜 반환
select next_day(sysdate, '월요일') from dual;

-- 5) last_day : 해당 달의 마지막 날짜를 반환
select sysdate, last_day(sysdate) from dual;

-- 6) round : 특정 기준으로 반올림 해주는 함수
select sysdate, round(sysdate, 'month') from dual;

-- 7) trunc: 특정 기준으로 버리는 함수
select sysdate, trunc(sysdate, 'month') from dual;

--------------------------------------------------
-- (4) 변환 함수
--------------------------------------------------
-- 1) to_char() : 문자열로 변환
-- to_char(날짜데이터, '출력형식') : 날짜를 문자열로 변환
select sysdate, to_char(sysdate, 'yyyy-mm-dd') from dual;

select sysdate, length(to_char(sysdate, 'yyyy-mm-dd')) from dual;

-- am, pm : 오전인지 오후인지 출력
select sysdate, to_char(sysdate, 'yyyy-mm-dd dy am hh12:mi:ss') from dual; -- 12시간제
select sysdate, to_char(sysdate, 'yyyy-mm-dd dy hh24:mi:ss') from dual; -- 24시간제

-- '9'는 자릿수를 나타내며 자릿수가 맞지 않으면 채우지 않음.
-- '0'은 자릿수를 나타내며 자릿수가 맞지 않아도 0으로 채움.
select to_char(123456, '9999999') from dual;
select to_char(123456, '0000000') from dual;

select first_name, salary, to_char(salary, 'L999,999') salary from employees; -- 현지 화폐단위로 변환

-- 2) to_date() : 숫자, 문자 데이터를 날짜 데이터로 변환
-- 입사일이 2006년 1월 3일인 사원 검색
select * from employees where hire_date = '06/01/03';

select * from employees where hire_date = to_date(20060103, 'yyyymmdd');
select * from employees where hire_date = to_date(20060103);

desc employees;

-- 3) to_number() : 숫자, 문자 데이터를 날짜 데이터로 변환
select '10000'+'20000' from dual; 
select '10.100'+'20.900' from dual; -- 자동 형 변환
select to_number('10,100', '99,999')+to_number('20,900', '99,999') from dual;

--------------------------------------------------
-- (5) 일반 함수
--------------------------------------------------
-- 1) nvl() ; 첫번째 인자로 받은 값이 null 이면 두번째 인자 값으로 변경
select employee_id, first_name, round((1 + nvl(commission_pct, 0)) * (salary * 12), 2) "annual salary" from employees;

-- 2) decode() : 프로그래밍 언어의 switch-case문과 같은 역할
-- decode(대상컬럼, [...{대상값, 출력값}] )
select * from departments;
select first_name, department_id, decode(department_id, 90, 'Executive') "부서이름" from employees;
select first_name, department_id, decode(department_id, 90, 'Executive', 60, 'IT', 100, 'Finance') "부서이름" from employees;

-- 3) case함수 : if -else if 와 비슷한 역할
select first_name, department_id, 
case when department_id = 90 then 'Executive'
     when department_id = 60 then 'IT'
     when department_id = 100 then 'Finance'
end
as 부서명
from employees;

select first_name, department_id, 
case when department_id = 90 then 'Executive'
     when department_id = 60 then 'IT'
     when department_id = 100 then 'Finance'
     else 'default' --위 조건식이 모두 false일 경우 
end
as 부서명
from employees;




-- 4) rank() : 공통순위는 건너뛰고 다음 순위로 출력
--    dense_rank() : 공통 순위를 건너뛰지 않고 다음 순위를 출력
--    row_number() : 공통 순위 없이 순위 출력

-- 금여별 랭킹 출력
select first_name, salary, rank() over(order by salary desc) rank, dense_rank() over(order by salary desc) dense_rank, row_number() over(order by salary desc) row_number from employees;

-- 2. 그룹함수
-- 1) sum() : 합계 구하는 함수. (단일행과 함께 쓸수 없음.)
select sum(salary) from employees;
select first_name, sum(salary) from employees; -- error

-- 2) avg() : 평균을 구하는 함수. 
select to_char(round(avg(salary),2), 'L999,999') from employees;

-- 3) max() : 최대값 구하는 함수
--    min() : 최소값 구하는 함수
select max(salary),min(salary) from employees;

-- 4) count() : 행의 갯수를 반환
select count(*) from employees; -- null을 제외한 row를 count
select count(commission_pct) from employees;

-- 5) stddev() : 표준편차를 구해주는 함수
select round(stddev(salary)) from employees;
-- 6) variance() : 분산
select round(variance(salary)) from employees;


-- group by : 
select department_id, sum(salary), avg(salary) from employees group by department_id;

-- 직무별 급여 총액과 평균 구하기
select job_id, sum(salary), avg(salary) from employees where department_id = 50 group by job_id order by avg(salary) desc;

-- having 절 : 그룹화를 시킨 컬럼에 조건을 부여할 때 사용하는 구문.
select department_id, avg(salary) from employees group by department_id having avg(salary) >= 5000; -- 정확한 구문 순서
select department_id, avg(salary) from employees having avg(salary) >= 5000 group by department_id; -- 잘못된 구문 순서

-- 직무별 급여 최댓값과 최솟값을 구하되 최대 급여가 7000 이상인 부서만 출력
select job_id, max(salary), min(salary) from employees group by job_id having max(salary) >= 7000 order by max(salary) desc;
select * from employees;

select job_id, department_id , sum(salary), avg(salary) from employees where department_id between 50 and 100 group by job_id, department_id order by job_id;
select job_id, department_id , sum(salary), avg(salary) from employees where department_id between 50 and 100 order by job_id group by job_id, department_id ; -- order by 는 항상 맨 끝에 입력해야 한다.

select job_id, sum(salary), avg(salary) from employees group by job_id having avg(salary) > 5000 order by avg(salary);



 