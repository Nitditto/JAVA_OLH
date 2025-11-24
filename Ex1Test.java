
import java.util.HashMap;
import java.util.Scanner;

public class Ex1Test {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int k = 1; k <= t; k++) {
            System.out.println("Test " + k + ":");
            int n = sc.nextInt();
            int a[] = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            HashMap<Integer, Integer> mp = new HashMap<>();
            for (int i = 0; i < n; i++) {
                if (!mp.containsKey(a[i])) {
                    mp.put(a[i], 1); 
                }else {
                    mp.put(a[i], mp.get(a[i]) + 1);
                }
            }
            for (int i = 0; i < n; i++) {
                if (mp.get(a[i]) > 0) {
                    System.out.println(a[i] + " " + mp.get(a[i]));
                    mp.put(a[i], 0);
                }
            }
        }
    }
}
