package shk;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-27
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-27          goott5             최초생성
 **/
public class ArrayListEx {
    public static void main(String[] args) {
        // 기본 생성자로 ArrayList 생성 : 기본 초기 길이가 10인 ArrayList 생성.
        ArrayList list1 = new ArrayList(); // 기본생성자

        list1.add(0);
        list1.add(new Integer(10));
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        System.out.println("list1의 길이 ; " + list1.size());

        ArrayList list2 = new ArrayList(list1.subList(0, 2)); // 0번째, 1번째 인덱스 값이 출력됨.
        System.out.println("list2 : " + list2);

        // containsAll(list2) : list2의 모든 요소가 포함되는지 여부
        System.out.println(list1.containsAll(list2)); // true

        list2.add("B");
        list2.add(3);
        list2.add(2, "A");
        System.out.println("list2.get(2)"+list2.get(2));
        System.out.println("list2.set(2)"+list2.set(2, "a"));
        System.out.println("list2 " + list2);

//        문자열 1 을 list1의 0번째에 추가하기
        list1.add(0, "1");

        System.out.println("문자열 1이 있는 index : " + list1.indexOf("1")); // 0
        System.out.println("숫자 1이 있는 index : " + list1.indexOf(1)); // -1

        list1.remove("1");

        System.out.println("retainAll()하기 전 list1 " + list1);
        System.out.println("list1.retainAll(list2) : " + list1.retainAll(list2)); // list1에서 list2와 겹치는 부분만 남기고 나머지는 제거. boolean 반환
        System.out.println("retainAll()한 후 list2 : " + list2);
        System.out.println("list2 " + list2);

        // removeAll
        System.out.println("list1 removeAll하기 전 " + list1);
        System.out.println("list2 removeAll하기 전 " + list2);
        System.out.println(list1.removeAll(list2));
        System.out.println("list1 removeAll한 후" + list1);
        System.out.println("list2 removeAll한 후" + list2);

        // clear
        System.out.println("list1 clear하기 전 " + list1);
        System.out.println("list2 clear하기 전 " + list2);
        list1.clear();
        list2.clear();
        System.out.println("list1 clear한 후" + list1);
        System.out.println("list2 clear한 후" + list2);

        list1.add(4);
        list1.add(5);
        list1.add(6);

        for(int i = 0 ; i < list1.size() ; i++ ){
            System.out.println(list1.get(i));
        }
        System.out.println("list1.size() "+list1.size());
        Iterator<Integer> iter = list1.iterator();

        System.out.println("iterator... ");
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

        Iterator<Integer> iter2 = list1.iterator();

        System.out.println("iterator... ");
        while(iter2.hasNext()){
            System.out.println(iter2.next());
        }
    }
}