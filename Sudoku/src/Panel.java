import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class Panel extends JPanel implements ActionListener{

    static final int[][] CLEAR_SLATE = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                                        {0, 0, 0, 0, 0, 0, 0, 0, 0},};
    static final int SCREEN_WIDTH = 720;
    static final int SCREEN_HEIGHT = 720;
    static final int UNIT_SIZE = 80;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 100;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    boolean running = false;
    Timer timer;
    

    Panel() {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.white);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        startGame();
    }
    public void startGame() {
        running = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }
    // This paints everything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }
    // This is what is being painted
    public void draw(Graphics g) {
        if(running) {
            for(int i=0; i<SCREEN_HEIGHT/UNIT_SIZE; i++) {
                g.setColor(Color.black);
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE); 
            }
            for(int j=0; j<3; j++) {
                g.setColor(Color.black);
                g.fillRect(j*UNIT_SIZE*3, 0, 4, SCREEN_HEIGHT);
                g.fillRect(0, j*UNIT_SIZE*3, SCREEN_HEIGHT, 4);
            }
            g.setColor(Color.black);
		    g.setFont( new Font("Ink Free",Font.BOLD, 40));
		    FontMetrics metrics1 = getFontMetrics(g.getFont());
            for(int h=0; h<x.length; h++) {
                g.drawString("H", (h*GAME_UNITS), (h*GAME_UNITS));
            }
        }
    }
    public void newGameBoard() {

    }
    public void checkValue() {

    }
    // Override method that allows for movement and constant running of game
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            checkValue();
        }
        repaint();  
    }
    // This mini-class allows for the listening of keys to check for important keystrokes
    public class myKeyAdapter extends KeyAdapter {
        //@Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_1:
            }
        }
    }
}
