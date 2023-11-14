package shk;

import java.util.Arrays;

public class ArrayEx7 {
    public static void main(String[] args) {
        System.out.println(args.length);
        System.out.println(Arrays.toString(args));
        System.out.println("어떻게 해야지 저게 뜨지?");
        if(args.length > 0) {
            for(int i = 0 ;i  < args.length ; i++) {
                if(args[i].equals("\\w")){
                    System.out.println(("P   r   i   n   t   W   i   d   e"));
                } else {
                    System.out.println(("P   r   i   n   t   T   E   S   T"));
                }
            }
        }

    }

}
