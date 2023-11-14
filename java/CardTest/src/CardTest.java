public class CardTest {
    public static void main(String[] args) {

        Card card1 = new Card();
        card1.kind = "Heart";
        card1.number = 7;

        System.out.println("card1 : " + card1 + ", 무늬는 " + card1.kind + ", 숫자는 " + card1.number);

        Card card2 = new Card();
        card2.kind = "Diamond";
        card2.number = 2;
        System.out.println("card2 : " + card2 + ", 무늬는 " + card2.kind + ", 숫자는 " + card2.number);

        // 클래스 변수는 클래스명.변수명
        System.out.println("card의 가로는 " + Card.width + ", 세로는 " + Card.height);}
}

class Card{
    String kind; // 카드 무늬 (인스턴스 변수)
    int number; // 카드 숫자 (인스턴스 변수)
    static int width = 100; // (클래스 변수)
    static int height = 200; // (클래스 변수)

}