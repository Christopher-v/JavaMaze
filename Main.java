import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyListener.*;
import java.awt.Component.*;

public class Main extends JPanel{


    public static void main(String[] args) {


        RecursiveMaze recursiveMaze = new RecursiveMaze(20,20);

        //Vi fylder først wall[][] med @'er og benytter derefter makeWay til at lave en vej igennem disse.
        recursiveMaze.initLabyrinth();
        recursiveMaze.lavvej();
        recursiveMaze.printlab();
        GUI gui = new GUI(recursiveMaze.wall);
        JFrame f= new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setTitle("Labyrinth");
        f.setSize(400,400);
        f.add(gui);
        f.setVisible(true);
        f.setFocusable(true);
        //kan ikke få keylisteren fra GUI klassen til intiere
      //  f.addKeyListener(this);

    }


}

