package Latihan4_221141;

import java.io.IOException;

public class Latihan10_WhileCount {
    public static void main (String args[]) {
      char input = (char) -1;
      int numToCount;
      System.out.println("Masukkan satu angka antara 1 dan 10: ");
      try {
        input = (char) System.in.read();
}
    catch (IOException e) {
        System.out.println("Error : " + e.toString());
    }
    numToCount = Character.digit(input, 10);
    if ((numToCount > 0) && (numToCount < 10)) {
        int i = 1;
        while (i <= numToCount) {
            System.out.println(i);
            i++;
        }
    }
    else System.out.println("ngka tsb tidak berada diantara 1 dan 10");
    }
}


