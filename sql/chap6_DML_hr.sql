-- DML : 테이블에 데이터를 추가, 수정, 삭제
-- CRUD : Create, Read, Update, Delete
create table emp01(
    empno number(4),
    ename varchar2(16),
    job varchar2(12),
    hiredate date,
    sal number(6)
);

truncate table emp01;

-- (1) insert into로 데이터를 추가
insert into emp01 values('1000', '고길동', '사장', sysdate, 100000); -- 자동 형변환
insert into emp01 values(1001, '도우너', '사원', sysdate, 50000);
insert into emp01 values(1002, '둘리', '사원', sysdate, 40000);

-- (2) update로 테이터 수정
-- update 테이블명 set 테이블 수정
-- update emp01 set 컬럼명1 = 값1, 컬럼명2 = 값2 ... where 조건식1

update emp01 set sal = 200000;
update emp01 set sal = 400000 where job = '사장';
update emp01 set sal = 300000, ename = '마이콜' where empno = 1001;

-- 문제 둘리사원의 월급을 마이콜사원의 월급과 같게 수정
--update emp01 set sal = (select sal from emp01 where empno = 1001) where empno = 1002;
select * from emp01;
update emp01 set sal = (select sal from emp01 where ename='마이콜' and rownum = 1) where ename='둘리';
delete from emp01 where rownum = 3 and empno = 1001;

-- 문제) 사번이 1002번인 사원 급여를 1001번 사원의 월 급여의 2배로 수정
update emp01 set sal = (select sal from emp01 where empno = 1001)*2 where empno = 1002;

-- 문제) 사번이 1003인 사원 또치가 오늘 입사했다. 월급은 마이콜의 1.5배로 책정되었다. 
insert into emp01 values(1003, '또치', '사원', sysdate, (select sal from emp01 where empno = 1001)*1.5 );

-- (3) delete로 데이터 삭제
-- delete from 테이블명[where 조건식]
delete from emp01 where empno = 1002;
delete from emp01; -- 모든 데이터가 지워짐.

rollback;



select * from emp01 ;
desc emp01;

--
--• DDL문 연습문제
--1. Scott계정의 emp테이블의 사번, 이름, 직급, 매니저 컬럼과 동일한 컬럼명과 유형을 갖는 테이블 을 emp01이라는 이름으로 생성하세요.
create table emp01 as (select empno, ename, job, mgr from emp where 1 = 0) ;
create table emp01 (
    tmpno number(4) not null,
    ename varchar2(10),
    job varchar2(9),
    mgr number(4)
);
desc emp01;



drop table emp01;

desc emp;

--2. 아래의 구조로 dept01 테이블을 생성하세요.
--・deptno 숫자 2자리, dname 가변길이문자 14자리, Ioc 가변길이문자 13자리
create table dept01 (
    deptno number(2),
    dname varchar2(14), 
    loc varchar2(13)
);
desc dept01;
--3. 이미 존재하는 emp01테이블에 날짜시간을 저장할 수 있는 타입으로 hiredate 컬럼을 추가하세 요.
alter table emp01 add (hiredate date);

select * from emp01;
desc emp01;
--4. emp01 테이블의 직급 컬럼의 크기를 20으로 변경하세요
alter table emp01 modify (job varchar2(20));

--5. emp01테이블의 매니저 컬럼을 삭제하세요.
alter table emp01 drop column mgr;

--6. emp01테이블의 이름을 emp02로 변경하세요 
rename emp01 to emp02;

-- 7. emp2 테이블과 dept01 테이블을 삭제하세요
drop table emp02;
drop table dept01;


select * from emp02;
desc emp01;
select * from dept01;




-- • DML문 연습문제
-- 1. emp01 테이블을 제거한 후 다음과 같은 구조로 emp01테이블을 생성하세요
-- empno number(4)
-- ename varchar2(10)
-- job varchar2(9)
-- mgr number(4)
-- hiredate date
-- sal number(7, 2)
-- comm number(7, 2)
-- deptno number(2)
create table emp01(
    empno number(4),
    ename varchar2(10),
    job varchar2(9),
    mgr number(4),
    hiredate date,
    sal number(7, 2),
    comm number(7, 2),
    deptno number(2)
);

--2. emp01테이블에 다음과 같은 데이터를 추가하세요
--• 7369 smith clerk 7839 80/12/17 800 null 20
--•7499 allen salseman 7369 87/12/20 1600 300 30
--•7839 king president null null 5000 null null
insert into emp01 values(7369, 'smith', 'clerk', 7839, '80/12/17', 800, null, null);
insert into emp01 values(7499, 'allen', 'salseman', 7369, '87/12/20', 1600, 300, 30);
insert into emp01 values(7839, 'king', 'pressident', null, null, 5000, null, null);

--3. emp01테이블의 모든 사원의 급여를 10% 인상하세요
select * from emp01;
update emp01 set sal = (select sal+sal/10 from emp01 );-- pass
update emp01 set sal = sal * 1.1;-- pass
--4. emp01 테이블에서 king의 입사일을 오늘 날짜로 수정하세요
update emp01 set hiredate = sysdate where ename = 'king';
-- (empno가 기본키람ㄴ primary key 라면 empno로 찾는것이 더 중요)
-- 5. emp01 테이블에서 1985년 이후에(포함한다) 입사한 모든 직원을 삭제하세요
delete from emp01 where hiredate > '1985/01/01';
select * from emp01;
--6. emp01테이블에서 커미션을 받지 못하는 모든 직원을 삭제하세요.
delete from emp01 where comm is null;

