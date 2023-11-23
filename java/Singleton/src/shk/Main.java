package shk;

import java.util.Calendar;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
//        Singleton s = new Singleton();
        for(int i = 0 ; i < 5 ; i++) {
            Singleton s = Singleton.getInstance(); // static 메서드이기때문에 italic체가 됨.
            System.out.println(s); // static 한 instance객체가 메모리에 이미 올라가있는것을 계속 호출하므로 같은 해시코드가 출력된다.
        }

        for(int i = 0 ; i < 5 ; i++) {
            Calendar c = Calendar.getInstance();
            System.out.println("Calendar.hashCode() : "+c.hashCode()); // 해시코드를 보면 다름. 완벽한 싱글톤이 아님. 비슷한 것.
        }

//        Calendar c2 = new Calendar // protected 일 경우 new 로 생성해서 사용하지 말라는 의미.
        // 만약 생성해서 사용해야한다면 abstract 메서드를 각각 구현해야 함.
    }
}