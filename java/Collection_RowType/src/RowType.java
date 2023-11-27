import shk.Computer;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * packageName : PACKAGE_NAME
 * fileName : RowType
 * author : goott5
 * date : 2023-11-27
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-27          goott5             최초생성
 **/
public class RowType {
    public static void main(String[] args) {
        ArrayList ar = new ArrayList<>(); // raw type의 ArrayList객체 생성 경고 메시지
        // raw타입으로 생성된 ArrayList ar에는 모든 데이터 타입이 저장될 수 있다.
        // 데이터 타입의 안정성을 보장하지 못한다. Generic 타입을 사용할 것을 권고한다.
        ar.add(10); // Integer(wrapper class : 기본형을 객체로 만들어 주는 클래스)
        ar.add(3.14f); //
        ar.add(3.141592); //
        ar.add("대한민국"); //
        ar.add('A'); //
        ar.add(new Computer()); // Unchecked call to 'add(E)' as a member of raw type 'java.util.ArrayList'
        //
        // 팀원간 협업을 위해서 타입을 한가지로 정해서 ArrayList를 만들어야한다.

        System.out.println(ar.toString());

        // Generic 타입을 사용
        ArrayList<String> ar2 = new ArrayList<>();
        ar2.add("대한민국"); //
        ar2.add("대한민국"); //
//        ar2.add(12345); // no suitable method found for add(int)

        System.out.println(ar2.toString());
        
//        ArrayList<int> ar3 = new ArrayList<int>(); // Type argument cannot be of primitive type
        ArrayList<Integer> ar3 = new ArrayList<>(); // 정수들을 포함하는 Integer 클래스를 타입으로 선언.
        ar3.add(10);
        ar3.add(100); // 기본타입을 객체타입으로 자동으로 바꿔준다. wrapper클래스로 boxing.
        ar3.add(new Integer(1000));
        System.out.println(ar3.toString());

//        List<String> ar4 = new ArrayList<String>(); // 다형성
        List<String> ar4 = new Stack<>(); // ArrayList에서 Stack으로 변경해서 써도 하위의 코드에 영향을 미치지 않는다.

    }
}
