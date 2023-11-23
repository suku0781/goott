package CardGame;

import java.lang.reflect.Array;

public class Main {
    // 셔플 메서드
    public static String shuffle(Card card){
        for(int i = 0 ; i < card.card.length ; i++){
            for(int j = 0 ; j < card.card[i].length ; j++){
                int iRanNum = (int)(Math.random() * card.card.length);
                int jRanNum = (int)(Math.random() * card.card[i].length);

                String tmp = card.card[iRanNum][jRanNum];

            }
        }
        return "";
    }


    public static void main(String[] args) {
        Card card = new Card();
        card.printCard();
        shuffle(card);
    }
}