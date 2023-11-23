/**
 * packageName : PACKAGE_NAME
 * fileName : Triangle
 * author : goott5
 * date : 2023-11-20
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-20          goott5             최초생성
 **/
public class Triangle extends Shape { // There is no default constructor available in 'Shape'
    // 부모 Shape클래스에 기본 생성자를 만들지 않은 상태에서 Triangle클래스를 만들려고 할 때 부모객체를 만들지 못해서 에러 발생.
    // 해결방법 1. 부모객체에 기본 생성자를 만들어준다.
    private int base;
    private int height;

    // 해결방법 2. 부모객체가 가지고있는 오버로딩된 생성자를 이용해서 부모객체를 만들도록 한다.
    public Triangle(String name , String color, Point p, int base, int height) {
        super(name, color, p);
        this.base = base;
        this.height = height;
    }

    // getter, setter
    public int getBase() {
        return base;
    }

    public void setBase(int base) {
        this.base = base;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw() {
        System.out.println("밑변 " + this.getBase() + ", 높이 "+ this.getHeight() +"인 삼각형을 그립니다.");
    }

    @Override
    public String toString() {
        return "Triangle{" +
                "base=" + base +
                ", height=" + height +
                ", getName()=" + super.getName() +
                ", getColor()=" + super.getColor() +
                ", getP()=" + super.getP() +
                '}';
    }
}
