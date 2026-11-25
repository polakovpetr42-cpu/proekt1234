import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class lobbi extends JFrame {

    public BufferedImage buttonstart;

    MouseListener ml = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) { //слушать мышку
            int x = e.getPoint().x;
            int y = e.getPoint().y;

            if (x > 0 && x < 384 && y > 0 && y < 540) new menu();
            if (x > 384 && x < 768 && y > 0 && y < 540) new menu();
            if (x > 768 && x < 1152 && y > 0 && y < 540) new menu();
            if (x > 1152 && x < 1536 && y > 0 && y < 540) new menu();
            if (x > 1536 && x < 1920 && y > 0 && y < 540) new menu();

            if (x > 0 && x < 384 && y > 540 && y < 1080) new menu();
            if (x > 384 && x < 768 && y > 540 && y < 1080) new menu();
            if (x > 768 && x < 1152 && y > 540 && y < 1080) new menu();
            if (x > 1152 && x < 1536 && y > 540 && y < 1080) new menu();
            if (x > 1536 && x < 1920 && y > 540 && y < 1080) new menu();
        }

        @Override public void mousePressed(MouseEvent e) {} //слушать мышку
        @Override public void mouseReleased(MouseEvent e) {}
        @Override public void mouseEntered(MouseEvent e) {}
        @Override public void mouseExited(MouseEvent e) {}
    };

    lobbi() {
        try {
            buttonstart = ImageIO.read(lobbi.class.getResource("/levels.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        setSize(1920, 1080); //размер окна и название
        setTitle("Red ball 4");

        addMouseListener(ml); //слушать мышку
        setVisible(true);
        toFront();
    }

    public void paint(Graphics g) {      //нарисовать кнопки и фон оранжевый
        super.paint(g);
        g.setColor(new Color(60, 40, 0));
        g.fillRect(0, 0, 1920, 1080);

        g.drawImage(buttonstart, 0, 0, 1920, 1080, this);
    }

    public static void main(String[] args) {
        new lobbi();
    }
}