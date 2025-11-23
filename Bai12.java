
import java.util.Scanner;
import java.util.TreeSet;

public class Bai12 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        TreeSet<Integer> se1 = new TreeSet<>();
        TreeSet<Integer> se2 = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            se1.add(x);
            se2.add(x);
        }
        int m = sc.nextInt();
        TreeSet<Integer> intersection = new TreeSet<>();
        for (int i = 0; i < m; i++) {
            int y = sc.nextInt();
            se1.add(y);
            if (se2.contains(y)) {
                intersection.add(y);
            }
        }
        for (int x : se1) {
            System.out.print(x + " ");
        }
        System.out.println();

        if (intersection.isEmpty()) {
            System.out.println("NO");
        } else {
            for (int x : intersection) {
                System.out.print(x + " ");
            }
        }
    }
}
