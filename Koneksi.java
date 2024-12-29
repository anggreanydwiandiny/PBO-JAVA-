package Tugas5_Perpustakaan_Anggreany_221141;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Koneksi {
    private Connection koneksiDatabase;
    public Connection getKoneksiDatabase(){
        if (koneksiDatabase == null) {
    try {
        Class.forName("com.mysql.jdbc.Driver");
        
        System.out.println("Driver Ditemukan");
     try {
        koneksiDatabase = DriverManager.getConnection("jdbc:mysql://localhost:3306/database_perpustakaan_dini", "root", "");
        System.out.println("Koneksi Database Ditemukan");
    } catch (SQLException ex) {
        System.out.println("Koneksi Database Tidak Ditemukan :\nDengan Pesan : " + ex.toString());
    }
    }
    catch (ClassNotFoundException ex) {
        System.out.println("Class Driver Database Tidak Ditemukan :\nDengan pesan Error : " + ex.toString());
    }
        
}
        return koneksiDatabase;
}
}