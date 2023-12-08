package com.shk.service;

import com.shk.dao.FriendMgmDaoImpl;
import com.shk.vo.Friend;

import java.sql.SQLException;
import java.util.List;

/**
 * packageName : com.shk.service
 * fileName : OutuEntireFriendService
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 **/
public class OutEntireFriendService implements FriendManagementService {
    @Override
    public void toDo() throws SQLException, ClassNotFoundException {
        System.out.println("전체 친구 조회");

        FriendMgmDaoImpl fmd = FriendMgmDaoImpl.getInstance();
        List<Friend> lst = fmd.selectAllFriends();

        for(Friend f :lst) {
            System.out.println(f.toString());
        }

    }
}
