package shk;


import java.util.*;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-30
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-30          goott5             최초생성
 **/
public class Main {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "하영씨");
        map.put(2, "연지씨");
        map.put(3, "지우씨");
        map.put(4, "수혁씨");
        map.put(5, "주영씨");
        map.put(6, "동진씨");
        map.put(7, "상용씨");

        map.put(7, "현성씨"); // 키가 중복되면 해당 키의 value가 overwrite된다.


        // 데이터 탐색
        if(map.containsKey(7)){
            System.out.println(map.get(7));
        } else {
            System.out.println("no people");
        }

        // Map에 있는 모든 키를 검색
        Set<Integer> keys = map.keySet();

        for(int i : keys) {
            System.out.print(i + "\t\t");

        }
        System.out.println();
        // Map에 있는 모든 값들을 검색
        Collection<String> values = map.values();
//        List<String> vlist = map.values(); // type-mismatch

        for(String v : values){
            System.out.print(v + "\t");
        }

        System.out.println();

        HashMap<String, String> userMap = new HashMap<String, String>();
        userMap.put("testId1", "jkl123");
        userMap.put("testId2", "qwerty123");
        userMap.put("testId3", "zxcvnm123");
        userMap.put("testId4", "qwertyuiop123");

        Scanner sc = new Scanner(System.in);

//        while (true) {
//            System.out.println("id와 pw를 입력하십시오.");
//            System.out.print("id >> ");
//            String userId = sc.nextLine();
//            System.out.print("password >> ");
//            String userPw = sc.nextLine();
//
//            if(userMap.containsKey(userId)){
//                if(userMap.get(userId).equals(userPw)){
//                    System.out.println(userId+"님 안녕하세요.");
//                    break;
//                } else {
//                    System.out.println("아이디나 비밀번호가 일치하지 않습니다.");
//                    continue;
//                }
//            } else {
//                System.out.println("해당 id가 존재하지 않습니다.");
//            }
//        }

        /*
        * 맵 생성 : Map : 학번, 성적
        * 총 학생 목록 출력
        * 총점, 평균, 최고점수, 최저점수
        *
        * */
        HashMap<String, Integer> scoreMap = new HashMap();

//        Scanner sc = new Scanner(System.in);
//        while (true) {
//            System.out.println("1 : 학생입력");
//            System.out.print("메뉴선택 >> ");
//            int num = sc.nextInt();

            int totalScore = 0;
            int maxScore = 0;
            int minScore = 100;
            scoreMap.put("1601050", 87);
            scoreMap.put("B611058", 99);
            scoreMap.put("2300001", 85);
            scoreMap.put("2300002", 26);
            scoreMap.put("2300002", 59);
            scoreMap.put("2300003", 81);

            System.out.println("학번\t\t점수" );
            for(String s : scoreMap.keySet()){
                System.out.println(s + "\t" + scoreMap.get(s));
                totalScore += scoreMap.get(s);
                if(maxScore < scoreMap.get(s)) maxScore = scoreMap.get(s);
                if(minScore > scoreMap.get(s)) minScore = scoreMap.get(s);
            }
            System.out.println("학생총점\t"+totalScore);
            System.out.println("학생평균\t"+(float)totalScore / scoreMap.size());
            System.out.println("최고점수\t"+maxScore);
            System.out.println("최저점수\t"+minScore);
//        }
        System.out.println("-------------------");

        HashMap<String, Integer> scoreMap2 = new HashMap();
        scoreMap2.put("1601050", 87);
        scoreMap2.put("B611058", 99);
        scoreMap2.put("2300001", 85);
        scoreMap2.put("2300002", 26);
        scoreMap2.put("2300002", 59);
        scoreMap2.put("2300003", 81);

        Set set = scoreMap2.entrySet();
        Iterator iter = set.iterator();

        while(iter.hasNext()){
            Map.Entry es = (Map.Entry) iter.next();
            System.out.println("학번 : "+es.getKey() + ", 점수 : "+es.getValue());
        }
        Set ks = scoreMap2.keySet();
        Collection vl = scoreMap2.values();
        System.out.println("학번 : "+ks);
        System.out.println("총학생수 : "+ks);
        System.out.println("점수 : "+ vl);
        int sum = 0;
        Iterator iter2 = vl.iterator();
        while(iter2.hasNext()){
            Integer i = (Integer) iter2.next();
            sum += i.intValue();
        }
        System.out.println("학생총점 : "+sum);
        System.out.println("학생평균 : "+(float)sum/scoreMap2.size());
        System.out.println("최고점수 : "+Collections.max(vl));
        System.out.println("최저점수 : "+Collections.min(vl));
    }
}