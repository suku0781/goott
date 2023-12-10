package com.shk.service;

import com.shk.dao.FriendMgmDao;
import com.shk.dao.FriendMgmDaoImpl;
import com.shk.view.FriendDBDelete;
import com.shk.view.FriendDBUpdate;
import com.shk.vo.Friend;

import java.sql.SQLException;

public class DeleteFriend implements FriendManagementService {
    @Override
    public void toDo() throws SQLException, ClassNotFoundException {
        Friend friend = FriendDBDelete.getFriend();
        FriendMgmDao dao = FriendMgmDaoImpl.getInstance();

        // 친구저장
        if(dao.deleteFriend(friend) == 1){
            System.out.println("친구 삭제 완료");
        }

    }
}
