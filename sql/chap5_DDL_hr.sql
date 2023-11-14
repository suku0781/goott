-- 서브쿼리를 이용하여 데이터 복사 
create table copyEMP as (select * from employees); -- 제약조건은 따로 줘야한다. 모두 다 복사되지 않는다. 

select * from copyEMP;

create table copyEMP1 as select employee_id, first_name, salary, hire_date, department_id from employees where department_id = 30;


insert into copyEMP1 values(300, '감삼백', 3000, '2001/01/01', 30);
insert into copyEMP1 values(301, '감삼백일', 3100, '2001-01-01', 30);
insert into copyEMP1 values(302, '감삼백이', 3200, to_date('01-01-2002', 'dd-mm-yyyy'), 30);
insert into copyemp1 values(303, '김삼백삼', 3300, sysdate, 30);

-- 데이터 구조만 복사 (where 조건절에 맞는 행이 하나도 없으므로 데이터는 복사가 안됨.)
create table copyEMP3 as select * from employees where 1 = 0;

select * from copyEMP3;

-- 서브쿼리를 사용해서 한번에 여러 데이터 추가
-- values절은 사용하지 않는다.
-- 컬럼갯수와 자료형이 일치해야한다.
insert into copyEMP3(employee_id, first_name, last_name, email, job_id, hire_date, salary, department_id)
select employee_id, first_name, last_name, email, job_id, hire_date, salary, department_id from employees;

select * from copyemp3;

drop table copyEMP3;

-- (1) create table 로 테이블 구조 정의
--CREATE TABLE 테이블명(
--	컬럼명1, 데이터타입 표현식, 
--	컬럼명2, 데이터타입 표현식, 
--	컬럼명3, 데이터타입 표현식, 
--
--	...
--
--	컬럼명n, 데이터타입 표현식, 
--)

create table Member01(
    userId varchar2(12),
    pwd varchar2(16),
    name varchar2(10),
    tel varchar2(13),
    birthday date,
    gender char(1)
);



-- insert into 로 데이터 저장
-- insert into 테이블명 [(컬럼명1, 컬럼명2 ...)] values(값1, 값2, ...)
-- 컬럼명 생략 가능. 컬럼명을 생략할 경우 모든 컬럼에 저장하겠다는 의미로 순서대로 넣어줘야함.
insert into member01 values('dooly', '1234', '둘리','010-1234-5555', '1998-01-01','M');
insert into member01 values('dooly', '1234', '둘리','010-1234-5555', '1998-01-01','M', '실수로넣은데이터'); -- SQL 오류: ORA-00913: too many values
insert into member01 values('doner', '1234', '도우너', '010-1234-6666', '1999-01-01'); -- 성별 입력x SQL 오류: ORA-00947: not enough values
insert into member01 values('doner', '1234', '도우너', '010-1234-6666', '1999-01-01', 'M');
insert into member01 values('huidong', '123123', '희동이', '010-1231-3125', '2010-01-01', 'M');
insert into member01 values('michol', '12357', '마이콜콜콜콜콜콜콜콜', '010-1235-2913', '1997-01-01', 'M'); -- 이름이 너무 길 경우 ORA-12899: value too large for column "HR"."MEMBER01"."NAME" (actual: 30, maximum: 10)
insert into member01 values('michol', '12357', '마이콜', '010-1235-2913', '1997-01-01', 'M'); 

insert into member01(userId, pwd, name) values('gildong', '1234', '고길동'); -- 컬럼을 넣어주지 않으면 null 이 된다.
insert into member01(userId, name, pwd) values('ddochi','또치','1234'); -- 묵시적 null 추가
insert into member01(userId, name, pwd) values('ddochi','또치',null); -- 명시적 null 추가

-- (2) alter table 구문으로 테이블 구조 수정
-- 1) add column : 새로운 컬럼 추가
alter table member01 add (job varchar2(10));
alter table member01 add (hobby varchar2(50));

-- 2) modify : 기존 컬럼 수정
-- 해당 컬럼에 데이터가 없으면 데이터타입, 사이즈 모두 변경 가능.
-- 해당 컬럼에 데이터가 있다면 데이터타입 변경 불가, 사이즈 변경 늘리기는 가능. 줄이기 불가.
alter table member01 modify (name varchar2(30));
alter table member01 modify (tel varchar2(10)); -- 기존 테이터가 있으니 사이즈를 줄일 수 없음. ORA-01441: cannot decrease column length because some value is too big
alter table member01 modify (tel varchar2(15)); -- 칼럼 사이즈 늘리기 가능
alter table member01 modify (birthday varchar2(15)); -- 데이터타입변경은 데이터가 없을 경우에만 가능하다. ORA-01439: column to be modified must be empty to change datatype

-- 3) drop column : 기존 컬럼 삭제
alter table member01 drop column birthday;
alter table member01 drop column hobby; -- 데이터가 있든 없든 날라감. 

-- 4) truncate table롤 테이블의 데이터 삭제하기
truncate table member01; -- 테이블의 데이터를 삭제
-- ddl은 복구가 안됨 dml 은 복구가 될 수 있음.

-- 5) renameto 테이블 이름 변경하기
rename member01 to members;

-- 6) rename column 으로 컬럼 이름 변경
alter table members rename column userId to memberId;

select * from Members;
desc members;

