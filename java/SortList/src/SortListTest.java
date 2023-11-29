import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-29
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-29          goott5             최초생성
 **/
public class SortListTest {
    public static void main(String[] args) {
        String[] strArr = {"a","b","e","F","Q","w"};
        Arrays.sort(strArr); // 대문자 먼저 소문자 나중에 사전식 정렬
        System.out.println(Arrays.toString(strArr));

        Arrays.sort(strArr, String.CASE_INSENSITIVE_ORDER);
        System.out.println(Arrays.toString(strArr)); // 대소문자 구분 없이 사전식 정렬

        List<String> list = new ArrayList<String>();
        list.add("둘리");
        list.add("마이콜");
        list.add("또치");
        list.add("도우너");
        list.add("Kim su hyeok");
        list.add("roh Ha young");
        list.add("kim chon sik");
//        comparable은 java.lang에 있음. 기본으로 정렬해줌.

        System.out.println("-----------------정렬 전 ---------------");
        System.out.println(list);
        System.out.println("-----------------정렬 후 ---------------");
        Comparator<String> comp = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2); // 오름차순
//                return o1.compareTo(o2) * -1; // 내림차순
//                return o2.compareTo(o1); // 내림차순
            }
//            @Override
//            public int compare(String o1, String o2) {
//                if(o1 < o2) { // String 에는 <, >, <=, >= 연산자 적용 불가.
//                    return -1;
//                } else if(o1.equals(o2)){
//                    return 0;
//                } else {
//                    return 1;
//                }
//            }
        };
        list.sort(comp); // Comparator는 java.util에 있는 Collection.
        System.out.println(list);

        List<Integer> intList = new ArrayList<>();
        intList.add(12);
        intList.add(45);
        intList.add(78);
        intList.add(13);
        intList.add(46);
        intList.add(79);
        intList.add(78);

        System.out.println("-----------------정렬 전 ---------------");
        System.out.println(intList);
        System.out.println("-----------------정렬 후 ---------------");
        Comparator<Integer> comp2 = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
//                if(o1 < o2) { // 오름차순
//                    return -1;
//                } else if(o1 == o2) {
//                    return 0;
//                } else {
//                    return 1;
//                }

                if(o1 < o2) { // 내림차순
                    return 1;
                } else if(o1 == o2) {
                    return 0;
                } else {
                    return -1;
                }
            }
        };

        intList.sort(comp2); // Comparator는 java.util에 있는 Collection.
        System.out.println(intList);

    }
}