package thread_collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * packageName : thread_collection
 * fileName : SyncCollectionTest
 * author : goott5
 * date : 2023-12-05
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-05          goott5             최초생성
 **/
public class SyncCollectionTest {
    static final int THREADCNT = 2;

    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
        List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());

        ExecutorService es = Executors.newFixedThreadPool(THREADCNT);

        for (int i = 0 ; i < THREADCNT ; i++) {
            es.execute(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        synchronized (list) {
                            list.clear();
                            list.add(100);
                            list.remove(0);
                        }
                    }
                }
            });
        }


    }
}
