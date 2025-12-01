
import java.util.*;

public class Sort {

    // Bubble Sort: so sánh từng cặp phần tử liền kề trong mảng. Nếu phần tử đứng trước lớn hơn phần tử đứng sau → đổi chỗ. Sau mỗi vòng lặp, phần tử lớn nhất sẽ “nổi” dần về cuối mảng (giống bong bóng nổi lên mặt nước).
    public static void bubbleSort(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1; j++) {
                if (arr.get(j) > arr.get(j + 1)) {
                    Collections.swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (swapped) {
                System.out.print("Buoc " + (i + 1) + ": ");
                for (int x : arr) {
                    System.out.print(x + " ");
                }
                System.out.println();
            }
        }
    }

    // Insertion Sort: so sánh phần tử hiện tại với các phần tử bên trải đã sắp xếp
    public static void insertionSort(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n; i++) {
            int key = arr.get(i);
            int j = i - 1;
            while (j >= 0 && arr.get(j) > key) {
                arr.set(j + 1, arr.get(j));
                j--;
            }
            arr.set(j + 1, key);

            System.out.print("Buoc " + i + ": ");
            for (int k = 0; k <= i; k++) {
                System.out.print(arr.get(k) + " ");
            }
            System.out.println();
        }
    }

    // Selection Sort
    public static void selectionSort(ArrayList<Integer> arr) {
        int n = arr.size();
        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr.get(j) < arr.get(min_idx)) {
                    min_idx = j;
                }
            }
            Collections.swap(arr, i, min_idx);

            System.out.print("Buoc " + (i + 1) + ": ");
            for (int x : arr) {
                System.out.print(x + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Integer> arr = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            arr.add(sc.nextInt());
        }
        ArrayList<Integer> arr1 = new ArrayList<>(arr);
        ArrayList<Integer> arr2 = new ArrayList<>(arr);
        ArrayList<Integer> arr3 = new ArrayList<>(arr);
        System.out.println("Bubble Sort:");
        bubbleSort(arr1);

        System.out.println("Insertion Sort:");
        insertionSort(arr2);

        System.out.println("Selection Sort:");
        selectionSort(arr3);
    }
}
