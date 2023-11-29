package shk;

import java.util.*;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-28
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-28          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
        Korean shk = new Korean("970411", "김수혁");
        Korean shk2 = new Korean("970411", "김수혁");
        Korean shk3 = new Korean("971010", "김수혁");

        System.out.println(shk.hashCode());
        System.out.println(shk2.hashCode());
        Set<Korean> set = new HashSet<Korean>();
        set.add(shk);
        set.add(shk2);
        set.add(shk3);

        for(Korean kor : set){
            System.out.println(kor.toString() + ", " + kor.hashCode()); // 중복되어 들어간다.
        }


        HashSet<Korean> hashSet = new HashSet<>(set);
        System.out.println("TEST"+hashSet);

        hashSet.hashCode();

        for(Korean kor : set){
            System.out.println(kor.toString() + ", " + kor.hashCode()); // 중복되어 들어간다.

        }

//        // 중복되어 들아가지 않도록 만들기
//        Set<Korean> set2 = new HashSet<Korean>();
//        set2.add(shk);
//        for(Korean kor : set2){
//            if( kor.getRegNo().equals(shk2.getRegNo()) && kor.getName().equals(shk2.getName()) ){
//                System.out.println("넣을 수 없음.");
//            } else {
//                set2.add(shk2);
//            }
//        }


    }
}