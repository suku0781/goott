----------------------------------------------------------------
-- 제약조건 
----------------------------------------------------------------
-- (1) not null 조건 : null을 할당할 수 없음.
-- (2) unique 조건 : 같은 조건을 한번만 입력할 수 있음.
-- (3) primary key 조건 : null입력x, 같은 조건을 한번만 입력0
drop table emp01;

create table emp01(
    empno number(4) PRIMARY KEY,
    ename varchar2(10) not null
);

insert into emp01 values(null, '둘리'); -- ORA-01400: cannot insert NULL into ("HR"."EMP01"."EMPNO")
insert into emp01 values(1000, '둘리');
insert into emp01 values(1001, '도우너'); 
insert into emp01 values(1001, '희동'); -- ORA-00001: unique constraint (HR.SYS_C007038) violated

select * from emp01;
-- primary key : not null and unique. 이 컬럼을 통해서 검색, 수정, 삭제하는 것이 좋다. 

-- 급여 컬럼 추가
alter table emp01 add sal number(6);

commit; -- 여기까지 테이블 영구저장

-- 1000번 사원 급여 5000으로 변경
update emp01 set sal = 5000 where empno = 5000;

rollback; -- 최종 commit 한 시점으로 롤백
select * from emp01;

-- (4) Foreign key 
-- 1) 부서 번호가 부서테이블(부모테이블)에 존재하지 않는 값을 넣으려고 할 경우 
insert into employees values(207, '길동', '홍', 'gildong', null, sysdate, 'IT_PROG', 5000, null, 115, 280 ); -- ORA-02291: integrity constraint (HR.EMP_DEPT_FK) violated - parent key not found

-- 2) 사원이 있는 부서를 삭제하려 할 경우 
delete from departments where department_id = 60; -- ORA-02292: integrity constraint (HR.EMP_DEPT_FK) violated - child record found
select * from employees where department_id = 60;
commit;
update employees set department_id = 210 where department_id = 60;
select * from employees where department_id = 210;
delete from departments where department_id = 60;
rollback;

-- 부서 테이블과 사원 테이블을 만들어보기
create table dept01(
    deptno number(2) primary key, 
    dname varchar2(10) not null
); 
desc dept01;

create table emp01(
    empno number(4) primary key,
    ename varchar2(10) not null, 
    deptno number(2) not null references dept01(deptno) -- dept01테이블의 deptno컬럼을 참조한다.
);
desc emp01;

insert into dept01 values(10, '총무부');
insert into dept01 values(20, 'IT');

insert into emp01 values(1000, '둘리', 30); -- 없는 부서번호를 넣으면 ORA-02291: integrity constraint (HR.SYS_C007046) violated - parent key not found
insert into emp01 values(1000, '둘리', 10);
insert into emp01 values(1001, '또치', 20);

select * from dept01;
select * from emp01;

delete from dept01 where deptno = 20; -- ORA-02292: integrity constraint (HR.SYS_C007046) violated - child record found
update emp01 set deptno = 10 where deptno = 20; -- 20번 부서의 사원을 이동시킨 후 
delete from dept01 where deptno = 20; -- 20번 부서 제거

drop table emp01; -- 자식 날리고
drop table dept01; -- 부모 날리기

-- (5) check 제약조건
create table emp01(
    empno number(4) primary key, -- 컬럼 레벨 제약조건 
    ename varchar2(10) not null, 
    gender char(1) check (gender in ('M', 'F', 'U'))
);

desc emp01;

insert into emp01 values(1000, '홍길동', 'm'); -- gender가 소문자라서 ORA-02290: check constraint (HR.SYS_C007060) violated
insert into emp01 values(1000, '홍길동', 'M');
insert into emp01 values(1001, '홍길서', 'F');
insert into emp01 values(1002, '홍길갑', 'U');

select * from emp01;

drop table emp01;

create table member(
    userId varchar2(20) primary key,
    userPw varchar2(30) not null, 
    age number(3) check (age between 20 and 150)
);

insert into member values('test01', 'qwer123', 10); -- ORA-02290: check constraint (HR.SYS_C007063) violated
insert into member values('test01', 'qwer123', 23);
insert into member values('pub01', 'q!w@e#r$r123', 55);
insert into member values('end01', 'tst#r$r123', 150);
insert into member values('end02', '123123', 151); -- ORA-02290: check constraint (HR.SYS_C007063) violated

select * from member;

drop table member;

-- (6) default 제약조건 (엄밀히 보면 제약조건은 아님)
create table dept01(
    deptno number(3) primary key,
    dname varchar2(10), 
    loc varchar2(16) default 'seoul'
);

desc dept01;

insert into dept01(deptno, dname) values(10, '개발부');
insert into dept01 values(20, 'KBSI', 'daegu');
insert into dept01 values(30, 'KBSM', null);
insert into dept01 values(40, '새마을', ''); -- ''도 null로 들어간다.

select * from dept01;

drop table dept01;

-- 제약조건 이름 기술하기 
-- constraint [테이블명]_[컬럼명]_[제약조건명]
-- 생략 가능. 자동으로 oracle이 생성해줌. 그러나 constraint해서 커스텀 해줄 경우 오류시 보기 편함.
create table dept01(
    deptno number(2) constraint dept01_deptnl_pk primary key,
    dname varchar2(10) constraint dept01_dname_nn not null,
    loc varchar2(16) default 'seoul'
);

create table emp01(
    empno number(4) constraint emp01_empno_pk primary key,
    ename varchar2(10) constraint emp01_ename_nn not null,
    email varchar2(20) constraint emp01_email_uk unique
);
alter table emp01 add gender varchar2(5) constraint emp01_gender_ck check(gender in ('M', 'F'));
alter table emp01 add deptno number(2) constraint emp01_deptno_fk references dept01(deptno);

select * from dept01;
select * from emp01;

insert into dept01 values (10, '총무부', 'dooly');
insert into emp01 values (1000, 'dooly', 'dooly', 'M', 10);
insert into emp01 values (1001, 'ddochi', 'ddochi', 'M', 20); -- ORA-02291: integrity constraint (HR.EMP01_DEPTNO_FK) violated - parent key not found

select * from user_constraints;

-- 지금까지 컬럼 레벨 제약조건이었음. 
-- 이제 테이블 레벨 제약조건.

drop table emp01;
drop table dept01;

create table dept01(
    deptno number(4), 
    dname varchar2(20),
    loc varchar2(10),
    
    constraint dept01_deptno_pk primary key (deptno),
    constraint dept01_dname_uk unique (dname)
);

desc dept01;

create table emp01(
    empno number(4) constraint emp01_cmpno_ck check(empno >= 100),
    ename varchar2(10) constraint emp01_ename_nn not null, -- not null은 컬럼레벨에서 기술.
    salary number(5),
    deptno number(4),
    
    constraint emp01_empno_pk primary key (empno),
    constraint emp01_salary_ck check(salary > 0),
    constraint emp01_deptno_fk foreign key(deptno) references dept01(deptno)
    
);

desc emp01;

insert into dept01 values(10, '총무부', '부산');
insert into dept01 values(20, '개발부', '서울');

insert into emp01 values(90, '고길동', 10000, 10); -- ORA-02290: check constraint (HR.EMP01_CMPNO_CK) violated
insert into emp01 values(900, '둘리', 5000, 20);
insert into emp01 values(102, '또치', 7000, 10);
--alter table emp01 modify (empno = 100) where empno = 900;
update emp01 set empno = 101 where empno = 900;

select * from emp01;
select * from dept01;

-- 1) 복합키
create table member(
    email varchar2(40),
    password varchar2(50) constraint member_password_nn not null,
    memname varchar2(50) constraint member_memname_nn not null,
    mobile char(13) constraint member_mobile_nn not null,
    addr varchar2(50),
    
    constraint member_combo_pk primary key(email, mobile) -- 복합키는 테이블레벨 단위로 기술해야함.
);

-- 회원가입
insert into member values('a@abc.com', 'qwer123','홍길동', '010-1234-5678', null);
insert into member values('a@abc.com', 'qwer쇼123','홍길갑', '010-1234-5678', null); -- 복합키 2개가 일치 ORA-00001: unique constraint (HR.MEMBER_COMBO_PK) violated
insert into member values('b@abc.com', 'qwertyu123','홍길갑', '010-1234-5678', null); -- 복합키 2개 중 1개 일치할 경우 insert 됨.
insert into member values('b@abc.com', 'qwertyu123','홍길을', '010-7894-5678', null); -- 복합키 2개 중 1개 일치할 경우 insert 됨.

select * from member;

select count(*) password from member where email = 'a@abc.com' and password = 'qwer123';

-- 2) alter table조 제약조건 추가
-- alter table 테이블명 add [ constraint 제약조건명 ] 제약조건 (컬럼명);

alter table member add gender char(3);

alter table member add constraint member_gender_ck check(gender in ("남","여"));

update member set addr = '서울 구로';--null 제거

alter table member modify addr constraint member_addr_nn not null;

-- 제약조건 제거
-- alter table 테이블명 drop constraint 제약조건명;
alter table member drop constraint member_addr_nn;

-- 제약조건 일시 비활성화
-- alter table 테이블명 disable constraint 제약조건명
alter table member disable constraint member_memname_nn;

insert into member values('a@abc.com','qwerty123',null,'010-1234-5012', '서울 금천','남');
desc member;

alter table member enable constraint member_memname_nn; -- ORA-02293: cannot validate (HR.MEMBER_MEMNAME_NN) - check constraint violated
update member set memname='홍길병' where memname is null; -- memname이 null인 멤버 이름을 홀길정으로 변경
alter table member enable constraint member_memname_nn; -- 정상작동
select  * from member;

-- 제약조건을 비활성화 하면 제약조건을 위반한 컬럼의 값도 insert가 될 수 있다. (disable되어있는 동안)
-- 제약조건을 확성화 하면 제약조건을 위반한 컬럼 값 때문에 활성화 되지 않는다. 
-- 이때는 컬럼의 값을 제약조건을 지키도록 수정한 뒤 활성화 해야한다. 

drop table member;

create table member(
    userId varchar2(12),
    pwd varchar2(20) constraint member_pwd_nn not null,
    constraint member_userId_pk primary key (userId)
);

insert into member values('gildong', '1234');
insert into member values('dooly', '1234');

select * from member;

create table board(
    boardNo number(10),
    writer varchar2(12),
    title varchar2(40),
    constraint board_boardNo_pk primary key(boardNo),
    constraint board_writer_fk foreign key(writer) references member(userId) on delete cascade -- 자식까지 다 지워짐. 
);

select * from board;

insert into board values(1, 'dooly', '아싸 1등!');
insert into board values(2, 'gildong', '2등이라니!');
insert into board values(3, 'dooly', '아배고파 !');

select * from board order by boardNo desc;
delete from member where userId = 'dooly';
select * from member;
select * from board;

drop table board;
drop table member;

create table member(
    userId varchar2(16),
    pwd varchar2(20) constraint member_pwd_nn not null,    
    constraint member_userId_pk primary key(userId)
);

create table board(
    boardNo number(6),
    boardTitle varchar2(50) constraint board_boardTitle_nn not null,
    writer varchar2(16),
    
    constraint board_boardNo_pk primary key(boardNo),
    constraint board_boardNo_fk foreign key(writer) references member(userId) on delete set null
);

desc member;
desc board;

--alter table board modify(board(writer))

insert into member values('gildong','qwer1234');
insert into member values('dooly','qwer1234');

-- 미완성(복습대상)

drop table board;
drop table member;

-- 문제
-- 1. 회원정보를 저장하는 테이블 member라는 이름으로 아래와 같이 작성
create table member(
    id varchar2(20), -- pk
    password varchar2(40), -- null허용 
    regno varchar2(13) constraint member_regno_nn not null, -- unique not null, 후보키
    mobile varchar2(13),
    address varchar2(100),
    constraint member_id_pk primary key(id),
    constraint member_regno_uk unique(regno),
    constraint member_mobile_uk unique(mobile)
);
desc member;
-- 2. 도서정보를 저장하는 테이블 book을 아래와 같이 작성
create table book(
    code number(4), --pk
    title varchar2(50) constraint book_title_nn not null,
    count number(6),
    price number(10),
    publish varchar2(50),
    constraint book_code_pk primary key(code)
);
-- 3. 회원이 책을 주문했을 때 이에 대한 정보를 저장하는 테이블 order 를 아래와 같이 작성.
create table order1(
    no varchar2(10), --pk
    id varchar2(20) constraint order1_id_nn not null,
    code number(4) constraint order1_code_nn not null,
    count number(6),
    orderData date,
    constraint order1_no_pk primary key(no),
    constraint order1_id_fk foreign key(id) references member(id),
    constraint order1_code_fk foreign key(code) references book(code)
);

desc member;
desc book;
desc order;

select * from emp_details_view;
select * from emp_details_view where department_id = 90 order by salary desc;

select view_name, text from user_views;

SELECT
  e.employee_id,
  e.job_id,
  e.manager_id,
  e.department_id,
  d.location_id,
  l.country_id,
  e.first_name,
  e.last_name,
  e.salary,
  e.commission_pct,
  d.department_name,
  j.job_title,
  l.city,
  l.state_province,
  c.country_name,
  r.region_name
FROM
  employees e,
  departments d,
  jobs j,
  locations l,
  countries c,
  regions r
WHERE e.department_id = d.department_id
  AND d.location_id = l.location_id
  AND l.country_id = c.country_id
  AND c.region_id = r.region_id
  AND j.job_id = e.job_id;
--WITH READ ONLY

-- 뷰 생성
-- create [ or replace ] [ force | noforce ] view 뷰이름 as (서브쿼리) [with check option] [with read only] ([] 생략 가능) ;
-- create [ or replace ] : 뷰가 없으면 create 하고 있으면 replace한다. 
create or replace view view_emp30 as select * from employees where department_id = 30;

select * from view_emp30;
select * from employees where employee_id = 100;
insert into view_emp30 values (301, 'gildong', 'koh', 'gogildong', '515,123,4567', '03/06/17', 'AD_PRES', 24000, null, null, 30);
delete from view_emp30 where employee_id = 301;

-- check option으로 뷰 생성
-- 뷰를 볼수있는 컬럼에 대해서만 insert/update작업이 수행된다.

select * from employees;
create or replace view view_emp90 as select employee_id, first_name, last_name, email, job_id, hire_date, department_id from employees where department_id = 90 with check option;
insert into view_emp90 values(302, '둘리', '김', 'doolyKim', 'IT_PROG', sysdate, 90);
update view_emp90 set salary = 9000 where employee_id = 302;
delete from view_emp90 where employee_id = 302;
select * from view_emp90;
update view_emp90 set hire_date = '23/11/02' where employee_id = 302;

--with read only 옵션으로 뷰 생성.
drop view view_emp90;
create or replace view view_emp90 as select employee_id, first_name, last_name, email, job_id, hire_date, department_id from employees where department_id = 90 with read only;
select * from view_emp90;
delete from view_emp90 where employee_id = 302; -- SQL 오류: ORA-42399: cannot perform a DML operation on a read-only view
insert into view_emp90 values(303, '또치', '김', 'ddochiKim', 'IT_PROG', sysdate, 90); -- SQL 오류: ORA-42399: cannot perform a DML operation on a read-only view
update view_emp90 set hire_date = sysdate where employee_id = 303; -- SQL 오류: ORA-42399: cannot perform a DML operation on a read-only view

select * from tab;
drop table order1;
drop table member;
select * from member;
create or replace view view_member as select * from member; -- ORA-00942: table or view does not exist
create or replace force view view_member as select * from member; -- (일단 강제적으로 만듦.) 컴파일 오류와 함께 뷰가 생성되었습니다.
create table member(
    userId varchar2(10)
);
select * from view_member;
select * from employees;

-- 뷰를 생성하는데 컬럼에 별칭을 생성하여 만들기
-- 해당 뷰에 대해서는 컬럼 별칭으로 조건절을 만들어야 함.
create or replace view view_emp(사번, 이름, 급여, 부서번호) as select employee_id, first_name, salary, department_id from employees;

select * from view_emp;
select * from view_emp where department_id = 90; -- ORA-00904: "DEPARTMENT_ID": invalid identifier
select * from view_emp where 부서번호 = 90; 

-- 그룹함수로 뷰를 생성하면 반드시 별칭을 지정해줘야 한다. 그룹함수로 생성된 뷰에는 DML문 사용이 불가하다.
create or replace view view_sal as select department_id, sum(salary), avg(salary) from employees group by department_id; -- ORA-00998: must name this expression with a column alias
create or replace view view_sal as select department_id, sum(salary) 급여총액, avg(salary) 급여평균 from employees group by department_id;
select * from view_sal;
delete from view_sal where department_id = 30; -- SQL 오류: ORA-01732: data manipulation operation not legal on this view

-- 조인으로 뷰 생성
-- view_emp_dept -> 사번, 이름, 부서번호, 부서이름 으로 select 하고 조인해서 뷰 생성.
select e.employee_id, e.first_name, d.department_id, d.department_name from employees e inner join departments d on e.department_id = d.department_id;
create or replace view view_emp_dept as select e.employee_id, e.first_name, d.department_id, d.department_name from employees e inner join departments d on e.department_id = d.department_id;
insert into view_emp_dept values(303, '또치', 90, 'Excutive'); -- ORA-01776: cannot modify more than one base table through a join view
update view_emp_dept set first_name = '또치' where department_id = 302;
delete from view_emp_dept where employee_id = 302;
-- DML 중 insert 만 안됨.
select * from view_emp_dept order by employee_id desc;



-- 급여가 높은 순으로 5명만 출력
--select employee_id, first_name, salary from employees order by salary desc;

create table testrn(
    userId number(3) primary key, 
    userName varchar2(15)
);

insert into testrn values(1, 'user1');
insert into testrn values(2, 'user2');
insert into testrn values(3, 'user3');
insert into testrn values(4, 'user4');
insert into testrn values(5, 'user5');
insert into testrn values(10, 'user10');
insert into testrn values(9, 'user9');
insert into testrn values(8, 'user8');
insert into testrn values(7, 'user7');
insert into testrn values(6, 'user6');

select rownum, * from testrn; -- ORA-00936: missing expression
select rownum, testrn.* from testrn where rownum between 4 and 8; -- 안나옴.
select * from (select rownum, testrn.* from testrn) where rownum between 5 and 10; -- 안나옴.
select * from (select rownum rn, testrn.*  from testrn) where rn between 5 and 10;

select rownum, employee_id, first_name, salary from employees order by salary desc;

-- 뷰 생성 
create or replace view view_sal_top5 as select employee_id, first_name, salary from employees order by salary desc;

select * from view_sal_top5;
select rownum, e.* from view_sal_top5 e where rownum <= 5;

-- 서브쿼리로 풀 수 있다. 
select rownum, e.* from (select employee_id, first_name, salary from employees) e where rownum < 6;

-- 입사일이 늦은 7명을 출력하기
select * from employees order by hire_date desc;
create or replace view view_hire_date_late7 as select employee_id, first_name, hire_date from employees order by hire_date desc;

select * from view_hire_date_late7;

select rownum, h.* from view_hire_date_late7 h where rownum <= 7;
select rownum, e.* from (select * from view_hire_date_late7) e where rownum <= 7;

-- 입사일이 늦은 그 다음 7명을 출력하기
select * from employees order by hire_date ;
create or replace view view_hiredate_late as select employee_id, first_name, hire_date from employees order by hire_date;
select * from (select rownum rn, hl.* from view_hiredate_late hl) where rn between 8 and 14;

select * from (select rownum rn, e.* from (select employee_id, first_name, hire_date from employees order by hire_date desc) e) where rn between 8 and 14;



