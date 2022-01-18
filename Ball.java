

import java.awt.Color;

public class Ball extends Polkadot {
	
	// private field variables
	private double dx;       
	private double dy;

	// constructors
	public Ball() {
		super(400, 400, 50, Color.BLACK);
		dx = 0;          
		dy = 0;
	}
	public Ball(double x, double y, double dia, Color c) {
		super(x, y, dia, c);
		dx = Math.random()* 12 - 6;
		dy = Math.random() * 12 - 6;
	}
	
	// getter methods
 	public double getdx() {
		return dx;
	}
	public double getdy() {
		return dy;
	}

	// stter methods
	public void setdx(double x) {
		dx = x;
	}
	public void setdy(double y) {
		dy = y;
	}  

	// other methods
	public void move(double rightEdge, double bottomEdge) {
		setX(getX()+ dx);                 
		setY(getY()+ dy+3);
	   
		/*
		if(getX() >= rightEdge - getRadius()) {
			setX(rightEdge - getRadius());
			dx = dx * -1; 
		}
      
		if(getY() >= bottomEdge-getRadius()) {
			setY(bottomEdge-getRadius());

			dy = dy * -1;
		} 
		*/
	}
}