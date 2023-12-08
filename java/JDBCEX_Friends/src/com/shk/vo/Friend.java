package com.shk.vo;

/**
 * packageName : com.shk.vo
 * fileName : Friend
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 **/
public class Friend {
    private int friendNo;
    private String friendName;
    private String mobild;
    private String addr;

    private int type;

    // 생성자
    public Friend(int friendNo, String friendName, String mobild, String addr) {
        this.friendNo = friendNo;
        this.friendName = friendName;
        this.mobild = mobild;
        this.addr = addr;
        this.type = 4;
    }

    public Friend(int friendNo) {
        this.friendNo = friendNo;
        this.type = 1;
    }

    public Friend(int friendno, String friendname) {
        this.friendNo = friendno;
        this.friendName = friendname;
        this.type = 2;
    }

    @Override
    public String toString() {
        String result = "";
        if(type >= 1) result += "Friend " + "friendNo=" + friendNo;
        if(type >= 2) result += ", friendName='" + friendName;
        if(type >= 3) result += '\'' + ", mobild='" + mobild;
        if(type >= 4) result += '\'' + ", addr='" + addr;
        return result;
    }
}
