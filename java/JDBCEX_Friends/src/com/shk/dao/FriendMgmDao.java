package com.shk.dao;

import com.shk.dto.FriendDTO;
import com.shk.vo.Friend;

import java.sql.SQLException;
import java.util.List;

/**
 * packageName : com.shk.dao
 * fileName : FriendMgmDao
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 **/
public interface FriendMgmDao {
    // 친구 전체 목록 가져오기
    List<Friend> selectAllFriends() throws SQLException, ClassNotFoundException;

    // 친구 번호 중 MAX값 가져오기
//    List<Friend> selectAllFriends() throws SQLException, ClassNotFoundException;
    int getNextFriendNo() throws SQLException, ClassNotFoundException;

    int insertFriend(int friendNo, FriendDTO friend) throws SQLException, ClassNotFoundException;

    // 전화번호 중복 체크
    int checkDuplicateMobild(String mobild) throws SQLException, ClassNotFoundException;

    // 친구이름으로 리스트 뽑아내기
    List<Friend> selectFriend(String name) throws SQLException, ClassNotFoundException;
    List<Friend> selectFriend(int friendNo) throws SQLException, ClassNotFoundException;

    void updateFriend(int friendNo, String name) throws SQLException, ClassNotFoundException;
}
