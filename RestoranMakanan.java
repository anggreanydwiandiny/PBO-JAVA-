package Tugas2_RestoranMakanann_221141;

import java.util.Scanner;

class Menu {
    static String[][] DftrMenu = {
        {"Ayam Lalapan", "20000", "Makanan"},
        {"Mie Pangsit", "15000", "Makanan"},
        {"Nasi Goreng", "13000", "Makanan"},
        {"Gado-gado", "12000", "Makanan"},
        {"Jus Buah Naga", "8000", "Minuman"},
        {"Kopi Hitam", "6000", "Minuman"},
        {"Es Kelapa", "7000", "Minuman"},
        {"Es Teh Manis", "5000", "Minuman"}
    };
}

public class RestoranMakanan {
    public static void main(String[] args) {
        int kode, jum, totp, totk = 0, totb, totbd, n = 0;
        String lagi, promo = "", progra = "", proba = "";
        boolean ulang = true;
        Scanner input = new Scanner(System.in);
        String[][] DftrPesanan = new String[100][100];

        System.out.println("=============== Daftar Menu ===============");
        System.out.println("Kode\tNama menu\tHarga\tKategori");
        for (int i = 0; i < Menu.DftrMenu.length; i++) {
            System.out.print((i + 1) + "\t");
            for (String item : Menu.DftrMenu[i]) {
                System.out.print(item + "\t");
            }
            System.out.println();
        }
        System.out.println("===========================================");

        while (ulang) {
            System.out.print("Pilih kode menu: ");
            kode = input.nextInt();
            System.out.print("Masukkan jumlah: ");
            jum = input.nextInt();
            
            if (kode >= 1 && kode <= Menu.DftrMenu.length) {
                int index = kode - 1;
                totp = Integer.parseInt(Menu.DftrMenu[index][1]) * jum;
                DftrPesanan[n][0] = Menu.DftrMenu[index][0];
                DftrPesanan[n][1] = Menu.DftrMenu[index][1];
                DftrPesanan[n][2] = String.valueOf(jum);
                DftrPesanan[n][3] = String.valueOf(totp);
                totk += totp;
                n++;
            } else {
                System.out.println("Kode menu tidak valid!");
            }

            System.out.print("Lagi? (y/t): ");
            lagi = input.next();
            if (lagi.equalsIgnoreCase("t")) {
                ulang = false;
            }
        }

        if (totk > 50000) {
            System.out.print("Total pesanan melebihi Rp.50000, terima promo beli 1 gratis 1? (y/t): ");
            promo = input.next();
            if (promo.equalsIgnoreCase("y")) {
                System.out.print("Pilih kode minuman (5,6,7,8) pada daftar menu: ");
                proba = input.next();
                switch (proba) {
                    case "5":
                    case "6":
                    case "7":
                    case "8":
                        int index = Integer.parseInt(proba) - 1;
                        proba = Menu.DftrMenu[index][0] + "\t" + Menu.DftrMenu[index][1] + "\t1\t" + Menu.DftrMenu[index][1];
                        progra = Menu.DftrMenu[index][0] + "\tGratis\t1\tGratis";
                        totk += Integer.parseInt(Menu.DftrMenu[index][1]);
                        break;
                    default:
                        System.out.println("Kode minuman tidak valid!");
                        break;
                }
            }
        }

        totb = totk + (int)(totk * 0.1) + 20000;

        System.out.println();
        System.out.println("============== Struk Pembayaran ==============");
        System.out.println("Pesanan\t\tHarga\tJumlah\tTotal");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(DftrPesanan[i][j] + "\t");
            }
            System.out.println();
        }
        if (promo.equalsIgnoreCase("y")) {
            System.out.println(proba);
            System.out.println(progra);
        }
        System.out.println("Pajak\t\t-\t-\t10%(" + (int)(totk * 0.1) + ")");
        System.out.println("Pelayanan\t-\t-\t20000");
        System.out.println("===========================================");
        if (totk > 100000) {
            totbd = totb - (int)(totb * 0.1);
            System.out.println("Kotor\t\t\t\tRp." + totk);
            System.out.println("Kotor (+pajak + layanan)\t\tRp." + totb);
            System.out.println("Bersih (diskon 10%)\t\tRp." + totbd);
        } else {
            System.out.println("Kotor\t\t\t\tRp." + totk);
            System.out.println("Bersih (+pajak + layanan)\t\tRp." + totb);
        }
        System.out.println("===========================================");
    }
}
