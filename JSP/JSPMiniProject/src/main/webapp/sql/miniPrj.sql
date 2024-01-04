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
select * from member;
