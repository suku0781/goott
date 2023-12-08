package com.shk.dao;

import com.shk.dto.FriendDTO;
import com.shk.vo.Friend;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * packageName : com.shk.dao
 * fileName : FriendMgmDaoImpl
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 */
public class FriendMgmDaoImpl implements FriendMgmDao{
    private static FriendMgmDaoImpl instance = null;

    private FriendMgmDaoImpl(){

    }

    /**
     * methodName : getInstance
     * author : shk
     * description :
     *
     * @return friend mgm dao
     */
    public static FriendMgmDaoImpl getInstance(){ // 싱글톤 패턴 메서드
        if(instance == null){
            instance = new FriendMgmDaoImpl();
        }

        return instance;
    }

    @Override
    public List<Friend> selectAllFriends() throws SQLException, ClassNotFoundException {
        // DB연결
        Connection con = DBConnection.getConnection();
        List<Friend> lst = new ArrayList<>();

        if(con != null){
            // 쿼리문
            String query = "select * from friends";
            // Statement객체
            PreparedStatement pstmt = con.prepareStatement(query);

            // 쿼리문 실행
            ResultSet rs = pstmt.executeQuery();

            // ResultSet
            // 한명씩 List에 add
            while(rs.next()){
                lst.add(new Friend(rs.getInt("FRIENDNO"),
                        rs.getString("friendName"),
                        rs.getString("mobild"),
                        rs.getString("addr")));
            }

            // 통신종료
            DBConnection.close(rs, pstmt, con);
        }
        return lst;
    }

    @Override
    public int getNextFriendNo() throws SQLException, ClassNotFoundException {
        // DB 연결
        Connection con = DBConnection.getConnection();
        int result = 0;

        if(con != null){
//            String query = "select max(friendNo) as maxNo from friends"; // 컬럼이름이 없는 경우
            String query = "select max(friendNo) as maxNo from friends"; // 컬럼이름이 있는 경우

            PreparedStatement pstmt = con.prepareStatement(query);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()){
//                result = rs.getInt(1); // max(friendNo)가 컬럼이름이 없는 경우 인덱스로 처리
                result = rs.getInt("maxNo") + 1; // max(friendNo)가 컬럼이름이 있는 경우우 인덱스로 처리
            }

            DBConnection.close(rs, pstmt, con);
        }
        return result;
    }

    @Override
    public int insertFriend(int friendNo, FriendDTO friend) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        int result = 0;
        if(con != null){
//            String query = "insert into friends values("+friendNo+", '"+friend.getFriendName()+"', '"+friend.getMobild()+"', '"+friend.getAddr()+"');";
            String query = "insert into friends values(?,?,?,?)";

            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setInt(1, friendNo);
            pstmt.setString(2, friend.getFriendName());
            pstmt.setString(3, friend.getMobild());
            pstmt.setString(4, friend.getAddr());

            result = pstmt.executeUpdate();

            DBConnection.close(pstmt, con);
        }
        return result;
    }

    @Override
    public int checkDuplicateMobild(String mobild) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        int result = 0; // 중복이 있다면 1 없다면 0

        if(con != null){
            String query = "select count(*) from friends where mobild = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, mobild);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                result = rs.getInt(1);
            }

            DBConnection.close(rs, pstmt, con);
        }
        return result;
    }

    public List<Friend> selectFriend(String name) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        List<Friend> lst = new ArrayList<>();

        if(con != null){
            String selectQuery = "select * from friends where friendname = ?"; // 친구 이름으로 friendNo가져오기
            PreparedStatement pstmt = con.prepareStatement(selectQuery);
            pstmt.setString(1, name);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                lst.add(new Friend( rs.getInt("FRIENDNO"),
                                    rs.getString("FRIENDNAME") ) );

            }
            DBConnection.close(rs, pstmt, con);
        }

        return lst;
    }
    public List<Friend> selectFriend(int friendNo) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        List<Friend> lst = new ArrayList<>();

        if(con != null){
            String selectQuery = "select * from friends where friendNo = ?"; // 친구 friendNo로 친구정보 가져오기
            PreparedStatement pstmt = con.prepareStatement(selectQuery);
            pstmt.setInt(1, friendNo);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                lst.add(new Friend( rs.getInt("FRIENDNO"),
                                    rs.getString("FRIENDNAME"),
                        rs.getString("MOBILD"),
                        rs.getString("ADDR"))  );

            }
            DBConnection.close(rs, pstmt, con);
        }

        return lst;
    }

    @Override
    public void updateFriend(int friendNo, String target, ) throws SQLException, ClassNotFoundException {
        Connection con = DBConnection.getConnection();
        List<Friend> lst = new ArrayList<>();

        if(con != null){
            //update emp01 set sal = 300000, ename = '마이콜' where empno = 1001;
            String query = "update friends set ? = ? where ? = ? ";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, );
        }
    }


}
