package shk;

public class ChainedTest {

    public static void main(String[] args) {
        install();

    }

    static void install() {
        try {
            startInstall();
        } catch (SpaceException e) {
            // TODO Auto-generated catch block
//         e.printStackTrace();
            System.out.println("----- 에러 메시지 :" + e.getMessage());

        }
//      copyFiles();

    }

    static void startInstall() throws SpaceException {
        System.out.println("설치를 시작합니다.....");
        // 공간이 충분하지 않은 경우
        if (!enoughSpace()) {
            throw new SpaceException("디스크 공간이 부족합니다");
        }

    }

    static boolean enoughSpace() {
        // TODO Auto-generated method stub
        return false;
    }



}

class SpaceException extends Exception {
    // 사용자 정의 예외 클래스
    SpaceException(String msg){
        super(msg);
    }

}
