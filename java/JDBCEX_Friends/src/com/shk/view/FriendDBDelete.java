package com.shk.view;

import com.shk.dao.FriendMgmDao;
import com.shk.dao.FriendMgmDaoImpl;
import com.shk.vo.Friend;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FriendDBDelete {
    public static Friend getFriend() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("친구이름 입력");
        String name = sc.nextLine();
        FriendMgmDao dao = FriendMgmDaoImpl.getInstance();
        List<Friend> friendList = dao.selectFriend(name);
        Friend friend = null;

        for(Friend f : friendList){
            System.out.println(f);
        }

        System.out.println("친구 friendNo 선택.");
        for(Friend f2 : dao.selectFriend(sc.nextInt())){
            friend = f2;
        }
        return friend;
    }

    public static Friend setFriend(){


        return null;
    }
}
