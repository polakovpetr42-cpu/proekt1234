import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Main extends JFrame {

    public BufferedImage buttonout, buttonstart, buttonsettings;

    MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) { //слушать мышку
            int x = e.getPoint().x;
            int y = e.getPoint().y;

            if (x > 885 && x < 1035 && y > 250 && y < 400) new lobbi();

            if (x > 885 && x < 1035 && y > 460 && y < 610) {
            }

            if (x > 885 && x < 1035 && y > 670 && y < 820) {
                dispose();
                System.exit(0);
            }
        }

        @Override public void mousePressed(MouseEvent e) {} //слушать мышку
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    };

    Main() {
        try {
            buttonout      = ImageIO.read(Main.class.getResource("/buttonOut.png"));
            buttonstart    = ImageIO.read(Main.class.getResource("/buttonPlay.png"));
            buttonsettings = ImageIO.read(Main.class.getResource("/buttonSettings.png"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        setSize(1920, 1080); //размер окна и название
        setTitle("Red ball 4");
        setVisible(true);
        addMouseListener(ml); //слушать мышку
    }

    public void paint(Graphics g) {      //нарисовать кнопки и фон темно синий
        super.paint(g);
        g.setColor(new Color(8, 15, 45));
        g.fillRect(0, 0, 1920, 1080);

        g.drawImage(buttonstart,    885, 250, 150, 150, this);
        g.drawImage(buttonsettings, 885, 460, 150, 150, this);
        g.drawImage(buttonout,      885, 670, 150, 150, this);
    }

    public static void main(String[] args) {
        new Main();
    }
}