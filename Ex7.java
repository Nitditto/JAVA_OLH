
import java.util.Scanner;

public class Ex7 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap thang: ");
        int month = sc.nextInt();
        System.out.print("Nhap nam: ");
        int year = sc.nextInt();
        int day = switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 ->
                31;
            case 4, 6, 9, 11 ->
                30;
            case 2 ->
                ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) ? 29 : 28;
            default ->
                0; 
        };

        if (day == 0) {
            System.out.println("Thang khong hop le!"); 
        }else {
            System.out.println("Thang " + month + " nam " + year + " co " + day + " ngay");
        }
    }
}
