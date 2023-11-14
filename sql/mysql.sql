create table board(
	boardNo varchar(10),
    title varchar(50)
);








select * from customer;
use nullish;
show tables;
insert into test values ('970411', '김수혁');
select * from customer;
select * from genre;
select * from rental;
select * from rental where rent_id = 11;
insert into rental values(null, 43, 85, 10752, '2023-11-13', 3, null);
update rental set return_exp='2023-11-15' where rent_id = 11;
insert into rental values(null, 22, 85, 10752, '2023-11-13', 3, null);
commit;
desc rental;
alter table customer modify cust_birth varchar(6);

commit;
create table status( 
	rent_id int(6) NOT NULL,
    rent_start date,
    rent_end date,
    late_fee int(10),
    constraint status_rent_id_pk primary key(rent_id) 
);

select * from sales;
select * from customer;
select * from rental;
select * from genre;
select * from genre where gen_id = 10752;
select * from rental r inner join genre g on r.gen_id = g.gen_id where rent_date between '2023-11-01' and '2023-11-13';
-- create view view_sales as 

select * from customer;
insert into customer value(null, '홍길동', '1997-04-11', '충청남도 서천군 시초면 시초동로 386-22', 01068457910, null, '2023-04-25' );
insert into customer value(null, '홍명기', '1997-05-06', '제주특별자치도 서귀포시 예래해안로 179-50(하예동)', '01084927950', null, '2023-08-05' );

insert into customer value(null, '안영실', '1984-08-04', '제주특별자치도 서귀포시 예래해안로 179-50(하예동)', '01069848974', null, '2023-11-01' );
insert into customer value(null, '윤민종', '1991-01-11', '서울특별시 강동구 구천면로33길 15(천호동)', '01091989987', 1, '2023-01-11' );
insert into customer value(null, '윤정배', '1985-08-03', '전라남도 여수시 안산2길 21-4(안산동)', '01041241630', null, '2023-05-21' );
insert into customer value(null, '김지훈', '1999-08-12', '부산광역시 부산진구 안창로60번길 6-19(범천동)', '01055120810', 2, '2023-04-19' );
insert into customer value(null, '정효진', '2001-11-12', '경상북도 봉화군 물야면 오록길 3', '01081255007', null, '2023-10-10' );
insert into customer value(63, '여다예', '2000-07-18', '부산광역시 중구 보수북길 8(보수동1가)', '01025810989', null, '2023-10-10' );
insert into customer value(64, '조본서', '1975-12-04', '광주광역시 서구 화정로260번길 9-3(화정동)', '01086941698', 1, '2023-08-29' );
insert into customer value(65, '안우진', '1999-12-25', '경기도 의정부시 호국로1346번길 32-8(의정부동)', '01055201548', null, '2023-05-27' );
select * from customer;
desc customer;
update customer set cust_name = '이상욱' where cust_id = 57;

select * from rental;
select * from video where gen_id = 99;
select * from genre;
insert into rental value(null, 65, 50, 80, '2023-11-13', '2023-11-20', null);
-- vid_id, gen_id, vid_tit, vid_grd, vid_com, vid_rel_date, vid_avg
-- rent_id, cust_id, vid_id, gen_id, rent_date, return_exp, return_date
insert into rental value(null, 64, 16, 878, '2023-10-20', '2023-11-27', null);
update rental set return_exp = "2023-10-27" where rent_id = 66;



select * from video;

desc video;

select * from board;
drop table board;

select * from video;
select * from genre;
select * from customer;
select * from rental;
select * from sales;

-- 홍길동 회원정보 확인
select * from customer where cust_name = '홍길동';

-- 회원 탈퇴 (회원을 신규로 생성 후 삭제 시키기)
insert into customer values(null, '테스트', '2000-01-01', '서울시 금천구 디지털로10길 69', '01056123451', 1, sysdate());
select cust_id from customer where cust_name = '테스트';
delete from customer where cust_id = 956378;
select * from customer;

-- 연체횟수에 따른 블랙리스트 조회 
select * from customer where cust_overdue > 0;

-- 비디오 추가
select * from video;
select * from genre;
select * from video where vid_tit = "포화속으로";
desc video;
insert into video values(null, 10752, "포화속으로", '12', '태원엔터테인먼트', '2010-06-16', 75, 2 );

-- 비디오 삭제 

select vid_id from video as v where vid_tit = "포화속으로";
delete from video where vid_id = 223; 
select * from video;





