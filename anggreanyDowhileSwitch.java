package Tugas1_221141;

import java.util.Scanner;

public class anggreanyDowhileSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String lanjut;
        
        do {
            System.out.print("Masukkan STB: ");
            String stb = scanner.nextLine();

            System.out.print("Masukkan Nama: ");
            String nama = scanner.nextLine();

            System.out.print("Masukkan Nilai Tugas: ");
            double nilaiTugas = scanner.nextDouble();

            System.out.print("Masukkan Nilai Mid: ");
            double nilaiMid = scanner.nextDouble();

            System.out.print("Masukkan Nilai Final: ");
            double nilaiFinal = scanner.nextDouble();

            // menghitung nilai akhir
            double nilaiAkhir = (0.4 * nilaiTugas) + (0.3 * nilaiMid) + (0.3 * nilaiFinal);

            // menghitung nilai huruf menggunakan switch
            String nilaiHuruf;
            int indeks = (int) nilaiAkhir / 10;
            switch (indeks) {
                case 10:
                case 9:
                    nilaiHuruf = "A";
                    break;
                case 8:
                    nilaiHuruf = "B+";
                    break;
                case 7:
                    nilaiHuruf = "B";
                    break;
                case 6:
                    nilaiHuruf = "C+";
                    break;
                case 5:
                    nilaiHuruf = "C";
                    break;
                case 4:
                    nilaiHuruf = "D";
                    break;
                default:
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

            scanner.nextLine(); // membersihkan newline dari buffer input
            System.out.print("Apakah ingin menghitung nilai mahasiswa lagi? (y/n): ");
            lanjut = scanner.nextLine(); // menyimpan input lanjut
        } while (lanjut.equalsIgnoreCase("y"));
        
        scanner.close(); // menutup scanner setelah selesai
    }
}
