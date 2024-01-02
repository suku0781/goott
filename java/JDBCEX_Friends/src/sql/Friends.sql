create table friends(
    friendNo number(4) primary key,
    friendName varchar2(15) not null,
    mobild varchar2(13) unique,
    addr varchar2(50)
);

insert into friends values(1, '노하영', '010-6279-9878', '서울 금천구');

<<<<<<< Updated upstream
select * from friends;
=======
commit;

-- 다음 친구 번호(friendNo)얻기
select count(*) from friends; -- 삭제시 문제발생
select max(friendNo) from friends;

commit;

desc friends;

truncate table friends;

SELECT * FROM ALL_CONSTRAINTS WHERE    TABLE_NAME = 'friends';
select *, all_constraints from friends;

select count(*) from friends; -- 1일 경우 중복됨.


delete from friends where  ;

select friendno from friends where friendname = '노하영';
>>>>>>> Stashed changes
