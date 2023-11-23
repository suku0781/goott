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
public class Main {
    public static void main(String[] args) {
        // shape객체가 추상 클래스이기때문에 객체를 만들려면 추상매서드를 오버라이딩 해야 만들어진다.
        // 객체가 분명해졌음을 의미한다. -> 그 객체는 더이상 Shape가 아님. 즉 추상적이지 않다.
        // 이 도형은 더이상 원, 삼각형, 사각형도 아우를 수 없다.
        // 이러한 논리적 모순이 생겨서 아래와 같이 쓰지 않는다.
        Shape s = new Shape("도형", "black", new Point(50, 50)) {
            @Override
            public void draw() {
                System.out.println("그린다.");
            }
        };
        System.out.println(s.toString());

        Triangle t1 = new Triangle("삼각형", "빨강", new Point(100, 100), 5, 10);
        System.out.println(t1.toString());
        t1.draw();

        Square s1 = new Square("사각형", "파랑", new Point(100, 100), 100, 100);
        System.out.println(s1.toString(1));
        s1.draw();

        Circle c1 = new Circle("원", "보라", new Point(50, 50), 50);
        System.out.println(c1.toString());
        c1.draw();

        System.out.println("---------------------------------------------------------------");
        Painter painter = new Painter("피카소");
        painter.drawShape(c1);




    }
}