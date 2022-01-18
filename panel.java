// import
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class panel extends JPanel {
	// constants
	private static final int FRAME = 800;
	private static final Color BACKGROUND = new Color(102, 205, 170);
	
	// private fields
	private BufferedImage myImage;
	private Graphics myBuffer;
	private Timer t;
	private Ball b;
	private Bumper top, bottom, prize;
	private JLabel theScore;
	private JButton reset;
	private int score, move = 1, drawer = 0;
	private boolean dead = false;
	
	
	
	// constructor
	public panel() {
		myImage =  new BufferedImage(FRAME, FRAME, BufferedImage.TYPE_INT_RGB);
        myBuffer = myImage.getGraphics();
        myBuffer.setColor(BACKGROUND);
        myBuffer.fillRect(0, 0, FRAME,FRAME);
        
        // ball
        b = new Ball(400, 400, 30, Color.black);
        b.setdx(0);
        b.setdy(0);
        
        // bumpers
        int gap = (int)(b.getDiameter()+Math.random()*300);
        int y = (int)(700-Math.random()*750);
        
        bottom = new Bumper(600, y, 30, 800-y,Color.green);
        top = new Bumper(600, 0, 30, y-gap,Color.green);
        prize = new Bumper(600, y-gap, 30, gap, Color.black);
        
        // label
        theScore = new JLabel("Score: " + score);
        
        // button
        reset = new JButton("RESET");
        reset.setBackground(Color.orange.brighter());
        reset.addActionListener(new Listener2());
        
        // mouse
        addMouseListener(new Mouse());
        
        // adding components
        add(theScore);
        add(reset);
        
        t = new Timer(5, new Listener1());
        t.start();  
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
	}
	
	public class Listener1 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			myBuffer.setColor(BACKGROUND); 
			myBuffer.fillRect(0,0,FRAME,FRAME);
			
			// adding gravity
			b.setdy(b.getdy()+0.15);
			
			// ball
			b.move(FRAME, FRAME);
			b.draw(myBuffer);
			
			// bottom
			bottom.draw(myBuffer);
			bottom.setX(bottom.getX()-move);
			
			// top
			top.draw(myBuffer);
			top.setX(top.getX()-move);
			
			// prize
			//prize.draw(myBuffer);
			prize.setX(prize.getX()-move);
			
			// collisions
			if(top.inBumper(b)) {
				move = 0;
			}
			if(bottom.inBumper(b)) {
				move = 0;
			}
			if(prize.inBumper(b)) {
				score++;
			}
			
			// update
			theScore.setText("Score: " + (int) score/59);
			drawer++;
			
			if(drawer == 350) {
				drawObstacles();
				drawer = 0;
			}
						
			repaint();
		}
	}
	
	public class Listener2 implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			
			b.setX(400);
			b.setY(400);
			b.setdx(0);
			b.setdy(0);
			score = 0;
			move = 1;
		}
	}	
	
	public class Mouse extends MouseAdapter {
		public void mousePressed (MouseEvent e) {
			b.setdy(b.getdy() - 6);
		}
	}
	
	public void drawObstacles() {
        int gap = (int)(b.getDiameter()+Math.random()*300);
        int y = (int)(700-Math.random()*750);
        
        bottom = new Bumper(600, y, 30, 800-y,Color.green);
        top = new Bumper(600, 0, 30, 10+(y-gap),Color.green);
        prize = new Bumper(600, y-gap, 30, gap, Color.black);
	}
}
