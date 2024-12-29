package Latihan7_221141;

public class Latihan19_Lingkaran {
    final static double PI = 3.14; // variabel class
    double jarijari; // variabel instance

    Latihan19_Lingkaran(double r) {
        jarijari = r; // variabel lokal
    }

    double getKeliling() {
        double keliling; // variabel lokal
        keliling = 2 * jarijari * PI;
        return keliling;
    }

    double getLuas() {
        double luas; // variabel lokal
        luas = PI * jarijari * jarijari;
        return luas;
    }

   
}
