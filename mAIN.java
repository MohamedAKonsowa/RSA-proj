import java.io.IOException;
import java.util.Scanner;

public class mAIN {
    public static void main(String[]args) throws IOException {
        RSA rsa = new RSA();
        System.out.println("1- Encrypt existing file\n2- Decrypt existing file\n3- create file");
        Scanner s1 = new Scanner(System.in);
        int actionChoice = s1.nextInt();
        if (actionChoice == 1){
            rsa.Encrypt();
        }
        else if (actionChoice == 2){
            rsa.Decrypt();
        }
        else if (actionChoice == 3){
            rsa.createFile();
        }
        else System.out.println("Invalid choice");
    }
}
