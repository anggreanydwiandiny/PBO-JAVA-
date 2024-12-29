package Latihan4_221141;

import java.io.DataInputStream;

public class Latiihan8_contohIfelse {
    public static void main (String args[]) {
    DataInputStream baca = new DataInputStream(System.in);
    int na =0;
    String input;
    System.out.print("Masukkan Nilai Akhir Anda anda: ");
    try {
    input= baca.readLine();
        na = Integer.parseInt(input);
    }
    catch (Exception e) {
    System.out.println("Error : " + e.toString());
    }
        if (na >= 85)
        System.out.println("Nilai Huruf A");
    else if (na >= 75) 
        System.out.println("Nilai Huruf B");
        else if (na >= 65)
            System.out.println("Nilai Huruf C");
            else if (na >=45) 
    System.out.println("Nilai Huruf D");
        else 
            System.out.println("Nilai Huruf E");
    }
}


