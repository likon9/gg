import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;

import java.util.concurrent.ThreadLocalRandom;
import javax.imageio.ImageIO;
import javax.swing.*;

public class DemoThread extends JFrame{



  int x,y;

    private static Image background;
    private static Image hero;
    private static Image platform;

    JButton bt, bt1;

    public DemoThread() {
        setTitle("Demo app");
        x=455;
        y=70;
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);
        setResizable(false);

        setContentPane(new Background()); // панель устанавливается как contentPane в наследнике JFrame
        Container content = getContentPane(); //теперь все элементы интерфейса будут на этой панели.

        bt = new JButton("Поворот влево");
        bt1 = new JButton("Поворот вправо");


        content.add(bt);
        content.add(bt1);



        bt.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {


                runleft();


            }
        });

        bt1.addActionListener(new ActionListener() {
            @Override
            public synchronized void actionPerformed(ActionEvent e) {

runright();




            }
        });



        content.add(new Hero());
    }

    private static class Background extends JPanel{ // отрисовка нового фона

        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            try {
                background = ImageIO.read(new File("D://псп/платформер/src/background.png"));
                platform = ImageIO.read(new File("D://псп/платформер/src/платформа.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            g.drawImage(background,0,0,null);
            g.drawImage(platform,350,250,null);

        }
    }

    private class Hero extends JPanel{

        public Hero() {
            setOpaque(false);
            setPreferredSize(new Dimension(1000, 600));
            try {
                hero = ImageIO.read(new File("D://псп/платформер/src/hero.png"));
            }
            catch (IOException exc) {};

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D graphics2D = (Graphics2D)g;
            graphics2D.drawImage(
                    hero, x, y, 80, 150, this);

        }
    }



    public void runleft() {

        repaint();

        try {

         x = x-60;

         hero = ImageIO.read(new File("D://псп/платформер/src/hero.png"));
             } catch (IOException e) {
                        e.printStackTrace();
        }
    }

    public void runright() {

        repaint();

        try {

            x = x+60;

            hero = ImageIO.read(new File("D://псп/платформер/src/hero.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new DemoThread().setVisible(true);
    }
}
