package Tugas1_221141;


import java.util.Scanner;

public class anggreanyWhileIf {
    public static void main(String[] args) {
      Scanner scanner = new Scanner(System.in);

        // jumlah mahasiswa yang ingin dihitung
        System.out.print("Masukkan jumlah mahasiswa yang ingin dihitung: ");
        int jumlahMahasiswa = scanner.nextInt();

        // perulangan untuk setiap mahasiswa
        int i = 0;
while (i < jumlahMahasiswa) {
            System.out.print("Masukkan STB: ");
            String stb = scanner.next();

            System.out.print("Masukkan Nama: ");
            String nama = scanner.next();

            System.out.print("Masukkan Nilai Tugas: ");
            double nilaiTugas = scanner.nextDouble();

            System.out.print("Masukkan Nilai Mid: ");
            double nilaiMid = scanner.nextDouble();

            System.out.print("Masukkan Nilai Final: ");
            double nilaiFinal = scanner.nextDouble();

            // menghitung nilai akhir
            double nilaiAkhir = (0.4 * nilaiTugas) + (0.3 * nilaiMid) + (0.3 * nilaiFinal);

            // menghitung nilai huruf
            String nilaiHuruf;
            if (nilaiAkhir >= 86 && nilaiAkhir <= 100) {
                nilaiHuruf = "A";
            } else if (nilaiAkhir >= 81 && nilaiAkhir <= 85) {
                nilaiHuruf = "A-";
            } else if (nilaiAkhir >= 76 && nilaiAkhir <= 80) {
                nilaiHuruf = "B+";
            } else if (nilaiAkhir >= 71 && nilaiAkhir <= 75) {
                nilaiHuruf = "B";
            } else if (nilaiAkhir >= 66 && nilaiAkhir <= 70) {
                nilaiHuruf = "B-";
            } else if (nilaiAkhir >= 61 && nilaiAkhir <= 65) {
                nilaiHuruf = "C+";
            } else if (nilaiAkhir >= 56 && nilaiAkhir <= 60) {
                nilaiHuruf = "C";
            } else if (nilaiAkhir >= 41 && nilaiAkhir <= 55) {
                nilaiHuruf = "D";
            } else {
                nilaiHuruf = "E";
            }

            // menampilkan hasil
            System.out.println("STB: " + stb);
            System.out.println("Nama: " + nama);
            System.out.println("Nilai Tugas: " + nilaiTugas);
            System.out.println("Nilai Mid: " + nilaiMid);
            System.out.println("Nilai Final: " + nilaiFinal);
            System.out.println("Nilai Akhir: " + nilaiAkhir);
            System.out.println("Nilai Huruf: " + nilaiHuruf);

            // increment counter
            i++;
        }
    }
}
