package shk;

/**
 * packageName : shk
 * fileName : Customer
 * author : goott5
 * date : 2023-11-22
 * description :
 * --------------------------------------------------------
 * DATE             AUTHOR              NOTE
 * --------------------------------------------------------
 * 2023-11-22          goott5             최초생성
 **/
public class Customer {
    private int haveMoney;
    private int spentMoney = 0;

    Customer(int haveMoney) {
        this.haveMoney = haveMoney;
    }

    public int getHaveMoney() {
        return haveMoney;
    }

    public int getSpentMoney(){
        return spentMoney;
    }

    public void setHaveMoney(int haveMoney) {
        this.haveMoney = haveMoney;
    }

    public int withdrawMoney(int targetMoney, int money) {
        spentMoney += targetMoney;
        return money - targetMoney;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "haveMoney=" + haveMoney +
                '}';
    }
}
