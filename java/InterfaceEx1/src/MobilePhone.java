/**
 * packageName : PACKAGE_NAME
 * fileName : MobilePhone
 * author : goott5
 * date : 2023-11-23
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-23          goott5             최초생성
 **/
public class MobilePhone extends Phone implements  Camera, Computer {
    // 전화, 문자, 인터넷, 카메라
    // MobilePhone이 가져야하는 기능을 규정하고 이런 기능이 있는 것을 mobilephone으로 하겠다는 일종의 계약.
    // 구현하지 못한 메서드가 있는 경우에는 현 클래스를 abstract class로 바꿔줘야한다.
    @Override
    public void calling() {

    }

    @Override
    public void receiving() {

    }

    @Override
    public void takePicture() {

    }

    @Override
    public void zoomIn() {

    }

    @Override
    public void zoomOut() {

    }

    @Override
    public void ComputerExpression() {

    }

    @Override
    public void playingApp() {

    }
}
