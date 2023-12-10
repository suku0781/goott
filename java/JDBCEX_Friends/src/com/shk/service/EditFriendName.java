package com.shk.service;

import com.shk.dao.FriendMgmDao;
import com.shk.dao.FriendMgmDaoImpl;
import com.shk.dto.FriendDTO;
import com.shk.view.FriendDBUpdate;
import com.shk.vo.Friend;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * packageName : com.shk.service
 * fileName : EditFriendName
 * author : goott5
 * date : 2023-12-08
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-08          goott5             최초생성
 **/
public class EditFriendName implements FriendManagementService {
    @Override
    public void toDo() throws SQLException, ClassNotFoundException {
        Friend friend = FriendDBUpdate.getFriendData();
        int friendNo = friend.getFriendNo();
        String target = FriendDBUpdate.setFriendData(friendNo, 1);
        FriendMgmDao dao = FriendMgmDaoImpl.getInstance();

        // 친구저장
        if(dao.updateFriend(friend, target, 1) == 1){
            System.out.println("친구 수정 완료");
        }

    }
}
