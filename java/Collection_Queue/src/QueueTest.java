import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

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
public class QueueTest {


    public static void main(String[] args) {
        Queue<String> queue = new ArrayDeque<>();
        Queue<String> queue1 = new LinkedList<String>(); // 주로 많이 씀.

        // 큐에 추가 삽입
        queue.add("하영");
        queue.add("연지");
        queue.offer("지우");
        queue.offer("수혁");

        // 큐에서 꺼내기
        System.out.println( queue );
        System.out.println(queue.poll() + " | " + queue );
        System.out.println(queue.poll() + " | " + queue );
        System.out.println(queue.poll() + " | " + queue );
        System.out.println(queue.poll() + " | " + queue );
        System.out.println(queue.poll() + " | " + queue );
        System.out.println(queue.remove() + " | " + queue );

    }
}