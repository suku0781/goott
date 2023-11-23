/**
 * packageName : PACKAGE_NAME
 * fileName : Square
 * author : goott5
 * date : 2023-11-20
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-20          goott5             최초생성
 **/
public class Square extends Shape{
    int width;
    int height;

    public Square(String name, String color, Point p, int width, int height) {
        super(name, color, p);
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public void draw() {
//        System.out.println("가로 "+ this.getWidth() + ", 세로 " + this.getHeight() + "인 사각형을 그립니다.");
        System.out.println(this.toString() + " 사각형이 그려집니다.");
    }

    @Override
    public String toString() {
        return "Square{" +
                "width=" + width +
                ", height=" + height +
                ", getName()=" + getName() +
                ", getColor()=" + getColor() +
                '}';
    }
}
