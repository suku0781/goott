package com.shk.service;

import com.shk.dao.FriendMgmDao;
import com.shk.dao.FriendMgmDaoImpl;
import com.shk.view.FriendDBUpdate;
import com.shk.vo.Friend;

import java.sql.SQLException;

public class EditFriendMobild implements FriendManagementService {
    @Override
    public void toDo() throws SQLException, ClassNotFoundException {
        Friend friend = FriendDBUpdate.getFriendData();
        int friendNo = friend.getFriendNo();
        String target = FriendDBUpdate.setFriendData(friendNo, 2);
        FriendMgmDao dao = FriendMgmDaoImpl.getInstance();

        // 친구저장
        if(dao.updateFriend(friend, target, 2) == 1){
            System.out.println("친구 수정 완료");
        }

    }
}
