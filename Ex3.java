
import java.util.Scanner;

public class Ex3 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        long tong = 0;
        for (int i = 0; i < s.length(); i++) {
            int num = s.charAt(i) - '0';
            tong += num;
        }
        System.out.println(s.length());
        System.out.println(tong);
    }
}
