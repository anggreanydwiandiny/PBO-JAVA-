package Latihan5dan6_221141;

import javax.swing.JOptionPane;

class Latihan16_hitung {
    public double x, y, r;

    public Latihan16_hitung(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public double keliling() {
        return 2 * 3.14 * r;
    }

    public double luas() {
        return 3.14 * r * r;
    }
}

class lingkaran {
    public static void main(String[] args) {
        Latihan16_hitung c = new Latihan16_hitung(0, 0, 0);
        String Stra = JOptionPane.showInputDialog("Nilai X= ");
        int d = Integer.parseInt(Stra);
        c.x = d;
        String Strb = JOptionPane.showInputDialog("Nilai Y = ");
        int e = Integer.parseInt(Strb);
        c.y = e;
        String Strc = JOptionPane.showInputDialog("Nilai R= ");
        int f = Integer.parseInt(Strc);
        c.r = f;
        double area = c.luas();
        double kel = c.keliling();
        System.out.println("luas = " + area);
        System.out.println("keliling =" + kel);
    }
}
