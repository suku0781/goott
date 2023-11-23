package CardDealerTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Dealer {
    public final static int CARD_NUM = 52;
    private Card[] cardDeck = new Card[CARD_NUM];
    private static int curCardCnt = 0; // 딜러가 현재 가지고 있는 카드 개수

    //생성자
    public Dealer() {
        curCardCnt = CARD_NUM;
        int count = 0;
        for(int cardKind = 1;  cardKind <= Card.KIND_MAX; cardKind++){
            for(int cardNum = 1; cardNum <= Card.NUMBER_MAX; cardNum++){
                this.cardDeck[count++] = new Card(cardKind, cardNum);
            }
        }
    }

    // shuffle
    public void shuffle() {
        for(int i = 0 ; i < 1000 ; i++) {
            int index = (int)(Math.random() * Dealer.curCardCnt); // 0 ~ 51
            // 섞기
            Card tmp = this.cardDeck[0];
            this.cardDeck[0] = this.cardDeck[index];
            this.cardDeck[index] = tmp;
        }
    }

    // pick
    public Card pick() {
        // 뽑은 카드 요소를 null로 바꾸기
        Card rtCard = null;
        int ranNum = 0;
        do{
            ranNum = (int)(Math.random() * cardDeck.length);
            rtCard = cardDeck[ranNum];

        }while(rtCard == null);

        cardDeck[ranNum] = null;
        curCardCnt--;

        return rtCard;
    }

    // pick
    public List pickWithRemovelist(){
        // 랜덤으로 카드를 뽑은 후 카드 배열의 크기를 줄여서 카드 덱을 새로 생성.
        List<String> list = new ArrayList<String>();
        for(int i = 0 ; i < cardDeck.length ; i++) {
            list.add(String.valueOf(cardDeck[i]));
        }

        list.removeAll(Arrays.asList("null"));
        return list;
    }


    public void pickWithRemove(int num){
        // 랜덤으로 카드를 뽑은 후 카드 배열의 크기를 줄여서 카드 덱을 새로 생성.
        Card[] tmpCardDeck = new Card[curCardCnt-1];
        int ranNum = 0;
        ranNum = (int)(Math.random() * cardDeck.length);
        Card rtCard = cardDeck[ranNum];
        System.out.println("tmpCardDeckLength" + tmpCardDeck.length);
        for(int i = 0 ; i < tmpCardDeck.length; i++) {

        }
//        for(int i = 0 ; i < tmpCardDeck.length ; i++) {
//            System.out.println("test");
////            if(this.cardDeck[i].equals(rtCard)){
////                tmpCardDeck[i] = this.cardDeck[i+1];
////                isPlusone = true;
////            } else {
////                if(isPlusone) {
////                    System.out.println('b');
////                    tmpCardDeck[i] = this.cardDeck[i+1];
////                } else {
////                    tmpCardDeck[i] = this.cardDeck[i];
////                }
////            }
//        }



        this.cardDeck = tmpCardDeck;

        System.out.println(tmpCardDeck.length+"tmpCardDeck "+Arrays.toString(tmpCardDeck));
    }

    public Card pickCardWithRemoveArray() {
        int index = 0;
        Card returnCard = null;
        index = (int)(Math.random() * curCardCnt);
        returnCard = this.cardDeck[index];

        // 새로운 빈 배열 생성.
        Card[] newCardDeck = new Card[curCardCnt-1];

        for(int from = 0 ; from < index ; from++) {
            newCardDeck[from] = this.cardDeck[from];
        }

        for(int from = index + 1 ; from < curCardCnt; from++){
            newCardDeck[from-1] = this.cardDeck[from];
        }

        curCardCnt--;

        this.cardDeck = newCardDeck;

        return returnCard;

    }




    public String dpCard() {
        return "전체카드 갯수 : " + cardDeck.length + ", 카드 : " + Arrays.toString(cardDeck);
    }

}
