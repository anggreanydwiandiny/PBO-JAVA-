package Latihan8_2211141;

import java.io.DataInputStream;
import java.io.IOException;

class Latihan24_turunangolongan extends Latihan22_golonganUmur {
 
    static String cekGolongan(int umur) {
        String gol = "";
        if (umur < 17)
            gol = "anak-anak";
        else if (umur < 25)
            gol = "remaja";
        else if (umur < 40 )
            gol = "Dewasa";
        else 
            gol = " Manula";
        return gol;
    }
    
  
    public static void main(String[] args) {
        DataInputStream baca = new DataInputStream(System.in);
        int tahunlahir = 0;
        
        
        try {
            System.out.print("Input tahun kelahiran anda: ");
            tahunlahir = Integer.parseInt(baca.readLine());
        } catch (IOException e) {
            System.out.println("Input gagal!!!");
        }
       Latihan23_penghitunganumur Umurku;
        int tahunLahir = 0;
       Umurku = new Latihan23_penghitunganumur(tahunLahir);
       System.out.println(cekGolongan(Umurku.umur));
}
}
    
   


