create table board(
    no number(6) primary key, 
    title varchar2(100), 
    writer varchar2(20)
);

insert into board values(1, '감기조심', 'dooly');
insert into board values(2, '독감조심', 'ddochi');
insert into board values(3, '몸살조심', 'huidong');

select * from board;

select max(no) from board;

drop table board;

-- 시퀀스 생성
-- create sequence 시퀀스명 
-- [start with n] 시퀀스의 시작값
-- [increment by n] -- 증가하거나 감소하는 값
-- [maxvlaue n | nomaxvalue] -- 최대값
-- [minvalue n | nominvalue] -- 최소값
-- [cycle | nocycle] -- cycle을 지정하면 최대값까지 증가가 완료된 후 다시 최소값부터 시작. nocycle로 지정하면 최대값까지 증가된 후 끝난다. 
-- [cache n | nocache] -- 오라클 서버가 미리 지정하고 메모리에 확보할 값

create sequence seq_board start with 1 increment by 1;

select * from user_sequences;

insert into board values(seq_board.nextval, 'title', 'dooly');
insert into board values(seq_board.nextval, '감기조심', 'dooly');
insert into board values(seq_board.nextval, '독감조심', 'ddochi');
insert into board values(seq_board.nextval, '건강관리', 'michol');
select  * from board;

select seq_board.currval from dual;
select seq_board.nextval from dual; -- 참조하는 것만으로도 시퀀스 값이 증가한다.
insert into board values(seq_board.nextval, '건강관리', 'dooly');

-- product 테이블 생성
create table product(
    serialNo varchar2(10) primary key,
    prodName varchar2(14)
);
-- 시퀀스 생성
create sequence seq_prod start with 1 increment by 1 maxvalue 5 cycle cache 2;

select * from user_sequences;

select to_char(sysdate, 'yymmdd') from dual;
insert into product values(to_char(sysdate, 'yymmdd') || '-' || seq_prod.nextval, '모니터' );
insert into product values(to_char(sysdate, 'yymmdd') || '-' || seq_prod.nextval, '모니터' );
select seq_prod.currval from dual;
select * from product;-- 5까지 자동 증가하다가 더이상 데이터가 추가되지 않는다. (unique제약조건 위반)

-- 시퀀스 수정
alter sequence seq_prod nocycle;

-- 시퀀스 삭제
drop sequence seq_prod;

-- 인덱스
select * from user_indexes;
select * from user_indexes where table_name = 'EMPLOYEES';

select * from employees where employee_id = 100;

create table members(
    memberId number primary key, 
    first_name varchar2(10) not null,
    last_name varchar2(10) not null,
    email varchar2(10),
    phone_number varchar2(20),
    regDate date
);

insert into members values(1, 'dooly', 'kim', 'dlk', '010-1234-5678', '23/10/01');
insert into members values(2, 'ddochi', 'kim', 'ddclk', '010-2345-6789', '23/10/31');
insert into members values(3, 'gildong', 'go', 'gogildong', '010-3456-7890', '23/10/31');
select * from user_indexes where table_name = "MEMBERS";

select * from members;

-- 인덱스 생성
create index regDate_idx on members(regdate);

select * from members where email="ddclk";

--다중속성 인덱스 생성
create index name_idx on members(first_name, last_name);

select * from user_indexes;


