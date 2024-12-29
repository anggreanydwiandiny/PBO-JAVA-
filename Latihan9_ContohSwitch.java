package Latihan4_221141;

import java.io.IOException;


public class Latihan9_ContohSwitch {
   public static void main (String args[]) {
char HurufDepan = (char) -1;
System.out.println("Masukkan huruf depan nama anda:");
try {
HurufDepan = (char) System.in.read();
}
catch (IOException e) {
System.out.println("Error :  "+ e.toString());
}
 switch (HurufDepan) {
case (char) -1 -> System.out.println("bukan huruf depan!" );
case 'a' -> System.out.println("Apa namamu Amin?");
case 'b' -> System.out.println("Apa namamu Bambang?");
case 'c' -> System.out.println("Apa namamu Charlie?");
case 'd' -> System.out.println("Apa namamu Daud?");
case 'e' -> System.out.println("Apa namamu Endang?");
default -> System.out.println("Aku belum bisa menebak namamu"); 
}
}
}
