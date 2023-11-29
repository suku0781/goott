package shk;

import java.util.*;

/**
 * packageName : shk
 * fileName : Collection_TreeSet
 * author : goott5
 * date : 2023-11-29
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-29          goott5             최초생성
 **/
public class Collection_TreeSet {
    public static void main(String[] args){

        System.out.println("_____________HashSet__________________");
        Set<Integer> hs = new HashSet<>();

        hs.add(new Integer(1)); // 원래는 이렇게 넣어야함.
        hs.add(10); // auto-boxing, unboxing이 되므로 이렇게 해도 됨.
        hs.add(51); // Set은 중복허용하지 않고 순서 상관 없다.
        hs.add(98); // hashSet은 정렬까지해준다.
        hs.add(50); // List는 중복 허용하고 순서 상관 있다.
        hs.add(63);
        hs.add(2);
        hs.add(41);

        for(Integer i : hs){
            System.out.println(i);
        }

        System.out.println("_____________TreeSet__________________");
        Set<Integer> ts = new TreeSet<>();
        ts.add(1);
        ts.add(10);
        ts.add(51);
        ts.add(98);
        ts.add(50);
        ts.add(63);
        ts.add(2);
        ts.add(41);

        for(Integer i : ts){
            System.out.println(i);
        }

        System.out.println("_____________TreeSet에 list추가__________");
        List<Integer> listInt = new ArrayList<>();
        listInt.add(1);
        listInt.add(2);
        listInt.add(3);

        listInt.addAll(listInt);

        for(Integer i : listInt){
            System.out.println(i);
        }

        System.out.println("_____________TreeSet에 String추가__________");
        Set<String> listStr = new TreeSet<>();
        listStr.add("shk");
        listStr.add("Hay"); // 대문자 먼저
        listStr.add("jhs"); // 소문자 나중에

        Iterator<String> iter = listStr.iterator();

        while(iter.hasNext()){
            System.out.println(iter.next());
        }

        System.out.println("_____________TreeSet : subset_________");
        TreeSet set1 = new TreeSet<>(); // 제너릭이 없어도 가능.
        set1.add("shk");
        set1.add("123");
        set1.add("Hay"); // 대문자 먼저
        set1.add("jhs"); // 소문자 나중에
        set1.add("999");
        set1.add("가나다");
        set1.add("라마바"); // 숫자 영문 한글 순으로 정렬됨.
        set1.add("라마바"); // 중복허용 x

//        Iterator<String> iter2 = listStr.iterator();
        System.out.println(set1);
        System.out.println(set1.subSet("Hay","가나다"));

        System.out.println("_____________TreeSet : headSet, tailSet_________");
        TreeSet set2 = new TreeSet();
        int[] score = {80, 98, 66, 98, 29, 99, 45};

        for(int i = 0 ; i < score.length ; i++){
            set2.add(new Integer(score[i]));
        }

        System.out.println(set2);
        System.out.println("80보다 작은 값  : " + set2.headSet(new Integer(80), true )); // inclusive가 false일 경우 같은수를 포함하지 않음. default : false
        System.out.println("80보다 큰 값  : " + set2.tailSet(80));

        System.out.println("_____________TreeSet : lotto(1~45) 6개 수_________");
        TreeSet<Integer> set3 = new TreeSet();
        while(set3.size()<6) {
            int lotto = (int) Math.floor(Math.random() * 45+1);
            set3.add(lotto );
        }
        System.out.println(set3);

        System.out.println("_____________HashSet : lotto(1~45) 6개 수_________");
        HashSet<Integer> set4 = new HashSet<>();
        while(set4.size()<6) {
            int lotto = (int) Math.floor(Math.random() * 45+1);
            set4.add(lotto );
        }
        List list = new LinkedList(set4);
        Collections.sort(list);
        System.out.println(list);
    }
}
