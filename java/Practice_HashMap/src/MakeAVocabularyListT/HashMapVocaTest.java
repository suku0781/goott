package MakeAVocabularyListT;

import java.util.*;

/**
 * packageName : MakeAVocabularyListT
 * fileName : HashMapVocaTest
 * author : goott5
 * date : 2023-12-04
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-12-04          goott5             최초생성
 **/
public class HashMapVocaTest {
    public static Scanner sc = new Scanner(System.in);
//    public static Map<String, ArrayList<String>> vocabook = new HashMap<>();
    public static Map<String, ArrayList<String>> vocabook = new TreeMap<>(); // 등록된 단어 정렬 순서가 오름차순으로 바뀜.

    // 단어와 뜻을 입력
    private static void addWord(String word, String meaning) {
        if(vocabook.containsKey(word)){ // 단어장에 단어가 존재하는 경우
            vocabook.get(word).add(meaning);
        } else { // 단어장에 단어가 존재하지 않는 경우
            ArrayList<String> meanings = new ArrayList<String>();
            meanings.add(meaning);
            vocabook.put(word, meanings);
        }
    }

    // 단어장 출력
    private static void outputVocabook() {
        Set<String> words = vocabook.keySet();
//        for(String word : words){
//            System.out.print("단어 : " + word + ", 뜻 : " );
//            List<String> means = vocabook.get(word);
//            for(String mean : means){
//                System.out.print("  " + mean);
//            }
//            System.out.println();
//        }


        // Map-Entry 사용
        for(Map.Entry<String, ArrayList<String>> entry : vocabook.entrySet()){
            String word = entry.getKey();
            ArrayList<String> meaning = entry.getValue();
            System.out.println(word + " : " + meaning);
        }
    }

    public static void output() {
        while (true) {
            System.out.println("1. 단어 등록 | 2. 검색: 단어로 검색 | 3. 검색: 뜻으로 검색 | 4. 단어장 출력 | 5. 단어 삭제 | 9. 종료");

            System.out.print("메뉴 번호 입력 >> ");
            int menu = Integer.parseInt(sc.nextLine());

            switch (menu){
                case 1:
                    addNewWord();
                    break;
                case 2:
                    searchByWord();
                    break;
                case 3:
                    searchByMeaning();
                    break;
                case 4:
                    outputVocabook();
                    break;
                case 5:
                    deleteWord();
                    break;
                case 9:
                    System.exit(0);

            }
        }
    }

    private static void deleteWord() {
        System.out.print("지울 단어 입력 >> ");
        String targetWord = sc.nextLine();
        ArrayList<String> word = vocabook.get(targetWord);

        if(word != null){
            vocabook.remove(targetWord);
        } else {
            System.out.println(word + " 단어가 없음.");
        }
        System.out.println(targetWord + " 단어를 지움.");
    }


    private static void addNewWord() {
        System.out.print("등록할 단어 입력 >> ");
        String newWord = sc.nextLine();
        while(true){
            System.out.print("단어 뜻 입력(완료 시 q 입력) >> ");
            String newMeaning = sc.nextLine();

            if(newMeaning.toLowerCase().equals("q")){
                break;
            } else {
                addWord(newWord, newMeaning);
            }
        }

    }
    // 뜻으로 단어를 찾음.
    private static void searchByMeaning() {
        System.out.println("조회할 뜻을 입력");
        String meaningToSearch = sc.nextLine();
        System.out.println(meaningToSearch + "뜻의 딘어을 검색함...");
        boolean isFind = false;

        for(Map.Entry<String, ArrayList<String>> entry : vocabook.entrySet()){
            String word = entry.getKey();
            ArrayList<String> meanings = entry.getValue();
            if(meanings.contains(meaningToSearch)){
                isFind = true;
                System.out.println(word);
            }
        }

        if(!isFind) System.out.println(meaningToSearch+" 단어 없음.");
    }

    // 단어로 뜻을 찾음.
    private static void searchByWord() {
        System.out.print("조회할 단어 입력 >> ");
        String wordToSearch = sc.nextLine();
        System.out.println(wordToSearch + "단어의 뜻을 검색함...");
        ArrayList<String> meanings = vocabook.get(wordToSearch);

        if(meanings != null){
            System.out.println(wordToSearch + " : " + meanings);
        } else {
            System.out.println(wordToSearch + "의 뜻이 없음.");
        }
    }

    public static void main(String[] args) {
        // 단어와 뜻을 입력
        addWord("apple", "사과");
        addWord("apple", "(주)애플코리아"); // 같은 단어에 또 추가됨.
        addWord("apple", "아이폰메이커"); // 같은 단어에 또 추가됨.
        addWord("banana", "바나나");
        addWord("cherry", "체리");
        addWord("goava", "구아바");
        addWord("harmon", "하몽");

        output();
    }


}
