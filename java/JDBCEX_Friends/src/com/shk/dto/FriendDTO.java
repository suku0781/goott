package com.shk.dto;

/**
 * packageName : com.shk.dto
 * fileName : FriendDTO
 * author : goott5
 * date : 2023-12-08
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-08          goott5             최초생성
 **/
public class FriendDTO {
    private String friendName;
    private String mobild;
    private String addr;

    public FriendDTO(String friendName, String mobild, String addr) {
        this.friendName = friendName;
        this.mobild = mobild;
        this.addr = addr;
    }
    public FriendDTO(String friendName) {
        this.friendName = friendName;
    }



    public String getFriendName() {
        return friendName;
    }

    public String getMobild() {
        return mobild;
    }

    public String getAddr() {
        return addr;
    }

    @Override
    public String toString() {
        return "FriendDTO{" + "friendName='" + friendName + '\'' + ", mobild='" + mobild + '\'' + ", addr='" + addr + '\'' + '}';
    }

}
