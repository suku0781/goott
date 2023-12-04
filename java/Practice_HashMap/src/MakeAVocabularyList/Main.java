package MakeAVocabularyList;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.util.*;

/**
 * packageName : MakeAVocabularyList
 * fileName : Main
 * author : goott5
 * date : 2023-11-30
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-30          goott5             최초생성
 **/
public class Main {
    static HashMap<String, ArrayList<String>> wordMap = new HashMap<>();
    static List<String> wordMap2 = new ArrayList<>();
    public static void outputMenu(String type){
        if(type.equals("main")){
            System.out.println("---------------------------------------------------");
            System.out.println("English Vocabulary v0.0.1");
            System.out.println("Press the menu number");
            System.out.println("1: Regist A English Word | 2 : Search English Vocabulary | 9 : Turn Off The System.");
            System.out.println("---------------------------------------------------");
        } else if(type.equals("searchMain")){
            System.out.println("Input The English Word To Search");
            System.out.print(">> ");
        } else if(type.equals("registMain")){
            System.out.println("Input English Word To Regist");
            System.out.print(">> ");
        } else if(type.equals("againYn")) {
            System.out.print("oneMoreTime(input Y/N) >> ");
        } else if(type.equals("inputMean")) {
            System.out.println("Input mean of English Word To Regist");
        } else if(type.equals("searchingBy")) {
            System.out.println("---------------------------------------------------");
            System.out.println("Press the menu number");
            System.out.println("1: search in English | 2 : search in Korean | 9 : back");
            System.out.println("---------------------------------------------------");
        }
    }

    public static void main(String[] args) throws IOException {
        /*
        * 2. 아래의 기능이 있는 영어단어암기장을 구현하시오. (Map을 이용.)
        - 영어 단어 등록
        - 영어 단어의 의미 등록(하나의 영어단어에는 여러가지 뜻이 있을 수 있다.)
        - 영어단어 뜻 검색
        - 뜻으로 영어단어 검색
        - 영어단어 삭제
        * */
        registWord("first");
        while (true) {
            outputMenu("main");
            Scanner sc = new Scanner(System.in);
            System.out.print(">> ");
            int num = sc.nextInt();
            switch (num){
                case 1:
                    registWord("regist");
                    break;
                case 2:
                    searchWord();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("err! please ");
                    break;
            }
        }

    }

    private static void searchWord() {
        Scanner sc = new Scanner(System.in);

        outputMenu("searchingBy");
        int type = sc.nextInt();
        if(type == 1){
            Scanner sc2 = new Scanner(System.in);
            outputMenu("searchMain");
            String target =  sc2.nextLine();
            System.out.println(target);
            System.out.println(wordMap.get(target));
        } else if(type == 2){

        } else if(type == 9){

        }

        outputMenu("againYn");
        String againYn =  sc.nextLine();
        if(againYn.toLowerCase().equals("y")) searchWord();
    }

    private static void registWord(String type) throws IOException {
        if(type.equals("first")){

            wordMap2 = firstRegist();
            for(int i = 0 ; i < wordMap2.size() ; i++) {
                String eng = "";
                List<String> mean = new ArrayList<>();
                eng = wordMap2.get(i).split(",")[0];
                for(int j = 1 ; j < wordMap2.get(i).split(",").length ; j++) {
                    mean.add(wordMap2.get(i).split(",")[j]);
                }
                wordMap.put(eng, (ArrayList<String>) mean);
            }
//            System.out.println(wordMap);

        } else if(type.equals("regist")) {

            Scanner sc = new Scanner(System.in);
            List<String> mean = new ArrayList<>();

            outputMenu("registMain");
            String regiEng = sc.nextLine();
            do{
            outputMenu("inputMean");
            mean.add(sc.nextLine());
            outputMenu("againYn");
            } while(sc.nextLine().toLowerCase().equals("y"));

            System.out.println("registed!");
            wordMap.put(regiEng, (ArrayList<String>) mean);
        }
    }

    private static List firstRegist() throws IOException {
        String currentFilePath = Paths.get("").toAbsolutePath().toString();
        currentFilePath+="/src/MakeAVocabularyList/Practice_HashMap.csv";
        English eng = new English();
        List<String> lines = eng.getEnglish(currentFilePath);
        return lines;
    }


}
