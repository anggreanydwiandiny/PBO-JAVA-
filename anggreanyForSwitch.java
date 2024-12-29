package Tugas1_221141;

import java.util.Scanner;


public class anggreanyForSwitch {

    public static void main(String[] args) {
        
        String pil,nama,jur,grade;
        int nim,nt,nm,nf,na,i;
        Scanner input=new Scanner(System.in);

        for (i=0;i<1;){
        
        System.out.println("=======ISI DATA BERIKUT======");
        System.out.print("Input NIM: ");
        nim=input.nextInt();
        System.out.print("Input Nama: ");
        nama=input.next();
        System.out.print("Input Jurusan: ");
        jur=input.next();
        System.out.print("Input Nilai tugas: ");
        nt=input.nextInt();
        System.out.print("Input Nilai MID: ");
        nm=input.nextInt();
        System.out.print("Input Nilai FINAL: ");
        nf=input.nextInt();
        
        na=(int) ((0.3*nt)+(0.3*nm)+(0.4*nf));
        
        switch (na){
            case 100: case 99: case 98: case 97: case 96: case 95: case 94: case 93: case 92:case 91:case 90:case 89:case 88:case 87:case 86:
                grade="A";
                break;
            case 85: case 84: case 83: case 82: case 81:
                grade="A-";
                break;
            case 80: case 79: case 78: case 77: case 76:;
                grade="B+";
                break;
            case 75: case 74: case 73: case 72: case 71:
                grade="B";
                break;
            case 70: case 69: case 68: case 67: case 66:
                grade="B-";
                break;
            case 65: case 64: case 63: case 62: case 61:
                grade="C+";
                break;
            case 60: case 59: case 58: case 57: case 56:
                grade="C";
                break;
            case 55: case 54: case 53: case 52: case 51: case 50: case 49: case 48: case 47: case 46: case 45: case 44: case 43: case 42: case 41:
                grade="D";
                break;
            default:
                grade="E";
        }
        
        System.out.println("");
        System.out.println("NIM : " + nim);
        System.out.println("Nama : " + nama);
        System.out.println("Jurusan : " + jur);
        System.out.println("Nilai akhir =: " + na);
        System.out.println("Grade : " + grade);
        
        System.out.print("Apakah masi ingin menginput  data(Y/N)?: ");
        pil=input.next();
        if("n".equals(pil) || "N".equals(pil)){
            i=1;
        }
        System.out.println("");
        }
               
    }
    
}