package teacher;

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
public class DescendingByStdScore implements Comparator<Student> {
    @Override
    public int compare(Student o1, Student o2) {
//        if(o1.getScore() < o2.getScore()){
//            return 1;
//        } else if(o1.getScore() == o2.getScore()){
//            return 0;
//        } else {
//            return -1;
//        }

        int compStdScore = Integer.compare(o1.getScore(), o2.getScore());

        return compStdScore * -1;
    }
    // 제 3자가 두학생을 비교.
}
