package Latihan4_221141;

import java.io.DataInputStream;
import java.io.IOException;

public class Latihan11_ProgLoop {
    public static void main(String[] args) {
int N=0;
int idx =0;
String input= "";
DataInputStream in = new DataInputStream(System.in);
try {
System.out.print("Masukkan jumlah anak ayam: ");
input = in.readLine();
N = Integer.parseInt(input);
}
catch (IOException e) {}
idx = N;
while (idx > 1)
{
System.out.println("Kotek-kotek kotek ...");
System.out.println("Anak ayam berkotek");
System.out.println("Anak ayam turun " + idx);
idx--;
System.out.println("mati 1 tinggal " + idx);
System.out.println(" ");
}
System.out.println("Anak ayam turun " + idx);
System.out.println("mati 1 tinggal induknya");
}
}


