package com.shk.controller;

import com.shk.service.*;

/**
 * packageName : com.shk.controller
 * fileName : Action
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 **/
public class Action {
    private static Action instance = null;
    private Action(){

    }
    public static Action getInstance(){ // 싱글톤 패턴 메서드

        if(instance == null) instance = new Action();
        return instance;
    }

    public FriendManagementService getService(int menu){
        FriendManagementService result = null;

        switch (menu){
            case 1:
                result = new OutEntireFriendService();
            break;
            case 2:
                result = new SaveFriendService();
            break;
            case 3:
                result = new searchFriend();
            break;
            case 4:
                result = new EditFriendName();
            break;
//            case 5:
//                result = new SaveFriendService();
//            break;
//            case 6:
//                result = new SaveFriendService();
//            break;
//            case 7:
//                result = new SaveFriendService();
//            break;
            case 9:
                System.exit(0);
            default:
                System.out.println("다시 번호 입력.");

        }
        return result;
    }
}
