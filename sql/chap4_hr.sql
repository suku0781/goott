-- 조인
-- 사번이 100번인 사원 정보(사번, 이름, 부서번호)와 그가 소속된 부서의 부서명까지 출력
select employee_id, first_name, employees.department_id, departments.department_name from employees, departments; -- cartesian product(의미없음)
select (select count(*) from employees) + (select count(*) from departments)"총길이" from dual;

select employee_id, first_name, employees.department_id, departments.department_name from employees, departments where employees.department_id = departments.department_id;

select e.employee_id, e.first_name, e.department_id, d.department_name from employees e, departments d where e.department_id = d.department_id;
select e.employee_id, e.first_name, e.department_id, d.department_name from employees e, departments d, locations l where e.department_id = d.department_id;


-- 문제) CEO가 모든 직원에게 택배로 선물을 준다. 모든 직원들이 택배를 무사히 받을 수 있도록 사무실의 주소, 사원 정보를 출력하기

select * from locations where location_id = all(select location_id from departments where department_id in (select distinct department_id from employees where department_id is not null));

select department_id from employees;
select location_id from departments where department_id in (select department_id from employees);
select * from locations where location_id in (select location_id from departments where department_id in (select department_id from employees));
--답
select e.first_name || e.last_name, e.phone_number, l.location_id, l.city, l.postal_code, c.contry_name, r.region_name -- 나중에 다시 해보기
from employees e, departments d, locations l, countries c, regions r
where e.department_id = d.department_id and e.location_id = d.location_id and e.COUNTRY_ID = c.COUNTRY_ID and r.region_id = c.region_id;

--------------------------------------------------
-- 조인(join)
--------------------------------------------------
-- 1) 크로스 조인(cross join) : 의미없는 단순 조인. 단순하게 두개 이상의 테이블을 곱연산 결과 냄.
select e.employee_id, e.first_name, d.department_id, d.department_name from employees e, departments d;

-- 2) 동등 조인 (Equi-join) : 가장 많이 사용함. 조인 대상이 되는 테이블에서 공통적으로 존재하는 컬럼을 연결("="로 연결)해서 결과를 생성하는 조인.
-- where절에 조인 조건 사용. 
-- join조건은 테이블 개수에서 -1개가 있어야 한다. 
select e.first_name, d.department_name from employees e, departments d where e.department_id = d.department_id;

-- 3) 비동등 조인(Nun-equi join) : 조인할 테이블 사이의 컬럼값이 직접적으로 일치하지 않을 때 사용하는 조인
-- '='연산자를 제외한 연산자를 사용한다. 

-- scott계정에서 salgrade의 예시
-- 사원의 호봉이 얼마인지, 사번, 이름, 급여, 호봉(grade)를 출력
select e.sal, e.empno, e.ename, s.grade from emp e, salgrade s where e.sal >= s.losal and e.sal <= s.hisal;

-- hr계정에서 employees테이블의 사원이 salgrade테이블의 min_salary와 max_salary사이 salary 출력
select * from employees e, jobs j where e.salary between j.min_salary and j.max_salary;

--4) outer join
-- 행이 조인 조건에 만족하지 않을 경우 그 행은 조인 결과에 나타나지 않는다. 
-- 그러나 가끔 조인조건에 만족하지 않는 행들도 나타내기 위해 outer join을 사용해야할 경우가 있다. 
select e.first_name, d.department_name from employees e, departments d where e.department_id = d.department_id; -- 동등조인 (비교대상이 null인 경우 제외함) 아무 부서에도 배치되지 않은 Kimberly의 정보까지 출력
-- 아무 부서에도 배치되지 않은 부서까지 출력할 경우
select e.first_name, d.department_name from employees e, departments d where e.department_id(+) = d.department_id; -- (+ : outer join)을 입력 시 null을 포함시킴 
select e.first_name, d.department_name from employees e, departments d where e.department_id(+) = d.department_id(+); -- ORA-01468: a predicate may reference only one outer-joined table

-- self join : 하나의 테이블 내에서 조인을 해서 원하는 데이터를 얻는 것
-- from 절에 동일한 테이블 이름을 주고 조인 조건을 주어야 하는데 이때 서로 다른 테이블인것 처럼 하기위해서 반드시 테이블 별칭을 준다. 
select worker.first_name || '의 직속 상사는 ' || manager.first_name || '입니다.' from employees worker, employees manager where worker.employee_id = manager.employee_id;
select employee_id, first_name, manager_id from employees where first_name = 'Ellen';


-- 사원의 이름을 출력하고 사원의 이름의 직속상사는 매니저이름 입니다. 사원의 사원번호로 정렬하기
select worker.first_name || '사원의 직속상사는 ' || manager.first_name || '입니다.' from employees worker, employees manager where worker.manager_id = manager.employee_id order by worker.employee_id ;

--------------------------------------------------
-- ANSI join : ANSI (미국 표준 연구소)에서 제정한 다른 DBMS와의 호환성을 위해 만든 조인
--------------------------------------------------
-- 1) ANSI Cross Join
select count(*) from employees cross join departments;
-- ANSI inner join : equi join과 같음.
-- 조인 조건을 where 이 아닌 on 절에 준다.
select e.first_name, d.department_name from employees e inner join departments d on e.department_id = d.department_id;

-- 일반 조건은 where 절에 주면 된다. 일반 조건dmfh n으로 끝나는 사원 출력
select e.first_name, d.department_name from employees e inner join departments d on e.department_id = d.department_id where e.first_name like '%n';

-- using절을 이용해서 join조건 지정
-- 조인 되는 컬럼명이 동일해야 하고 using절에는 테이블 별칭을 사용할 수 없다.
select e.first_name, d.department_name from employees e inner join departments d using (department_id);
select e.first_name, d.department_name from employees e inner join departments d using (d.department_id) ;  -- ORA-01748: only simple column names allowed here

-- natural join : 조인이 되는 컬럼명은 반드시 동일해야 한다.
-- DBMS가 알아서 테이블을 살펴보고 동일한 컬럼으로 inner join 진행
select e.employee_id, e.first_name, d.department_name from employees e natural join departments d;

select d.department_name, l.city from departments d natural join locations l;

-- 3) ANSI outer join : 외부 조인 
-- left couter join | right outer join | full outer join
select e.first_name, d.department_name from employees e left outer join  departments d on e.department_id = d.department_id; -- 107개 (왼쪽 테이블에서 누락된 정보를 확인)

select e.first_name, d.department_name from employees e right outer join  departments d on e.department_id = d.department_id; -- 122개 (오른쪽 테이블에서 누락된 정보를 확인)

select e.first_name, d.department_name from employees e full outer join  departments d on e.department_id = d.department_id; -- 123개 (양쪽 테이블에서 누락된 정보를 확인)



-- 
--Chapter. 조인 연습문제 (scott)
--
--1. 급여가 3000에서 5000사이인 직원의 이름과 소속 부서명을 출력
select e.ename, d.deptno, d.dname from emp e, dept d  where e.sal >= 3000 and e.sal < 5000;
select e.ename, e.sal, d.dname from emp e, dept d where sal between 3000 and 5000 and e.deptno = d.deptno;
--2. 직급이 manager인 사원의 이름, 부서명을 출력하세요
select e.ename, d.dname from emp e, dept d where e.deptno = d.deptno and e.job = upper('manager');
--3. accounting 부서 소속 사원의 이름과 입사일 출력하세요
select e.ename, e.hiredate from emp e, dept d where e.deptno = d.deptno and dname = upper('accounting');
select e.ename, e.hiredate from emp e inner join dept d on e.deptno = d.deptno where lower(d.dname) = 'accounting';
--4. 커미션을 받는 사원의 이름과 그가 속한 부서명을 출력
select e.ename, d.dname from emp e, dept d where e.deptno = d.deptno and e.comm is not null;
select e.ename, d.dname from emp e inner join dept d using (deptno) where e.comm is not null;
--5. 뉴욕에서 근무하는 사원의 이름과 급여를 출력
select e.ename, e.sal from emp e, dept d where e.deptno = d.deptno and d.loc = upper('new york');
--6. 급여가 1200 이하인 사원 이름과 급여와 그가 근무하는 근무지를 출력하세요
select e.ename, d.loc, e.sal from emp e, dept d where e.deptno = d.deptno and e.sal <= 1200;
select e.ename, d.loc, e.sal from emp e inner join dept d on e.deptno = d.deptno where e.sal <= 1200;

--7. smith와 동일한 근무지에서 근무하는 사원의 이름을 출력하세요
select * from emp e, dept d where e.ename = upper('smith') and e.deptno = d.deptno;
select * from emp e, dept d where e.deptno = d.deptno;
select e.loc from emp e, dept d where e.deptno = d.deptno and e.ename = upper('smith');
select e.empno, e.ename from emp e, dept d where e.deptno = d.deptno and d.loc = (select loc from emp e, dept d where e.deptno = d.deptno and e.ename = upper('smith'));
select coworker.ename , coworker.deptno from emp worker inner join emp coworker on worker.deptno = coworker.deptno where worker.ename = upper('smith') and coworker.ename != upper('smith');
--8. 매니저가 king인 사원들의 이름과 직급을 출력하세요
select e from emp e where e.mgr = (select empno from emp where ename = upper('king'));
select worker.ename, worker.job from emp worker inner join emp manager on manager.empno = worker.mgr where manager.ename = upper('king');
--9. 급여가 2000 초과인 사원들의 부서번호, 부서이름, 사원번호, 사원이름, 월급을 출력하세요
select deptno, dname, empno, ename, e.sal  from emp inner join dept using (deptno) where sal > 2000;
--10. 각 부서별 평균 급여, 최대급여, 최소급여, 사원수를 출력하세요.
select count(*), deptno, floor(avg(sal)), max(sal), min(sal) from emp group by deptno;
--11. 모든 부서 정보와 사원 정보를 부서번호, 사원 이름순으로 정렬하여 출력하세요
-- 가능하면 * 는 날리지 말자.
select d.*, e.* from emp e inner join dept d on e.deptno = d.deptno order by d.deptno, e.ename;

select d.deptno, d.dname, d.loc, e.empno, e.ename, e.job, e.mgr, e.hiredate, e.sal, e.comm 
from emp e, dept d
where e.deptno = d.deptno
order by d.deptno, e.ename;

--12. 부서번호, 부서이름, 사원번호 사원이름, losal, hisal, grade, 매니저번호, 매니저이름을 출력하 세요.
select e.deptno, d.dname, e.empno, e.ename, s.losal, s.hisal, s.grade, m.empno, m.ename 
from emp e full join dept d on e.deptno = d.deptno full join emp m on e.mgr = m.empno inner join salgrade s on e.sal between s.losal and s.hisal;\

select * from emp;
select * from dept;
select * from salgrade;
select e.empno, e.ename, e.deptno, d.dname, s.losal, s.hisal, s.grade, e.mgr  from emp e inner join dept d on e.deptno = d.deptno inner join salgrade s on e.sal between s.losal and s.hisal ;


