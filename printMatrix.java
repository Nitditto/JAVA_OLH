import java.util.Scanner;

public class printMatrix {
    public static void input(int arr[][] , Scanner sc) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
    }
    public static void output(int arr[][]) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[i][j]+" "); 
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int arr [][] = new int[3][3]; 
        input(arr, sc);
        output(arr);
    }
}
