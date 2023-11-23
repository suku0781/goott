import javafx.print.Printer;

public class Main {
    public static void main(String[] args) {
        member m = new member("홍길동", "hong", "1234", "27");
        boolean isLogin = false;
        if(member.login(m.id, m.password)){
            System.out.println("로그인 되었습니다. ");
            isLogin = true;
        } else {
            System.out.println("아이디나 비밀번호가 틀렸습니다. ");
        }

        if(member.logout(m.id)){
            isLogin = false;
        }
        System.out.println(m.toString());



    }
}