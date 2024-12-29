package Tugas3_animasi_221141;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.net.URL;

public class i extends JPanel implements Runnable {

    private Image[] stickmanpics = new Image[4];
    private int idx = 0;
    private String[] stickmansrc = {
        "gambar1.jpg", "gambar2.jpg", "gambar3.jpg", "gambar4.jpg",
        "jump1", "jump2",  "jump3", "jump4","jump5"
    };

    private Image currentimg;
    private Thread runner;
    private int xpos = 0;
    private int ypos = 50;
    private boolean running = false;

    public i() {
        for (int i = 0; i < stickmanpics.length; i++) {
            URL imgURL = getClass().getResource(stickmansrc[i]);
            if (imgURL != null) {
                stickmanpics[i] = getToolkit().getImage(imgURL);
            } else {
                System.err.println("Couldn't find file: " + stickmansrc[i]);
            }
        }
        setBackground(Color.white);
    }

    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            running = true;
            runner.start();
        }
    }

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

    @Override
    public void run() {
        while (running) {
            for (int i = 0; i < stickmanpics.length; i++) {
                currentimg = stickmanpics[i];
                repaint();
                pause(150);
                xpos += 50;
                if (xpos >= getWidth()) {
                    xpos = 0;
                }
            }
        }
    }

    private void pause(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(currentimg, xpos, ypos, this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Animasi Lari");
        i animasi = new i();
        frame.add(animasi);
        frame.setSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        animasi.start();
    }
}
