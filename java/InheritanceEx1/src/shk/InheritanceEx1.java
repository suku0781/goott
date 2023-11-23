package shk;

import java.awt.*;

/**
 * packageName :
 * fileName : ${NAME}
 * author : goott5
 * date : 2023-11-20
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-20          goott5             최초생성
 **/
public class InheritanceEx1 extends Frame {
    public InheritanceEx1(String title){
        super(title);
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");
        InheritanceEx1 myWindow = new InheritanceEx1("나의 윈도우");
        System.out.println(myWindow.toString());

        myWindow.setVisible(true);
        Window pWindow = new Window(myWindow);
        pWindow.setBounds(null);

//        pWindow.setMenuBar(null); // 자식 frame클래스의 메서드 이므로 부모window 참조변수가 호출할 수 없다.

    }
}

