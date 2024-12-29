
package Tugas3_animasi_221141;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;

public class Animasi_lari extends JPanel implements Runnable {

    private Image[] stickmanpics = new Image[9]; // Menyesuaikan jumlah gambar
    private int idx = 0;
    private String[] stickmansrc = {
        "gambar1.jpg", "gambar2.jpg", "gambar3.jpg", "gambar4.jpg",
        "jump1.jpg", "jump2.jpg", "jump3.jpg", "jump4.jpg", "jump5.jpg" // Menyesuaikan nama gambar
    };

    private Image currentimg;
    private Thread runner;
    private int xpos = 0;
    private int ypos = 50;
    private boolean running = false;

    // Konstruktor untuk inisialisasi
    public Animasi_lari() {
        // Memuat gambar-gambar stickman dari sumber daya
        for (int i = 0; i < stickmanpics.length; i++) {
            URL imgURL = getClass().getResource(stickmansrc[i]);
            if (imgURL != null) {
                stickmanpics[i] = getToolkit().getImage(imgURL);
            } else {
                System.err.println("Couldn't find file: " + stickmansrc[i]);
            }
        }
        // Mengatur latar belakang panel menjadi putih
        setBackground(Color.white);
    }

    // Metode untuk memulai animasi
    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            running = true;
            runner.start();
        }
    }

    // Metode untuk menghentikan animasi
    public void stop() {
        running = false;
        if (runner != null) {
            try {
                runner.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runner = null;
        }
    }

    // Metode run yang dijalankan oleh thread
    @Override
    public void run() {
        while (running) {
            // Loop melalui gambar-gambar stickman
            for (int i = 0; i < stickmanpics.length; i++) {
                // Kondisi untuk beralih antara gambar berlari dan gambar melompat
                if (i < 4) {
                    currentimg = stickmanpics[i];
                } else {
                    currentimg = stickmanpics[i % 5 + 4]; // Mengulang gambar lompat
                }
                repaint();
                pause(150);
                xpos += 50;
                if (xpos >= getWidth()) {
                    xpos = 0;
                }
            }
        }
    }

    // Metode untuk menghentikan eksekusi sementara
    private void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Metode untuk menggambar komponen
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentimg, xpos, ypos, this);
    }

    // Metode utama untuk menjalankan program
    public static void main(String[] args) {
        // Membuat frame untuk menampung panel animasi
        JFrame frame = new JFrame("Animasi Lari");
        Animasi_lari animasi = new Animasi_lari(); // Membuat instance panel animasi
        frame.add(animasi); // Menambahkan panel animasi ke frame
        frame.setSize(new Dimension(800, 600)); // Mengatur ukuran frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Mengatur aksi penutupan frame
        frame.setVisible(true); // Menampilkan frame
        animasi.start(); // Memulai animasi
    }
}
