package Latihan4_221141;
 import java.io.IOException;

public class Latihan7_contohIfElse {
  
    public static void main(String args[]) {
        char HurufDepan = (char) -1;
        System.out.println("Masukkan huruf depan nama anda: ");
        try {
            HurufDepan = (char) System.in.read();
        } catch (IOException e) {
            System.out.println("Error : " + e.toString());
        }
        if (HurufDepan == -1)
            System.out.println("Anda tidak memasukkan huruf depan!");
        else if (HurufDepan == 'a') 
            System.out.println("Apa namamu Amin?");
        else if (HurufDepan == 'b')
            System.out.println("Apa namamu Bambang?");
        else if (HurufDepan == 'c')
            System.out.println("Apa namamu Charlie?");  
        else if (HurufDepan == 'd')
            System.out.println("Apa namamu Daud?");
        else if (HurufDepan == 'e')
            System.out.println("Apa namamu Endang?");
        else 
            System.out.println("Aku belum bisa menebak!");
    }
}


