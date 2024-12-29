package Latihan3_221141;

import java.io.IOException;

public class Latihan4_bacadata {
    public static void main(String args[]) {
        StringBuffer s = new StringBuffer();
        char c;
        try {
            while ((c = (char) System.in.read()) != '\n') {
                s.append(c);
            }
        } catch (IOException e) {
            System.out.println("Error : " + e.toString());
        }
        System.out.println(s);
    }
}



