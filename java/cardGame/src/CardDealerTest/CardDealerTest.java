package CardDealerTest;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class CardDealerTest extends JFrame {
    // 생성자
    public CardDealerTest(String title){
        super(title);
        this.setVisible(true); // 보이는 창이 나타나고 닫기 버튼이 활성화된다.
        this.setSize(new Dimension(1000, 1000));
    }

    @Override // annotation
    public void paint(Graphics g) {
        super.paint(g);
//        System.out.println("!");
        String imagePath = "D:\\lecture\\java\\pocker.jpg"; // 역슬래시(/)를 하나만 넣을 경우 탭기능을 실행하기때문에 두번써야 역슬래시(문자)로 인식한다.
        File file = new File(imagePath);


        try{
            g.drawImage(ImageIO.read(file), 500, 500, null);
        } catch (IOException e){
            e.printStackTrace();
            System.out.println("이미지를 불러오지 못했습니다.");
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        Dealer dealer = new Dealer();
        System.out.println(dealer.dpCard());
        dealer.shuffle();
        System.out.println("suffled dard"+dealer.dpCard());
        Card[] card = new Card[7];

        for(int i = 0 ; i < 7 ; i++) {
//            card[i] = dealer.pick();
//            card[i] = dealer.pickWithRemove(i);
            card[i] = dealer.pickCardWithRemoveArray();
//            dealer.pickWithRemove(i);
        }
        System.out.println("뽑은 카드: "+ Arrays.toString(card));
        System.out.println("남은 카드: "+ dealer.dpCard());

        System.out.println("-----------------------------------------------");
        System.out.println("뽑은 카드 : " + Arrays.toString(card));
        System.out.println("남은 카드 : " + dealer.dpCard());

        CardDealerTest win = new CardDealerTest("포커");



    }

}
