package CardDealerTest;

public class Card {
    // 카드 타입이 하트, 스페이드, 클로버, 다이아몬드) number : A(1) ~ 10, j(11), Q(12), K(13)
    private int kind; // 카드의 모양
    private int number; // 카드 숫자

    private static int width = 100;
    private static int height = 200;

    private static final int HEART = 1;
    private static final int SPADE = 2;
    private static final int CLOVER = 3;
    private static final int DIAMOND = 4;

    static final int KIND_MAX = 4; // default 접근자 (같은 패키지 안에서 접근가능)
    static final int NUMBER_MAX = 13; // default 접근자 (같은 패키지 안에서 접근가능)

    // 기본 생성자
    public Card(){

    }

    // 생성자 오버로딩
    public Card(int kind, int number){
        this.kind = kind;
        this.number = number;
    }

    public String toString(){
        String kind = "";
        String number = "";

        switch(this.number) {
            case 1:
                number = "A";
                break;
            case 11:
                number = "J";
                break;
            case 12:
                number = "Q";
                break;
            case 13:
                number = "K";
                break;
            default:
                number = this.number + "";
        }
        switch(this.kind) {
            case HEART:
                kind = "♥";
                break;
            case SPADE:
                kind = "♠";
                break;
            case CLOVER:
                kind = "♣";
                break;
            case DIAMOND:
                kind = "◆";
                break;
        }

        return kind + number;
    }


}
