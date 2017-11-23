package sonicala.view.element;

import javafx.scene.canvas.Canvas;

public class PolarCanvas extends Canvas{
	
	private double centerX,centerY;
	
	public PolarCanvas(double width, double height) {
		super(width,height);
		centerX = width/2;
		centerY = height/2;
	}
	
	public double getX(double r, double theta) {
		return centerX + r*Math.cos(Math.toRadians(theta));
	}
	
	public double getY(double r, double theta) {
		return centerY - r*Math.sin(Math.toRadians(theta));
	}
}
