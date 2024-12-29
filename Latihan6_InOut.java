package Latihan3_221141;
import java.io.*;

class Latihan6_InOut {
    public static void main(String args[]) {
        DataInputStream in = new DataInputStream(System.in);
        char huruf = ' ';
        String kalimat = "";
        int angka = 0;

        try {
            // Menerima input String lalu dirubah ke Integer
            System.out.print("Enter a number: ");
            kalimat = in.readLine();
            angka = Integer.parseInt(kalimat);
            
            // Menerima karakter dari input-stream
            System.out.print("Enter a character: ");
            kalimat = in.readLine();
            huruf = kalimat.charAt(0);
            
            // Menerima String dari input-stream
            System.out.print("Enter some words: ");
            kalimat = in.readLine();
        } catch (IOException e) {
            System.out.println(e);
        }

        // Hasil input ditampilkan
        System.out.println("Here are what you typed in:");
        System.out.println(angka);
        System.out.println(huruf);
        System.out.println(kalimat);
    }
}
