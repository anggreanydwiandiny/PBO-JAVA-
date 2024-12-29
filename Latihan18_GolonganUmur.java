package Latihan7_221141;

import java.io.*;

public class Latihan18_GolonganUmur {
    static String cekGolongan(int umur) {
        String gol = "";
        if (umur < 20)
            gol = "Anak-anak";
        else if (umur >= 60)
            gol = "Manula";
        return gol;
    }

    public Latihan18_GolonganUmur() {
    }

    public Latihan18_GolonganUmur(int umur) {
        cekGolongan(umur);
    }

    public static void main(String[] args) {
        DataInputStream baca = new DataInputStream(System.in);
        int tahunLahir = 0;
        int umur;
        
        try {
            System.out.print("Input tahun kelahiran anda : ");
            tahunLahir = Integer.parseInt(baca.readLine());
        } catch (IOException e) {
            System.out.println("Input gagal !!!");
        }
        
        umur = ambilUmur(tahunLahir);
        System.out.println(cekGolongan(umur));
    }
    
    static int ambilUmur(int tahunLahir) {
        return 2022 - tahunLahir; // Angka 2022 bisa diganti dengan tahun sekarang
    }
}
