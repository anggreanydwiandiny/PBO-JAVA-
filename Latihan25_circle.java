package Latihan9_221141;

public class Latihan25_circle {
    public double x, y, r;
    
    // constructor pertama
    public Latihan25_circle(double x, double y, double r){
        this.x = x;
        this.y = y;
        this.r = r;
    }
    
    // constructor kedua
    public Latihan25_circle(double x, double y){
        this(x, y, 10); // Memanggil constructor pertama dengan nilai default r = 10
    }
    
    // constructor ketiga
    public Latihan25_circle(double r){
        this(0.0, 0.0, r); // Memanggil constructor pertama dengan x dan y default 0.0
    }
    
    // constructor keempat
    public Latihan25_circle(Latihan25_circle c){
        this(c.x, c.y, c.r); // Memanggil constructor pertama dengan nilai dari objek c
    }

    public double keliling(){
        return 2 * 3.14159 * r;
    }

    public double luas(){
        return 3.14159 * r * r;
    }
}
