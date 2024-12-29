
package Latihan5dan6_221141;

    

class Latihan5_2_Buku {
   
    String pengarang;
    String judul;

    void isi(String isil, String isi2) {
        judul = isil;
        pengarang = isi2;
    }

    void cetakKeLayar() {
        if (judul == null && pengarang == null) return;
        System.out.println("Judul: " + judul + ", Pengarang: " + pengarang);
    }
}

class Karangan {
    public static void main(String[] args) {
        Latihan5_2_Buku a, b, c, d;
        a = b = c = d = new Latihan5_2_Buku();
        a.isi("Pemrograman Java", "Asep Herman Suyanto");
        a.cetakKeLayar();
        b.isi(null, null);
        b.cetakKeLayar();
        c.isi(null, "Johan Prasetyo Hendriyanto");
        c.cetakKeLayar();
        d.isi("Pemrograman Web", null);
        d.cetakKeLayar();
    }
}


