package shk;

import java.util.*;

/**
 * packageName : teacher
 * fileName : DescendingByStdScore
 * author : goott5
 * date : 2023-11-30
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-30          goott5             최초생성
 **/
public class DescendingByStdScore implements Sortable {
    @Override
    public int compare(Student o1, Student o2) {
        if(o1.getScore() < o2.getScore()){
            return 1;
        } else if(o1.getScore() == o2.getScore()){
            return o1.getStdNo().compareTo(o2.getStdNo());
        } else {
            return -1;
        }
    }

    // 제 3자가 두학생을 비교.

}
