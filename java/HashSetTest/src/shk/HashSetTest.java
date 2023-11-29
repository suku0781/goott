package shk;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

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
public class HashSetTest {
    public static void main(String[] args) {
        HashSet setA = new HashSet();
        HashSet setB = new HashSet();
        HashSet setUni = new HashSet(); // 합집합
        HashSet setInter = new HashSet(); // 교집합
        HashSet setDiff = new HashSet(); // 차집합


        setA.add("1");
        setA.add("2");
        setA.add("3");
        setA.add("4");
        setA.add("5");
        setA.add("5"); // 중복되기때문에 들어가지 않음.
        setA.add("1"); // 중복을 허용하지 않는다. 집합의 개념.
        System.out.println(setA.add("1")); // false

        setB.add("4");
        setB.add("5");
        setB.add("6");
        setB.add("7");
        setB.add("8");
        System.out.println(setB);

        System.out.println(setA);

        // Iterator()
        Iterator iter = setA.iterator();
        Iterator iter2 = setB.iterator();
        while (iter.hasNext()) {
            setUni.add(iter.next());
        }
        while (iter2.hasNext()) {
            setUni.add(iter2.next());
        }

        System.out.println("합집합" + setUni);
        // 교집합
        while (iter.hasNext()) {
            Object tmp = iter.next();
            if (setB.contains(tmp)) {
                setInter.add(tmp);
            }
        }
        System.out.println("교집합" + setInter);

        // 차집합
        iter = setA.iterator();
        while (iter.hasNext()) {
            Object tmp = iter.next();
            if (!setB.contains(tmp)) {
                setDiff.add(tmp);
            }

            System.out.println("차집합 : " + setDiff);
        }

        System.out.println("------------------------------------------------------------");

        // 메서드를 활용해서 차집합, 교집합, 합집합 만들기
        setA = makeSetA();
        setB = makeSetB();
        System.out.println(setA + ", " + setB); // 차집합
        setA.removeAll(setB);
        System.out.println(setA); // 차집합

        setA = makeSetA();
        setB = makeSetB();
        System.out.println(setA + ", " + setB); // 교집합
        setA.retainAll(setB);
        System.out.println(setA); // 교집합

        setA = makeSetA();
        setB = makeSetB();
        System.out.println(setA + ", " + setB); // 합집합
        setA.addAll(setB);
        System.out.println(setB); // 합집합
    }

    static HashSet makeSetA() {
        HashSet setA = new HashSet();
        setA.add("1");
        setA.add("2");
        setA.add("3");
        setA.add("4");
        setA.add("5");
        setA.add("5");
        setA.add("1");

        return setA;
    }

    static HashSet makeSetB() {
        HashSet setB = new HashSet();
        setB.add("4");
        setB.add("5");
        setB.add("6");
        setB.add("7");
        setB.add("8");

        return setB;
    }
}