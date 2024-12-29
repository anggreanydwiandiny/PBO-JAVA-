package Latihan5dan6_221141;

class Mhs {

    private String nama;
    private String nim;
    private String ttl;
    private String agama;

    public Mhs(String nama, String nim, String ttl, String agama) {
        this.nama = nama;
        this.nim = nim;
        this.ttl = ttl;
        this.agama = agama;
    }

    public String getNama() {
        return this.nama;
    }

    public String getNim() {
        return nim;
    }

    public static void main(String args[]) {
        Mhs m = new Mhs("Joko", "192001", "17 Januari 2000", "Hindu");
        System.out.println("Nama = " + m.getNama());
    }
}
