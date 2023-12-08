package com.shk.view;

import com.shk.controller.Action;
import com.shk.service.FriendManagementService;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * packageName : com.shk.view
 * fileName : FriendDBView
 * author : goott5
 * date : 2023-12-07
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-07          goott5             최초생성
 **/
public class FriendDBView {
    public static void outputMenu() {
        System.out.println("*********************************************************************************************************************************************************************************");
        System.out.println("                                                                               친구관리                                                                                 ");
        System.out.println("*********************************************************************************************************************************************************************************");
        System.out.println("1 : 친구조회(전체조회) | 2 : 친구추가 | 3 : 친구이름조회 | 4 : 친구이름수정 | 5 : 친구전화번호수정 | 6. 친구주소수정 | 7 : 친구삭제 | 9 : 종료");
        System.out.println("*********************************************************************************************************************************************************************************");
        System.out.println("메뉴 번호 입력 >> ");
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {


        while (true) {
            outputMenu();
            Scanner sc = new Scanner(System.in);
            int menu = sc.nextInt();
            FriendManagementService fms = Action.getInstance().getService(menu);
            try {
                fms.toDo();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
