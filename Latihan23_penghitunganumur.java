package Latihan8_2211141;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

public class Latihan23_penghitunganumur {
    static final int tahunSekarang = ambilTahun();
    int tahunLahir;
    public int umur;
    
    public Latihan23_penghitunganumur() {
    }
    
    public Latihan23_penghitunganumur(int tahunLahir) {
        this.tahunLahir = tahunLahir;
        this.umur = hitungUmur(tahunLahir);
    }
    
    static int ambilTahun() {
        Date tanggal = new Date();
        return tanggal.getYear() + 1900;
    }
    
    static int hitungUmur(int tahunLahir) {
        return tahunSekarang - tahunLahir;
    }
    
    public static void main(String[] args) {
        BufferedReader baca = new BufferedReader(new InputStreamReader(System.in));
        int tahunLahir = 0;
        Latihan23_penghitunganumur saya;
        
        try {
            System.out.print("Input tahun kelahiran anda: ");
            tahunLahir = Integer.parseInt(baca.readLine());
        } catch(IOException e) {
            System.out.println("Input gagal !!!");
        }
        
        saya = new Latihan23_penghitunganumur(tahunLahir);
        
        System.out.println("Sekarang tahun: " + Latihan23_penghitunganumur.tahunSekarang);
        System.out.println("Anda sekarang berumur " + saya.umur + " tahun");
    }
}
