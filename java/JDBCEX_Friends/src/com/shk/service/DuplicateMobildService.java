package com.shk.service;

import com.shk.dao.FriendMgmDao;
import com.shk.dao.FriendMgmDaoImpl;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * packageName : com.shk.service
 * fileName : DuplicateMobildService
 * author : goott5
 * date : 2023-12-08
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-08          goott5             최초생성
 **/
public class DuplicateMobildService {
    // 싱글톤 인스턴스에 개겣를 담을 것임.
//    제어자는 private static 하게
    // 이름은 보통 instance로 함.
    // 생성자도 pricate하게
    // getInstance는 public static하게

    private static DuplicateMobildService instance = null;

    // 생성자
    private DuplicateMobildService(){}

    public static DuplicateMobildService getInstance(){
        if(instance == null) instance = new DuplicateMobildService();
        return instance;
    }


    /*중복체크하는 메서드, boolean조건 반환.*/
    public boolean duplicateMobildService(String mobild) throws SQLException, ClassNotFoundException {
        System.out.println(mobild+"전화번호 중복체크");

        boolean isDuplYn = false; // 전화번호가 중복된다면 true 를 반환 아니면 false

        // 중복 체크 후 결과가 1일 경우 true리턴, 0일경우 false리턴
        String query = "select count(*) from friends where mobild = '?'";

        FriendMgmDao dao = FriendMgmDaoImpl.getInstance();
        if(dao.checkDuplicateMobild(mobild) == 1){
            isDuplYn = true;
        }

        return isDuplYn;
    }


}
