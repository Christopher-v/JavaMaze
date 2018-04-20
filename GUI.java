import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI extends JPanel implements KeyListener {

    public String[][] wall;

    public int x = 5, y = 5;

    public GUI(String[][] walla) {
        this.wall = walla;
    }
    public void paint(Graphics g) {

        super.paint(g);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                if (wall[i][j] == "@") {
                    g.setColor(Color.BLACK);
                    g.fillRect(i * 20, j * 20, 20, 20);
                } else if (wall[i][j] == " ") {
                    g.setColor(Color.YELLOW);
                    g.fillRect(i * 20, j * 20, 20, 20);
                }
            }
        }
        g.setColor(Color.WHITE);
        g.fillOval(x, y, 20, 20);
        repaint();
    }
    //Her er et forsøg på at lave en keylistener som kan styre en oval
    @Override
    public void keyPressed (KeyEvent e){


        int c = e.getKeyCode();
        if (c == KeyEvent.VK_W) {
            y-=4;
        }
        if (c == KeyEvent.VK_S) {
            y+=4;
        }
        if (c == KeyEvent.VK_A) {
            x-=4;
        }
        if (c == KeyEvent.VK_D) {
            x+=4;
        }

    }
    @Override
    public void keyReleased (KeyEvent e){

    }
    @Override
    public void keyTyped (KeyEvent e){


    }


}



