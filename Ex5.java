
import java.util.Scanner;

public class Ex5 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            int q = sc.nextInt();
            switch (q) {
                case 1 -> {
                    double n = sc.nextDouble();
                    System.out.println((n * 1.8) + 32);
                }
                case 2 -> {
                    double n = sc.nextDouble();
                    System.out.println((n - 32) / 1.8);
                }
                default -> {
                    System.out.println("Tam biet!");
                    return; 
                }
            }
        }
    }
}
