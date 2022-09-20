package snake;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;

public class Panel extends JPanel implements ActionListener {

    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 40;
    static final int GAME_UNITS = (SCREEN_WIDTH*SCREEN_HEIGHT)/UNIT_SIZE;
    static final int DELAY = 150;
    final int[] x = new int[GAME_UNITS];
    final int[] y = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    Random random;

    // Creates blank Panel for drawing and starts game
    Panel() {
        random = new Random();
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setFocusable(true);
        this.addKeyListener(new myKeyAdapter());
        startGame();
    }
    // Creates random apple and starts the timer
    public void startGame() {
        newApple();
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
                g.setColor(Color.GRAY);
                g.drawLine(i*UNIT_SIZE, 0, i*UNIT_SIZE, SCREEN_HEIGHT);
                g.drawLine(0, i*UNIT_SIZE, SCREEN_WIDTH, i*UNIT_SIZE); 
            }
            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for(int i=0; i<bodyParts; i++) {
                if(i == 0) {
                    g.setColor(Color.GREEN);
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
                else {
                    g.setColor(new Color(45, 180, 0));
                    g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
        }
        else {
            gameOver(g);
        }
    }
    // Randomly creates apple, if an apple in the body of the snake, a new apple is again created
    public void newApple() {
        appleX = random.nextInt((int)(SCREEN_WIDTH/UNIT_SIZE))*UNIT_SIZE;
        appleY = random.nextInt((int)(SCREEN_HEIGHT/UNIT_SIZE))*UNIT_SIZE;
        for(int i = 0; i<bodyParts; i++) {
            if(x[i] == appleX && y[i] == appleY) {
                newApple();
            }
        }
    }
    // Moves the snake
    public void move() {
        for(int i=bodyParts; i>0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        switch(direction) {
            case 'U': 
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D': 
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L': 
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R': 
                x[0] = x[0] + UNIT_SIZE;
                break;

        }

    }
    // Is checking to see if the apple has been eaten, if so, increases body, makes new apple, and increases score
    public void checkApple() {
        if(x[0] == appleX && y[0] == appleY) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }
    // Checks to see if there is any collisions between snake and its own body and the snake and the border, if so, end game
    public void checkCollisions() {
        // Body collision
        for(int i=bodyParts; i>0; i--) {
            if((x[0] == x[i]) && (y[0] == y[i])) {
                running = false;
            }
        }
        // Left side collision
        if(x[0] < 0) {
            running = false;
        }
        // Right side collision
        if(x[0] >= SCREEN_WIDTH) {
            running = false;
        }
        // Top collision
        if(y[0] < 0) {
            running = false;
        }
        // Bottom collision
        if(y[0] >= SCREEN_HEIGHT) {
            running = false;
        }
        if(!running) {
            timer.stop();
        }

    }
    // All of the end game messages
    public void gameOver(Graphics g) {
        //Score
        g.setColor(Color.white);
		g.setFont( new Font("Ink Free",Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: "+applesEaten, (SCREEN_WIDTH - metrics1.stringWidth("Score: "+applesEaten))/2, g.getFont().getSize());
        // Restart interface
        g.setColor(Color.white);
		g.setFont( new Font("Ink Free",Font.BOLD, 20));
		FontMetrics metrics3 = getFontMetrics(g.getFont());
	    g.drawString("Press R to restart!", (SCREEN_WIDTH - metrics3.stringWidth("Press R to restart"))/2, 2*SCREEN_HEIGHT/3);
		//Game win screen
        if(bodyParts >= 225) {
            g.setColor(Color.green);
		    g.setFont( new Font("Ink Free",Font.BOLD, 40));
		    FontMetrics metrics2 = getFontMetrics(g.getFont());
		    g.drawString("GAME OVER, YOU WIN!", (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER, YOU WIN!"))/2, SCREEN_HEIGHT/2);
        }
        // Game lose screen
        else {
            g.setColor(Color.red);
		    g.setFont( new Font("Ink Free",Font.BOLD, 40));
		    FontMetrics metrics2 = getFontMetrics(g.getFont());
		    g.drawString("GAME OVER, YOU LOSE!", (SCREEN_WIDTH - metrics2.stringWidth("GAME OVER, YOU LOSE!"))/2, SCREEN_HEIGHT/2);
        }
    }
    // Override method that allows for movement and constant running of game
    @Override
    public void actionPerformed(ActionEvent e) {
        if(running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();  
    }
    // This mini-class allows for the listening of keys to check for important keystrokes
    public class myKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch(e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_A:
                    if(direction != 'R'){
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_D:
                    if(direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_W:
                    if(direction != 'D'){
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_S:
                    if(direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_R:
                    new Frame();
                    startGame();
                    break;
            }
        }
    }
}
