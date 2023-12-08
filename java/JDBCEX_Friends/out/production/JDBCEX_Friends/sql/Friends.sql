select * from employees;
--select * from employees where employee_id = 100 or 1=1 --;
select * from employees where first_name = ?;
select * from employees where first_name like '%'||?||'%';
select * from departments;
select * from locations;
delete from departments where department_id = 285;  
commit;

create table friends(
    friendNo number(4) primary key,
    friendName varchar2(15) not null,
    mobild varchar2(13) unique,
    addr varchar2(50)
);

select * from friends;

insert into friends values(1, '노하영', '010-6279-9878', '서울 금천구');

commit;

