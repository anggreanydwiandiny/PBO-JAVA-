package Latihan3_221141;

public class Latihan5_bacadata2 {
    
  public static void main (String args[ ]) {
    byte buf[ ] = new byte[80];
    try {
        System.in.read(buf);
}
    catch (Exception e) {
        System.out.println("Error : " + e.toString());
}
    String s = new String(buf, 0);
    System.out.println(s);
        }

    }
