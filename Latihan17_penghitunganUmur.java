package Latihan7_221141;

import java.io.*;
import java.util.*;

public class Latihan17_penghitunganUmur {
    static final int tahunSekarang = ambilTahun();
    int tahunLahir;
    protected int umur;
    
    public Latihan17_penghitunganUmur() {
    }
    
    public Latihan17_penghitunganUmur(int tahunLahir) {
        this.tahunLahir = tahunLahir;
        umur = tahunSekarang - tahunLahir;
    }
    
    static int ambilTahun() {
        Date tanggal = new Date();
        return tanggal.getYear() + 1900;
    }
    
    int hitungUmur(int tahunLahir) {
        return tahunSekarang - tahunLahir;
    }
    
    public static void main(String args[]) {
        DataInputStream baca = new DataInputStream(System.in);
        int tahunLahir = 0;
        Latihan17_penghitunganUmur saya;
        
        try {
            System.out.println("Input tahun kelahiran anda: ");
            tahunLahir = Integer.parseInt(baca.readLine());
        } catch(IOException e) {
        }
        
        saya = new Latihan17_penghitunganUmur(tahunLahir);
        
        System.out.println("Sekarang tahun: " + tahunSekarang);
        System.out.println("Anda sekarang berumur " + saya.umur + " tahun");
    }
}
