package com.shk.service;

import com.shk.dao.FriendMgmDao;
import com.shk.dao.FriendMgmDaoImpl;
import com.shk.dto.FriendDTO;
import com.shk.view.FriendDBInsert;

import java.sql.SQLException;

/**
 * packageName : com.shk.service
 * fileName : SaveFriendService
 * author : goott5
 * date : 2023-12-08
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-08          goott5             최초생성
 **/
public class SaveFriendService implements FriendManagementService {
    @Override
    public void toDo() throws SQLException, ClassNotFoundException {
        // 새 친구 저장(입력)
        System.out.println("친구 입력");
        FriendMgmDao dao = FriendMgmDaoImpl.getInstance();

        // 입력될 친구 데이터 가져오기
        FriendDTO friend = FriendDBInsert.getFriendData();

        // 입력될 친구의 friendNo를 위한 친구 번호 중 가장 큰 값 +1 가져오기
        int friendNo = dao.getNextFriendNo();

        // 친구저장
        if(dao.insertFriend(friendNo, friend) == 1){
            System.out.println("친구 저장 완료");
        }



    }
}
