package ObjectEx3;

import java.util.Arrays;
import java.util.Scanner;

public class ObjectEx3 {
    private static Account[] accountArr = new Account[100];
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        int count = 0;
        ObjectEx3.choiceNumber(count);


    }

    // 번호 선택 메서드
    private static void choiceNumber(int count){
        System.out.println("---------------------------------------------------------------");
        System.out.println("정성을 다하는 KB국민은행 염창점 입니다. 진행하실 업무를 선택해주십시오.");
        System.out.println("1. 계좌생성 | 2. 계좌목록 | 3. 예금 | 4. 출금 | 5. 종료");
        System.out.println("---------------------------------------------------------------");
        System.out.print("선택 >> ");

        int selectNo = Integer.parseInt(sc.nextLine());

        switch(selectNo){
            case 1:
                // 꼐좌 생성 메소드 호출
                createAccount(count);
                count++;
                choiceNumber(count);
                break;
            case 2:
                // 계좌 목록 출력 메소드 호출
                for(Account i : accountArr){
                    if(i != null){
                        System.out.println(i.잔액조회());
//                        System.out.println(i);
                    }
                }
                choiceNumber(count);
                break;
            case 3:
                // 예금 메소드 호출
                deposit();
                choiceNumber(count);
                break;
            case 4:
                // 출금 메소드 호출
                withdraw();
                choiceNumber(count);
                break;
            case 5:
                // 종료 메소드 호출
                System.out.println("시스템을 종료합니다. 이용해주셔서 감사합니다.");
                System.exit(0);
                break;

        }

    }

    // 꼐좌 생성 메소드
    private static void createAccount(int count){
        String accountNo;
        String owner;
        int balance;

        System.out.print("이름 입력 >> ");
        Account account = new Account(count, sc.nextLine());
        accountArr[count] = account;
        System.out.println(accountArr[count].getOwner()+"님의 계좌"+ accountArr[count].getAccount() +"가 개설되었습니다.");
    }

    // 예금 메소드
    private static void deposit(){
        System.out.print("계좌번호 입력 >> ");
        String accountNo = sc.nextLine();

        System.out.print("예금액 입력 >> ");
        int deposit = Integer.parseInt(sc.nextLine());
        boolean flag = false;

        for(Account i : accountArr){
            if(i != null && i.accountNo.equals(accountNo)){
                i.setBalance(deposit, "deposit");
                flag = true;
            }
        }
        if(!flag) System.out.println("입력하신 계좌 " + accountNo + "는 존재하지 않는 계좌 입니다.");
    }

    // 출금 메소드
    private static void withdraw(){
        System.out.print("계좌번호 입력 >> ");
        String accountNo = sc.nextLine();

        System.out.print("출금액 입력 >> ");
        int withdraw = Integer.parseInt(sc.nextLine());
        boolean flag = false;

        for(Account i : accountArr){
            if(i != null && i.accountNo.equals(accountNo)){
                i.setBalance(withdraw, "withdraw");
                flag = true;
            }
        }
        if(!flag) System.out.println("입력하신 계좌 " + accountNo + "는 존재하지 않는 계좌 입니다.");
    }
}


