package Tugas3_animasi_221141;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Color;

public class Animasi_lingkaran extends Applet implements Runnable{

    Thread runner;
    int xpos,ypos,ux1,ux2;
    
    /**
     *
     */
    @Override
    public void start(){
        if(runner==null){
            runner = new Thread(this);
            runner.start();
        }
    }

    /**
     *
     */
    @Override
    public void stop(){
        if(runner != null){
            runner.stop();
            runner=null;
        }
    }

    /**
     *
     */
    @Override
    public void run(){
        setBackground(Color.blue);
        while(true){
            for(xpos=5;xpos<=105;xpos+=4){
                repaint();
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){}
            }
            for(ypos=5;ypos<=105;ypos+=4){
                repaint();
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){}
            }
            for(xpos=105;xpos>5;xpos-=4){
                repaint();
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){}
            }
            for(ypos=105;ypos>5;ypos-=4){
                repaint();
                try{
                    Thread.sleep(100);
                }
                catch(InterruptedException e){}
            }
        }
    }
    

    /**
     *
     * @param g
     */
    @Override
    public void paint(Graphics g){
        g.setColor(Color.black);
        g.fillRect(0, 0, 100, 100);
        
        g.setColor(Color.green);
        g.fillRect(100, 0, 100, 100);
        
        g.setColor(Color.green);
        g.fillRect(0, 100, 100, 100);
        
        g.setColor(Color.black);
        g.fillRect(100, 100, 100, 100);
        
        g.setColor(Color.red);
        g.fillOval(xpos,ypos , 90, 90);
        
        ux1=ux2=0;
    }
}