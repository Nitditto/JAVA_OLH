
import java.util.*;

public class Ex5Test {

    public static void solve(double a, double b, double c) {
        double delta = b * b - 4.0 * a * c;
        if (delta < -1e-12) {
            System.out.println("Phuong trinh vo nghiem");
            return;
        }
        if (Math.abs(delta) <= 1e-12) {
            double x = -b / (2.0 * a);
            System.out.printf("%.2f\n", x);
            return;
        }
        double sqrt = Math.sqrt(delta);
        double x1 = (-b - sqrt) / (2.0 * a);
        double x2 = (-b + sqrt) / (2.0 * a);
        if (x1 > x2) {
            System.out.printf("%.2f" + " " + "%.2f\n", x2, x1); 
        }else {
            System.out.printf("%.2f" + " " + "%.2f\n", x1, x2);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long a = sc.nextLong();
        long b = sc.nextLong();
        long c = sc.nextLong();
        long d = sc.nextLong();
        long e = sc.nextLong();
        long f = sc.nextLong();
        solve(a, b, c);
        solve(d, e, f);
        solve(a + d, b + e, c + f);
        solve(a - d, b - e, c - f);
    }
}
