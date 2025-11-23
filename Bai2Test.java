
import java.util.Scanner;

public class Bai2Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        long a[] = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = sc.nextInt();
        }
        long tong = 0;
        for (int i = 0; i < n - 1; i++) {
            tong += a[i] * a[i + 1];
        }
        System.out.println(tong);
    }
}
