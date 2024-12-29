package Latihan10dan11_221141;
class Latihan28_B extends Latihan27_A {
    int B1;
    
    public void ENTRYB(int objekInputan) {
        System.out.println("B1 = " + objekInputan); // Menampilkan nilai ObjekInputan
        B1 = objekInputan;
        // A1=ObjekInputan; // Baris ini akan menyebabkan kesalahan karena A1 tidak dideklarasikan di dalam kelas B
        System.out.println("A2 = " + A2); // Menggunakan variabel A2 yang diwarisi dari kelas A
    }
}


