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

-- 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 insert하는 메서드
insert into readCountProcess(ipAddr, boardNo) values(?, ?);

-- 아이피주소와 글번호와 읽은시간을 readCountProcess테이블에 update하는 메서드
update readCountProcess set readtime = now() where ipAddr = ? and boardNo = ?;

-- no번 글에 조회수를 증가하는 쿼리문
-- 과제
