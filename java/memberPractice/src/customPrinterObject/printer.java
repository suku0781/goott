package customPrinterObject;

import java.util.Arrays;

public class printer {
    printer(String ...par){ System.out.println(Arrays.toString(par)); }
    printer(int ...par){ System.out.println(Arrays.toString(par)); }
    printer(double ...par){ System.out.println(Arrays.toString(par)); }
    printer(long ...par){ System.out.println(Arrays.toString(par)); }
    printer(char ...par){ System.out.println(Arrays.toString(par)); }
    printer(byte ...par){ System.out.println(Arrays.toString(par)); }
    printer(short ...par){ System.out.println(Arrays.toString(par)); }
    printer(boolean ...par){ System.out.println(Arrays.toString(par)); }

    public static void printer(String par) {
        System.out.println(par);
    }
}
