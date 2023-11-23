/**
 * packageName : PACKAGE_NAME
 * fileName : Painter
 * author : goott5
 * date : 2023-11-21
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-21          goott5             최초생성
 **/



public class Painter {
    private String name;

    // 생성자
    Painter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName() {
        this.name = name;
    }


    // 메서드
    // 화가 클래스를 추가한 후 그림을 그리게 하기
    public void drawShape(Shape s) {
        // 다형성의 장점1. 부모의 이름으로 모든 자식 객체를 매게변수로 다 받을 수 있다.
        System.out.println(this.name + " 화백이"+ s.toString(1) + "인 " + s.getName() + "을 그린다.");
        // 다형성의 장점2. 부모 이름으로 자식이 구현한 멤버를 사용한다.
        s.draw();
    }
}
