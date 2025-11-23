
import java.util.Scanner;

public class Bai1 {

    public static boolean nt(int n) {
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return n >= 2;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n % 2 == 0) {
            System.out.println("So chan"); 
        }else {
            System.out.println("So le");
        }

        if (nt(n)) {
            System.out.println("So nguyen to"); 
        }else {
            System.out.println("Khong phai so nguyen to");
        }
    }
}
