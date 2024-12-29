package Tugas1_221141;

import java.util.Scanner;

public class anggreanyWhileSwitch {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean ulang = true;

        while (ulang) {
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

           
            double nilaiAkhir = (0.3 * nilaiTugas) + (0.3* nilaiMid) + (0.4 * nilaiFinal);

            // menghitung nilai huruf dengan switch case
            String nilaiHuruf;
            switch ((int) nilaiAkhir / 5) {
                case 20:
                case 19:
                case 18:
                    nilaiHuruf = "A";
                    break;
                case 17:
                    nilaiHuruf = "A-";
                    break;
                case 16:
                    nilaiHuruf = "B+";
                    break;
                case 15:
                case 14:
                    nilaiHuruf = "B";
                    break;
                case 13:
                    nilaiHuruf = "B-";
                    break;
                case 12:
                    nilaiHuruf = "C+";
                    break;
                case 11:
                case 10:
                    nilaiHuruf = "C";
                    break;
                case 9:
                case 8:
                case 7:
                case 6:
                    nilaiHuruf = "D";
                    break;
                default:
                    nilaiHuruf = "E";
                    break;
            }

            // membersihkan scanner
            scanner.nextLine();

            // menampilkan hasil
            System.out.println("STB: " + stb);
            System.out.println("Nama: " + nama);
            System.out.println("Nilai Tugas: " + nilaiTugas);
            System.out.println("Nilai Mid: " + nilaiMid);
            System.out.println("Nilai Final: " + nilaiFinal);
            System.out.println("Nilai Akhir: " + nilaiAkhir);
            System.out.println("Nilai Huruf: " + nilaiHuruf);

            // menanyakan apakah ingin mengulang inputan
            System.out.print("Ingin menghitung nilai mahasiswa lagi? (y/n): ");
            String ulangi = scanner.nextLine();
            if (!ulangi.equalsIgnoreCase("y")) {
                ulang = false;
            }
        }
        scanner.close(); // Menutup scanner untuk mencegah memory leak
    }
}
