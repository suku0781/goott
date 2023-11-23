package shk;

/**
 * packageName : shk
 * fileName : Singleton
 * author : goott5
 * date : 2023-11-23
 * description : 싱글톤 패턴 기본 생성법
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class Singleton {
    private static Singleton instance; // 담아놓을 변수를 생성.

    // 기본 생성자
    private Singleton(){ }

    public static Singleton getInstance(){
        if(instance == null) instance = new Singleton();
        return instance;
    }

}

