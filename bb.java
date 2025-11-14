import java.util.Scanner;

public class bb {
  public static String res(int n){
    if(n%2==0)
      return "Chan";
    return "Le";
  }
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int n = sc.nextInt();
    System.out.println(res(n));
  }
}
