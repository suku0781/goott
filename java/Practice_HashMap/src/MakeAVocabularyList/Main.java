package MakeAVocabularyList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

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
            System.out.println("---------------------------------------------------");
            System.out.println("English Vocabulary v0.0.1");
            System.out.println("Press the menu number");
            System.out.println("1: Regist A English Word | 2 : Search English Vocabulary | 9 : Turn Off The System.");
            System.out.println("---------------------------------------------------");
        } else {

        }
    }

    public static void main(String[] args) {
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
                    registWord(null);
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
        outputMenu("searchMain");
        String target =  sc.nextLine();

        System.out.println(target);
        System.out.println(wordMap.get(target));

    }

    private static void registWord(String type) {
        if(type.equals("first")){
            firstRegist();
        } else {
            outputMenu("registMain");
        }
    }

    private static void firstRegist() {
        wordMap.put( "test", new ArrayList<String>( Arrays.asList(new String[]{"테스트하다1", "테스트하다2", "테스트하다3"}) ) );
        wordMap.put( "abandon", new ArrayList<String>( Arrays.asList(new String[]{"v. 그만두다"," 단념하다","버리다","떠나다"}) ) );
        wordMap.put( "abstain", new ArrayList<String>( Arrays.asList(new String[]{"v. 그만두다","삼가다","끊다(from) ; 기권하다","회피하다"}) ) );
        wordMap.put( "aberrant", new ArrayList<String>( Arrays.asList(new String[]{"a. 정도를 벗어난","이상한 형태의","변종의 n. 괴짜","기인"}) ) );
        wordMap.put( "abstract", new ArrayList<String>( Arrays.asList(new String[]{"n. 발췌","요약","적요; 추상","이상 a. 추상적인","이론적인"}) ) );
        wordMap.put( "abide", new ArrayList<String>( Arrays.asList(new String[]{"vt. 참다","견디다; 저항하다 vi. 머무르다","체재하다"}) ) );
        wordMap.put( "abstruse", new ArrayList<String>( Arrays.asList(new String[]{"a. 난해한","심오한"}) ) );
        wordMap.put( "ablaze", new ArrayList<String>( Arrays.asList(new String[]{"a. 밝은 빛으로 빛나는; 타서","타올라서;흥분하여"}) ) );
        wordMap.put( "absurd", new ArrayList<String>( Arrays.asList(new String[]{"a. 어리석은","우스꽝스러운 ; 불합리한","모순된; 부조리한"}) ) );
        wordMap.put( "abolish", new ArrayList<String>( Arrays.asList(new String[]{"v. (법률","제도 등)을 폐지하다"}) ) );
        wordMap.put( "abuse", new ArrayList<String>( Arrays.asList(new String[]{"v. 모욕하다","욕하다; 악용하다","남용하다; 학대하다"}) ) );
        wordMap.put( "abortive", new ArrayList<String>( Arrays.asList(new String[]{"a. 유산의; 실패한 n. 낙태약","유산"}) ) );
        wordMap.put( "abysmal", new ArrayList<String>( Arrays.asList(new String[]{"a. 지독한"}) ) );
        wordMap.put( "abridge", new ArrayList<String>( Arrays.asList(new String[]{"v. 요약하다; 단축하다","삭감하다; 빼앗다","박탈하다"}) ) );
        wordMap.put( "accept", new ArrayList<String>( Arrays.asList(new String[]{"v. 받아들이다","허락하다;(사태 등)에 순응하다","정하다"}) ) );
        wordMap.put( "abrogate", new ArrayList<String>( Arrays.asList(new String[]{"v. (공식적으로)폐지하다","기하다; ~을 치우다","만두다"}) ) );
        wordMap.put( "accessible", new ArrayList<String>( Arrays.asList(new String[]{"a. 접근하기 쉬운","닿을 수 있는"}) ) );
        wordMap.put( "abruptly", new ArrayList<String>( Arrays.asList(new String[]{"ad. 갑자기","불시에"}) ) );
        wordMap.put( "accidental", new ArrayList<String>( Arrays.asList(new String[]{"a. 우연의","뜻밖의 ; 비본질적인","부속적인"}) ) );
        wordMap.put( "abscond", new ArrayList<String>( Arrays.asList(new String[]{"v. 몰래 도주하다","종적을 감추다"}) ) );
        wordMap.put( "acclaim", new ArrayList<String>( Arrays.asList(new String[]{"v. 갈채하다","환호하다","찬양하다; 큰 소리로 말하다"}) ) );
        wordMap.put( "accomplice", new ArrayList<String>( Arrays.asList(new String[]{"n. 공범자","한 패","한통속"}) ) );
        wordMap.put( "acrimonious", new ArrayList<String>( Arrays.asList(new String[]{"a. (태도,말 등이) 통렬한","신랄한","얼얼한"}) ) );
        wordMap.put( "account", new ArrayList<String>( Arrays.asList(new String[]{"v. (이유를)설명하다", "n.기술","서술","이야기","담화","가사"}) ) );
    }
}
