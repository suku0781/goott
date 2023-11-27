public class ChainedTest {

    public static void main(String[] args) throws InstallException {
        try {
            install();
        } catch (InstallException e) {
//            throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }
        System.out.println("설치를 종료합니다.");

    }

    static void install() throws InstallException {
        try {
            startInstall();
            copyFiles();
        } catch (SpaceException e) {
            System.out.println("----- 에러 메시지 : " + e.getMessage());
            InstallException ie = new InstallException("설치 중 예외 발생: 공간을 확보한 후 다시 설치하십시오.");
            ie.initCause(e);
            ie.getCause();
            throw ie;
        } catch (MemoryException e) {
            System.out.println("----- 에러 메시지 : " + e.getMessage());
        } catch (RuntimeException e){
            System.out.println("----- 에러 메시지 : " + e.getMessage());
        }

    }

    static void copyFiles() {
        System.out.println("파일복사를 시작합니다.");
    }

    static void startInstall() throws SpaceException, MemoryException, RuntimeException {
        System.out.println("설치를 시작합니다.....");
        // 공간이 충분하지 않은 경우
        if (!enoughSpace()) {
            throw new SpaceException("디스크 공간이 부족합니다");
        }

        if(!enoughMemory()){
//            throw new MemoryException("메모리기 부족합니다");
            throw new RuntimeException(new MemoryException("메모리가 부족합니다."));
        }

    }



    static boolean enoughSpace() {
        // TODO Auto-generated method stub
        return true;
    }

    static boolean enoughMemory() {
        // TODO Auto-generated method stub
        return false;
    }
}

class MemoryException extends Exception{
    MemoryException(String msg){
        super(msg);
    }
}

class InstallException extends Exception{
    InstallException(String msg){
        super(msg);
    }
}



class SpaceException extends Exception {
    // 사용자 정의 예외 클래스
    SpaceException(String msg){
        super(msg);
    }

}
