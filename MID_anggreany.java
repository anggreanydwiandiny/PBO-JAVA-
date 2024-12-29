package MID_ANGGREANYDWIANDINY_221141;
import java.util.Scanner;

public class MID_anggreany {
    
int jumlahbeli;
double harga;
double diskon;
double totalpembelian;

public MID_anggreany(){
    this.jumlahbeli = 0;
    this.harga = 0.0;
    this.diskon = 0.0;
    this.totalpembelian = 0.0;
}
 public void inputdata(){
   Scanner scanner = new Scanner(System.in);

 System.out.print("Masukkan jumlah beli: ");
 jumlahbeli = scanner.nextInt();
 System.out.print("Masukkan harga : ");
 harga = scanner.nextDouble();
}

public double hitungdiskon(){
    diskon = 0.10 * (jumlahbeli * harga);
    return diskon;
 }

  public void cetakdataa(){
    totalpembelian = (jumlahbeli*harga) - hitungdiskon();
    System.out.println("Jumlah beli: "+jumlahbeli);
    System.out.println("Harga : "+ harga);
    System.out.println("Diskon: "+ diskon);
    System.out.println("Total pembelian setelah diskon: "+ totalpembelian);
    }

 public static void main(String[] args){
   MID_anggreany anggreany = new MID_anggreany();

   anggreany.inputdata();
anggreany.hitungdiskon();
anggreany.cetakdataa();
    }
}

