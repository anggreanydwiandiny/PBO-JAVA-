package Latihan8_2211141;

import Latihan8_2211141.Latihan23_penghitunganumur;
import java.io.*;

public class Latihan22_golonganUmur {
    static String cekGolongan(int umur) {
        String gol = "";
        if (umur < 20)
            gol = "anak-anak";
        else if (umur < 40)
            gol = "dewasa";
        else
            gol = "manula";
        return gol;
    }

    private static boolean cekgolongan(int umur) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public Latihan22_golonganUmur() {
    }
    
    public Latihan22_golonganUmur(int umur) {
        cekGolongan(umur);
    }
    
    public static void main(String[] args) {
        DataInputStream baca = new DataInputStream(System.in);
        int tahunlahir = 0;
        Latihan23_penghitunganumur aku;
        
        try {
            System.out.print("Input tahun kelahiran anda: ");
            tahunlahir = Integer.parseInt(baca.readLine());
        } catch (IOException e) {
            System.out.println("Input gagal!!!");
        }
        
        aku = new Latihan23_penghitunganumur(tahunlahir);
        System.out.println(cekgolongan(aku.umur));
    }
}
