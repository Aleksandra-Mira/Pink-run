package cwiczenia;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class Okno extends JFrame implements KeyListener {
    Thread drawObstacles, refresh, drawPerson;
    int highScore = 0;
    int xPerson = 200;
    int yPerson = 400;
    int xObstacle = 801;
    int obstacle = 0;
    int time = 10;
    int time2 = 3;
    int refreshRate = 38;
    int direction = -1;
    int yourScore = 0;
    boolean play = true;

    public Okno() {
        readHighScore();
        initWindow();
    }

    void initWindow() {
        setSize(800, 800);
        setTitle("Pink RUN!");
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255, 178, 201));
        setLocationRelativeTo(null);
        addKeyListener(this);
        drawObstacles();
        refreshScreen();
    }

    public void paint(Graphics g) {
        super.paint(g);
        draw(g);
    }

    private void draw(Graphics g) {
        Person p = new Person();
        p.draw(g, xPerson, yPerson);
        Rainbow r = new Rainbow(this.xObstacle, 407);
        Glitter glitter = new Glitter(this.xObstacle, 420);
        Heart heart = new Heart();
        switch (obstacle) {
            case 0:
                r.draw(g);
                break;
            case 1:
                glitter.draw(g);
                break;
            default:
                heart.draw(g, this.xObstacle, 380);
        }
    }

    private void drawObstacles() {
        drawObstacles = new Thread(() -> {
            while (play) {
                this.xObstacle--;
                if (this.xObstacle == -90) {
                    yourScore++;
                    System.out.println(yourScore);
                    obstacle = (int) (Math.random() * 3);
                    switch (obstacle) {
                        case 0:
                            time = 10;
                            refreshRate = 40;
                            break;
                        case 1:
                            time = 6;
                            refreshRate = 20;
                            break;
                        default:
                            time = 4;
                            refreshRate = 10;
                    }
                    this.xObstacle = 801;
                }
                try {
                    Thread.sleep(time);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        drawObstacles.start();
    }

    private void refreshScreen() {
        refresh = new Thread(() -> {
            while (play) {
                gameOver();
                repaint();
                try {
                    Thread.sleep(refreshRate);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        refresh.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            direction = -1;
            jumpAnimation();

        }
    }

    public void jumpAnimation() {
        drawPerson = new Thread(() -> {
            while (play) {
                this.yPerson += direction;
                if (yPerson == 200) {
                    direction = 1;
                    time2 = 10;
                }
                if (yPerson == 400) {
                    direction = 0;
                    time2 = 3;
                    break;
                }

                try {
                    Thread.sleep(time2);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        drawPerson.start();
    }

    public void gameOver() {
        if (obstacle == 0) {
            if (((xPerson + 60) >= xObstacle) && (xPerson <= (xObstacle + 90))) {
                if ((yPerson + 60) > 410) {
                    play = false;
                    System.out.println("Twoj wynik: "+yourScore);
                    saveHighScore();
                }
            }
        }
        if (obstacle == 1) {
            if (((xPerson + 60) >= (xObstacle - 40)) && (xPerson <= (xObstacle + 60))) {
                if ((yPerson + 60) > 360) {
                    play = false;
                    System.out.println("Twoj wynik: "+yourScore);
                    saveHighScore();
                }
            }
        }
        if (obstacle == 2) {
            if (((xPerson + 60) >= xObstacle) && (xPerson <= (xObstacle + 60))) {
                if ((yPerson + 60) > 400) {
                    play = false;
                    System.out.println("Twoj wynik: "+yourScore);
                    saveHighScore();
                }
            }
        }
    }

    public void readHighScore() {
        try {
            FileInputStream fis = new FileInputStream("HighScore.txt");
            int wrt;
            while ((wrt = fis.read()) != -1) {
                highScore += wrt;
            }
            System.out.println("High score: "+highScore);
        } catch (IOException e) {

        }
    }

    public void saveHighScore() {
        if (highScore < yourScore) {
            try {
                FileOutputStream fos = new FileOutputStream("HighScore.txt");
                fos.write(yourScore);
                for (int i = 1; (yourScore << (i * 8)) == 0; ) {
                    fos.write(yourScore << (i * 8));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
