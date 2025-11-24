
import java.util.Scanner;

public class Ex11 {

    public static boolean check(int n) {
        int sum = 0;
        for (int i = 1; i <= n / 2; i++) {
            if (n % i == 0) {
                sum += i;
            }
        }
        return sum == n;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (check(n)) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }
    }
}
