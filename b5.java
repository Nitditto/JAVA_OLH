
import java.util.Scanner;

public class b5 {
  public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    while (true) { 
        int q=sc.nextInt();
        if(q==1){
          double n=sc.nextDouble();
          System.out.println((n*1.8)+32);
        }
        else if(q==2){
          double n=sc.nextDouble();
          System.out.println((n-32)/1.8);
        }
        else{
          System.out.println("Tam biet!");
          break;
        }
    }
  }
}
