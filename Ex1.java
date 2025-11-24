
import java.util.Scanner;

public class Ex1 {

    public static boolean iSPrime(int n) {
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

        if (iSPrime(n)) {
            System.out.println("So nguyen to"); 
        }else {
            System.out.println("Khong phai so nguyen to");
        }
    }
}
