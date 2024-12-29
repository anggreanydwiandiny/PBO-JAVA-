package Latihan9_221141;

public class Latihan26_overloadContoh {
   
    public static void main(String[] args){
        Car car1 = new Car(70);
        Car car2 = new Car("fast");
         
        System.out.println("The car1 speed is " + car1.getSpeed());
        System.out.println("The car2 speed is " + car2.getSpeed());
    }
}

class Car {
    int speed = 0;

    public Car(int speed) {
        this.speed = speed;
    }

    public Car(String speed) {
        if(speed.equals("fast")) {
            this.speed = 100;
        } else {
            this.speed = 50;
        }
    }

    public int getSpeed() {
        return speed;
    }
}
