package com.shk.view;

import com.shk.dto.FriendDTO;
import com.shk.service.DuplicateMobildService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * packageName : com.shk.view
 * fileName : FriendDBInsert
 * author : goott5
 * date : 2023-12-08
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-08          goott5             최초생성
 **/
public class FriendDBInsert {

    // 키보드로부터 친구정보를 입력받기
    public static FriendDTO getFriendData() throws SQLException, ClassNotFoundException {
        String name = null;
        String mobild = null;
        String addr = null;
        FriendDTO friend = null;

        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("저장할 친구 이름 >> ");
            name = sc.nextLine();
        } while (name.equals(""));
        do {
            System.out.println("저장할 친구 전화번호 >> ");
            mobild = sc.nextLine();
        } while (DuplicateMobildService.getInstance().duplicateMobildService(mobild));// 전화번호 중복체크
        System.out.println("저장할 친구 주소 >> ");
        addr = sc.nextLine();

        friend = new FriendDTO(name, mobild, addr);

        return friend;
    }


}
