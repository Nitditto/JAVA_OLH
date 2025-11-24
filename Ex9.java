
import java.util.Scanner;

public class Ex9 {

    public static boolean check(int n) {
        int tong = 0;
        int tmp = n;
        while (n > 0) {
            int i = n % 10;
            tong += i * i * i;
            n /= 10;
        }
        return tmp == tong;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (check(n)) {
            System.out.println("Yes"); 
        }else {
            System.out.println("No");
        }
    }
}
