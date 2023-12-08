package com.shk.service;

import com.shk.dao.FriendMgmDao;
import com.shk.dao.FriendMgmDaoImpl;
import com.shk.vo.Friend;

import java.sql.SQLException;
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
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 친구 이름 입력");
        String name = sc.nextLine();
        FriendMgmDao dao = FriendMgmDaoImpl.getInstance();
        List<Friend> friendList = dao.selectFriend(name);

        for(Friend f : friendList){
            System.out.println(f);
        }

        System.out.println("친구 friendNo 선택.");
        dao.updateFriend(sc.nextInt(), "FRIENDNAME");
    }
}
