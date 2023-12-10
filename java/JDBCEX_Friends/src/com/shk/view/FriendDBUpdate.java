package com.shk.view;


import com.shk.dao.FriendMgmDao;
import com.shk.dao.FriendMgmDaoImpl;
import com.shk.vo.Friend;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class FriendDBUpdate {
    static String result = null;
    static Friend friend = null;
    static Scanner sc = new Scanner(System.in);

    public static Friend getFriendData() throws SQLException, ClassNotFoundException {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 친구이름 입력");
        String name = sc.nextLine();
        FriendMgmDao dao = FriendMgmDaoImpl.getInstance();
        List<Friend> friendList = dao.selectFriend(name);

        for(Friend f : friendList){
            System.out.println(f);
        }

        System.out.println("친구 friendNo 선택.");
        for(Friend f2 : dao.selectFriend(sc.nextInt())){
            friend = f2;
        }

        return friend;
    }

    public static String setFriendData(int friendNo, int type) {
        do {
            if(type == 1){
                System.out.print("수정후 친구 이름 >> ");
            } else if(type == 2){
                System.out.print("수정후 친구 전화번호 >> ");
            } else if(type == 3){
                System.out.print("수정후 친구 주소 >> ");
            }
                result = sc.nextLine();
        } while (result.equals(""));

        return result;
    }
}
