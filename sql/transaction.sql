-- DDL은 실행 시 바로 commit 된다. create alter truncated drop rename
-- DML은 실행 후 commit 되지 않는다. 따로 commit 해줘야한다. -- select insert update delete
-- transaction 작업단위 : all or nothing



INSERT INTO GOOTT values(40, 'shk', 'sql너무너무어렵다.');
commit; -- git에서 push와 같은 개념
update goott set content = '롤백은안되네' where userId = 110;

select * from goott;

rollbock;
TRUNCATE table goott;

delete from goott where userId = 40;

insert into goott values(120, 'web', 'new Writed');

savepoint after_insert; -- git에서 commit과 같은 개념

update goott set content = 'db열심히하자' where userId = 40;

rollback;


-- 문제) 다음의 쿼리문이 순서대로 실행되었다면 DB에 영구 밪ㄴ영되는 문장은 몇번인지?
--insert
--savepoint a;
--delete
--savepoint b;
--update
--rollback to a;
--insert
--savepoint c;
--delete
--commit;

create table testTable(
    userId varchar2(10)
);
insert into testTable values('0000');
insert into testTable values('0001');
savePoint a;
delete from testTable where userId = '0001';
savePoint b;
update testTable set userId = '1000' where userid='0000';
rollback a;
insert into testTable values('0002');
savepoint c;
delete from testTable;
commit;

select * from testTable;
drop table testTable;
