package shk;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * packageName : PACKAGE_NAME
 * fileName : Collection_ArrayListLinkedList
 * author : goott5
 * date : 2023-11-28
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-28          goott5             최초생성
 **/
public class Collection_ArrayListLinkedList {

    private static long addSeq(List list) {
        long start = System.currentTimeMillis();

        for(int i = 0 ; i < 100000 ; i++) list.add(i + "");

        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long addMed(List list) {
        long start = System.currentTimeMillis();

        for(int i = 0 ; i < 100000 ; i++) list.add(400 + "A");

        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long removeMed(List list) {
        long start = System.currentTimeMillis();

        for(int i = 0 ; i < 100000 ; i++) list.remove(400);

        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long removeSeq(List list) {
        long start = System.currentTimeMillis();

        for(int i = list.size()-1 ; i >= 0 ; i--) list.remove(i);

        long end = System.currentTimeMillis();
        return end - start;
    }

    private static long getEle(List list) {
        long start = System.currentTimeMillis();

        for(int i = list.size()-1 ; i >= 0 ; i--) list.get(i);

        long end = System.currentTimeMillis();
        return end - start;
    }


    public static void main(String[] args) {
        ArrayList a1 = new ArrayList(12);
        LinkedList ll = new LinkedList();

        System.out.println("순차적으로 요소 추가");
        System.out.println("ArrayList : " + addSeq(a1));
        System.out.println("LinkedList : " + addSeq(ll));
        System.out.println("---------------------------------------------------");

        System.out.println("중간에 요소 값 가져오기");
        System.out.println("ArrayList = " + getEle(a1));
        System.out.println("LinkedList = " + getEle(ll));
        System.out.println("---------------------------------------------------");

        System.out.println("중간에 요소 추가");
        System.out.println("ArrayList = " + addMed(a1));
        System.out.println("LinkedList = " + addMed(ll));
        System.out.println("---------------------------------------------------");

        System.out.println("중간에 요소 삭제");
        System.out.println("ArrayList = " + removeMed(a1));
        System.out.println("LinkedList = " + removeMed(ll));
        System.out.println("---------------------------------------------------");

        System.out.println("뒤에서 부터 요소 순차적으로 삭제");
        System.out.println("ArrayList = " + removeSeq(a1));
        System.out.println("LinkedList = " + removeSeq(ll));
        System.out.println("---------------------------------------------------");
    }



}
