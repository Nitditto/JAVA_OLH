
import java.math.BigInteger;
import java.util.Scanner;

public class Bai3Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0) {
            BigInteger a = sc.nextBigInteger();
            BigInteger b = sc.nextBigInteger();
            BigInteger c = sc.nextBigInteger();
            if (a.compareTo(BigInteger.ZERO) <= 0
                    || b.compareTo(BigInteger.ZERO) <= 0
                    || c.compareTo(BigInteger.ZERO) <= 0 || a.add(b).compareTo(c) <= 0
                    || a.add(c).compareTo(b) <= 0
                    || b.add(c).compareTo(a) <= 0) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }
        }
    }
}
