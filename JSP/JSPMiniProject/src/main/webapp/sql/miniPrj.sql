-- member 테이블 생성
CREATE TABLE `shk`.`member` (
  `userId` VARCHAR(8) NOT NULL,
  `userPw` VARCHAR(500) NOT NULL,
  `userEmail` VARCHAR(50) NULL DEFAULT 'null',
  `registDate` DATETIME NOT NULL DEFAULT now(),
  `userImg` VARCHAR(50) NULL DEFAULT 'null',
  `userPoint` INT(11) NULL DEFAULT '0',
  PRIMARY KEY (`userId`),
  UNIQUE INDEX `userEmail_UNIQUE` (`userEmail` ASC) VISIBLE);
  
  -- uploadedfile 테이블 생성
  CREATE TABLE `uploadedfile` (
  `no` int(11) NOT NULL,
  `originalFileName` varchar(50) DEFAULT NULL,
  `ext` varchar(5) DEFAULT NULL,
  `newFileName` varchar(50) DEFAULT NULL,
  `size` int(11) DEFAULT NULL,
  PRIMARY KEY (`no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_cimember;

-- pointpolicy 테이블 생성
CREATE TABLE `pointpolicy` (
  `why` varchar(50) NOT NULL,
  `howMuch` int(11) DEFAULT NULL,
  PRIMARY KEY (`why`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `shk`.`pointpolicy` (`why`, `howMuch`) VALUES ('회원가입', '100');
INSERT INTO `shk`.`pointpolicy` (`why`, `howMuch`) VALUES ('로그인', '5');
INSERT INTO `shk`.`pointpolicy` (`why`, `howMuch`) VALUES ('게시물작성', '2');
INSERT INTO `shk`.`pointpolicy` (`why`, `howMuch`) VALUES ('답글작성', '1');

-- pointLog 테이블 생성
CREATE TABLE `shk`.`pointlog` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `when` DATETIME NOT NULL DEFAULT now(),
  `why` VARCHAR(50) NULL,
  `howMuch` INT(11) NULL,
  `who` VARCHAR(8) NULL,
  PRIMARY KEY (`id`),
  INDEX `pointLog.why.fk_idx` (`why` ASC) VISIBLE,
  INDEX `pointLog.who.fk_idx` (`who` ASC) VISIBLE,
  CONSTRAINT `pointLog.why.fk`
    FOREIGN KEY (`why`)
    REFERENCES `shk`.`pointpolicy` (`why`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `pointLog.who.fk`
    FOREIGN KEY (`who`)
    REFERENCES `shk`.`member` (`userId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION);
    

insert into uploadedFile(originalFileName, ext, newFileName, size) values(?, ?, ?, ?);

select no from uploadedFile where newFileName = ?;
select * from uploadedFile;

insert into member(userId, userPw, userEmail, userImg, userPoint) values(?, sha1(md5(?)), ?, ?, ?);

select sha1('shk');

insert into pointLog(why, howMuch, who) values(?, ?, ?);

select * from uploadedFile;
insert into member(userId, userPw, userEmail, userImg, userPoint) values("suku0781", sha1(md5("jkl123")), "suku0781@naver.com", 1, 1);
select * from member where userId = 'asdfas';
select * from uploadedfile;
select * from pointLog;
select * from pointPolicy;
select * from member where userEmail = ?;

truncate table member;
truncate table uploadedfile;
truncate table pointlog;
truncate table member;



select * from member;
select * from uploadedfile;
select * from pointLog ;

select m.*, u.newFileName from member m inner join uploadedfile u on m.userImg = u.no where userId = test2 and userPw = sha1(md5('jkl123'));

select * from member m inner join uploadedfile u on m.userImg = u.no where userId = 'test' and userPw = sha1(md5('jkl123'));

-- 회원정보 + memberImg


-- 로그인 성공 시 member테이블 포인트를 update
select * from pointLog ;
update member set userPoint = userPoint + 1 where userId = 'test';

-- 해당 아이디 회원의 정보 
select m.*, u.newFileName from member m inner join uploadedfile u on m.userImg = u.no where userId = 'jkl123';

-- 해당 회원의 포인트 내역
select * from pointlog where who = 'jkl123';

-- 게시판 테이블생성.
CREATE TABLE `shk`.`board` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `writter` VARCHAR(8) NULL,
  `title` VARCHAR(100) NOT NULL,
  `postDate` DATETIME NULL DEFAULT now(),
  `content` VARCHAR(1000) NOT NULL,
  `readCount` INT NULL DEFAULT 0,
  `likeCount` INT NULL DEFAULT 0,
  `ref` INT NULL DEFAULT NULL,
  `step` INT NULL DEFAULT 0,
  `refOrder` INT NULL DEFAULT 0,
  `isDelete` VARCHAR(1) NULL DEFAULT 'n',
  PRIMARY KEY (`no`),
  INDEX `board_writer_fk_idx` (`writter` ASC) VISIBLE,
  CONSTRAINT `board_writer_fk`
    FOREIGN KEY (`writter`)
    REFERENCES `shk`.`member` (`userId`)
    ON DELETE SET NULL
    ON UPDATE NO ACTION);

-- updateFile테이블 수정
ALTER TABLE `shk`.`uploadedfile` 
ADD COLUMN `boardNo` INT(11) NULL AFTER `size`,
ADD COLUMN `base64String` LONGTEXT NULL AFTER `boardNo`;

ALTER TABLE `shk`.`uploadedfile` 
ADD INDEX `uploadedFile_boardNo_fk_idx` (`boardNo` ASC) VISIBLE;

-- uploadedFile의 boardNo 외래키 수정
ALTER TABLE `shk`.`uploadedfile` 
ADD CONSTRAINT `uploadedFile_boardNo_fk`
  FOREIGN KEY (`boardNo`)
  REFERENCES `shk`.`board` (`no`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

select * from board;
select * from uploadedfile;


-- 게시판 전체 글 목록
select * from board order by no desc;
select * from board;
select * from member;
select * from uploadedFile;
select max(no)+1 from board;

select max(b.no)+1 from board b;

-- 게시판 글 저장
insert into board(writter, title, content, ref) values('test', '너무어렵다.', '자바로 게시판crud 테스트하는거 너무 어려워요.', (select max(b.no)+1 from board b));
select * from board;
alter table board auto_increment = 50;
select * from board;
select LAST_INSERT_ID();
select * from board where no =54;
select * from board b inner join uploadedFile u on b.no = u.boardNo where b.no = ?;

-- 게시글 상세 조회
-- 조회수 처리 테이블 생성
CREATE TABLE `shk`.`readcountprocess` (
  `no` INT NOT NULL AUTO_INCREMENT,
  `ipAddr` VARCHAR(50) NOT NULL,
  `boardNo` INT NULL,
  `readTime` DATETIME NULL DEFAULT now(),
  PRIMARY KEY (`no`),
  INDEX `rcp_boardNo_fk_idx` (`boardNo` ASC) VISIBLE,
  CONSTRAINT `rcp_boardNo_fk`
    FOREIGN KEY (`boardNo`)
    REFERENCES `shk`.`board` (`no`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);

-- readcountprocess 테이블에 해당 아이피 주소와 글 번호가 같은게 있는지 없는지
select * from readcountprocess where ipAddr = ? and boardNo = ?;
-- 24시간이 지났는지 확인
SELECT TIMEDIFF( (select * from readcountprocess where ipAddr = ? and boardNo = ?) , now()) AS time_diff;
SELECT TIMESTAMPDIFF(hour, (select readTime from readcountprocess where ipAddr = ? and boardNo = ?) , now()) AS hourDiff;
select readTime from readcountprocess where ipAddr = ? and boardNo = ?;
select * from board;
-- 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 insert하는 메서드
insert into readCountProcess(ipAddr, boardNo) values(?, ?);

-- 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 update하는 메서드
update readCountProcess set readtime = now() where ipAddr = ? and boardNo = ?;

-- no번 글에 조회수를 증가하는 쿼리문
-- 과제
update board set readCount = readCount+1 where no = ?;
select * from board;

-- no번 글 데이터 가져오기
select * from board where no = ?;

select * from readcountprocess;

select * from uploadedfile;

-- 게시글 삭제하기 
update board set isDelete = 'n' where no = 58;
select * from board where no = 58;

-- 답글 처리 
-- 게시글 리스트를 출력할 때 정렬 기준 
select * from board order by ref desc, refOrder asc;

-- 답글을 board테이블에 등록
-- ref = pRef, step = pStep + 1, refOrder = pRefOrder + 1 
update board set refOrder = refOrder + 1 where ref = ? and refOrder > ?;
insert into board(writter, title, content, ref, step, refOrder) values(?, ?, ?, ?, ?, ?);
select * from board;
desc board;
-- pointPolicy에 따라 답글 작성시 포인트 +1 , pointLog기록


-- 테스트하기위한 copy테이블 생성.
create table copyBoard as select * from board where 1 = 0;
select * from copyBoard;
truncate table copyBoard;
insert into copyBoard(no, writter, title, content, ref, step, refOrder) values(1, 'dooly', '게시판', '테스트', 1, 0, 0);

insert into copyBoard(no, writter, title, content, ref, step, refOrder) values(2, 'dooly', '답글', '테스트', 1, 1, 1);
select * from copyBoard where ref = 1 and step = 1;

-- 답글만 가져오기
select * from board where no != ref and ref = 121;

-- 페이징 처리
select * from board order by readCount desc limit 5;

-- 페이징을 구현할 때 필요한 것들
-- 한 페이지당 보여줄 글의 개수 3
-- 전체 페이지 개수 
-- 총 글의 개수 = 총 게시판 글의 갯수 / 한 페이지당 보여줄 글의 개수
-- 15 / 5 -> 3
-- 18 / 5 -> 3.5 나누어 떨어지지 않으면 +1
select count(*)from board;
-- 보여주기 시작할 row index번호
-- limit [보여주기 시작할 row index 번호], 보여줄 row의 갯수
-- [보여주기 시작할 row index 번호] = (현재 페이지번호 -1) * 페이지당 보여줄 글의 갯수
-- 1페이지
select * from board order by ref desc, refOrder asc limit 0, 3;
-- 2페이지
select * from board order by ref desc, refOrder asc limit 3, 3;
-- 3페이지
select * from board order by ref desc, refOrder asc limit 6, 3;
-- 4페이지
select * from board order by ref desc, refOrder asc limit 9, 3;

-- 페이징 블럭 처리
-- 1. 1개의 블럭에 몇개 페이지를 보여줄 것인지 (pageCntPerBlock) : 10
-- 1 2 3 4 5 6 7 8 9 10 / 11 12
-- 전체 페이징 블럭 갯수 = 2

-- 2. 현재 페이지가 속한 페이징 블럭 번호 : 현재 페이지 번호 / pageCntPerBlock -> 나누어 떨어지지 않으면 올림.
-- 현재 페이지가 1 이면 1번블럭 : 1 / 10 = 1
-- 현재 페이지가 12 면 2번 블럭 : 11 / 10 = 1.1 -> 2

select * from board order by ref desc, refOrder asc limit 9, 3;

-- 3. 현재 페이징 블럭 시작 페이지 번호 = (블럭번호 - 1) * pageCntPerBlock + 1
-- 4. 현재 페이징 블럭 끝 페이지 번호 = 블럭번호 * pageCntPerBlock
select * from board;
use shk;

select * from board;