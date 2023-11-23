package CardGame;

public class Card {
    String[] shape = {"heart", "spade", "clover", "diamond"};
    String[][] card = new String[4][13];
    Card(){
        for(int i = 0 ; i < shape.length ; i++) {
            String cardShape = shape[i];
            for(int j = 0 ; j < 13 ; j++) {
                card[i][j] = cardShape+(j+1);
            }
        }
    }

    public void printCard() {
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("개봉 직후 카드");
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
        for(int i = 0 ; i < card.length ; i++) {
            for(int j = 0 ; j < card[i].length ; j++){
                System.out.print(card[i][j]);
                if(j != card[i].length-1)System.out.print(", ");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------");
    }
}
