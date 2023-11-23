import java.lang.reflect.Field;
import java.util.Arrays;

public class member {
    String name;
    String id;
    String password = null;
    String age = "0";

    // 기본생성자
    member() {

    }

    member(String name, String id, String password, String age) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.age = age;
    }

    public static boolean login(String id, String password){
        if(id.equals("hong") && password.equals("1234")){
            return true;
        } else {
            return false;
        }
    }

    public static boolean logout(String id){
        System.out.println(id + "님이 로그아웃 되었습니다.");
        return false;
    }

//
//    member(String name, String id) {
//        this.name = name;
//        this.id = id;
//    }

//    member(String... param){
//        int count = 0;
//        Field[] arr = this.getClass().getDeclaredFields();
//        for(String i : param){
//            String field = arr[count].toString().replace("java.lang.String ", "");
//            field = i;
//            count++;
//        }
//    }



//        이름 String name
//        아이디 String id
//        비밀번호 String password
//        나이 int(정수 타입중 아무거나) age
//


    @Override
    public String toString() {
        return "member{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", password='" + password + '\'' +
                ", age=" + age +
                '}';
    }
}
