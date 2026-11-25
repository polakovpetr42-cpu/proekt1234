import java.awt.*; //рисование
import java.awt.event.*; // клавиши
import java.awt.image.BufferedImage; // тип картинки
import java.io.File; // файл с пк
import javax.imageio.ImageIO; // чтение картинки с файла
import javax.swing.*; // окно и панель с таймером

public class menu extends JFrame { // класс окно
    public BufferedImage fon; //перемннная фона
    public BufferedImage hero;// переменная героя\
    public BufferedImage ball; //переменная шара
    Timer t;
    int heroX = 100;// герой по горизонтали
    int heroY = 930;//  по вертикали(Oy направлена вниз)
    int jumpLeft = 0;// сколько лететь вверх
    boolean onGround = true;// стоит ли на чем то твердом

    // лестницы ихние координаты(x левый край;y	верхний край; w ширина; h высота)
    int p1x = 300,  p1y = 850, p1w = 200, p1h = 20;
    int p2x = 700,  p2y = 700, p2w = 200, p2h = 20;
    int p3x = 1100, p3y = 550, p3w = 200, p3h = 20;

    // шар в конце уровня, ровно на полу
    int ballX = 1700, ballY = 940, ballSize = 150;

    menu() {
        try {
            fon  = ImageIO.read(menu.class.getResource("/fon.jpg"));
            hero = ImageIO.read(menu.class.getResource("/hero.png"));//картинки с пк
            ball = ImageIO.read(menu.class.getResource("/buttonOut.png"));//картинки с пк
        } catch (Exception e) { //если есть ошибка покажи а не игнорируй
            System.out.println("Ошибка загрузки: " + e);
        }

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) { //создана новая панедь чтобы рисовать
                super.paintComponent(g); // залитоь платформу цветом ее фона
                g.drawImage(fon, 0, 0, getWidth(), getHeight(), this); //фон на весь экранфф

                g.setColor(Color.GRAY); //закрашивание платформ серым цветом
                g.fillRect(p1x, p1y, p1w, p1h);
                g.fillRect(p2x, p2y, p2w, p2h);
                g.fillRect(p3x, p3y, p3w, p3h);

                g.drawImage(hero, heroX, heroY + 10, 150, 150, this); // рисуем героя чуть ниже
                g.drawImage(ball, ballX, ballY, ballSize, ballSize, this); // рисуем шар
            }
        };
        panel.setBackground(new Color(8, 15, 45)); // цвет фона темно синий
        panel.setFocusable(true); //реагирует на клавиши

        add(panel); // панель в окно
        setSize(1920, 1080); //размер панели
        setExtendedState(JFrame.MAXIMIZED_BOTH); //на весь экран
        setVisible(true); // панель видима

        t = new Timer(20, e -> { //каждые 20мс выполняй это
            onGround = false; // сбрасываем дабы герой не прыгал с воздуха

            if (jumpLeft > 0) {
                heroY -= 10; jumpLeft--;
            } //прыжок вверх
            else  {
                heroY += 10;
            } // вниз

            step(p1x, p1y, p1w, p1h); //платформы
            step(p2x, p2y, p2w, p2h);
            step(p3x, p3y, p3w, p3h);

            if (heroY > 930) {
                heroY = 930; onGround = true;
            } //если геройц чуть ниже исправляем

            if (heroX + 150 > ballX && heroX < ballX + ballSize
                    && heroY + 160 > ballY && heroY + 10 < ballY + ballSize) {
                t.stop();
                dispose();
                new lobbi();
                return;
            }

            panel.repaint(); // перерисовка
        });
        t.start();//запускаем таймер

        panel.addKeyListener(new KeyListener() { //слушает клаву
            public void keyPressed(KeyEvent e) { //реагирует на зажим отпускание и нажатие
                int k = e.getKeyCode(); //код клавиши
                if (k == KeyEvent.VK_A || k == KeyEvent.VK_LEFT)  heroX -= 30; //движение
                if (k == KeyEvent.VK_D || k == KeyEvent.VK_RIGHT) heroX += 30;
                if (k == KeyEvent.VK_SPACE && onGround) {
                    jumpLeft = 30;
                    onGround = false;
                } //прыжок
                panel.repaint(); //перерисовать
            }
            public void keyReleased(KeyEvent e) {}
            public void keyTyped(KeyEvent e) {}
        });

        panel.requestFocus(); //панель активна
    }

    void step(int x, int y, int w, int h) { //метод проверки платформы принимает 4 числа
        boolean overX = heroX + 150 > x && heroX < x + w; //герой по x на платформе

        if (jumpLeft == 0 && overX //не летим вверх и по x на платформе и ноги героя выше платофрмы(более чем на 30пикселей)
                && heroY + 150 >= y && heroY + 150 <= y + 30) {
            heroY = y - 150; //ставим на платформу
            onGround = true; //стоим на земле
        }

        if (jumpLeft > 0 && overX
                && heroY <= y + h && heroY >= y + h - 30) { //если прыгаем под платформой удаояемся головой и начинаем падать
            jumpLeft = 0;
            heroY = y + h;
        }
    }

    public static void main(String[] args) { new menu(); }
}