create table friends(
    friendNo number(4) primary key,
    friendName varchar2(15) not null,
    mobild varchar2(13) unique,
    addr varchar2(50)
);

insert into friends values(1, '노하영', '010-6279-9878', '서울 금천구');

select * from friends;