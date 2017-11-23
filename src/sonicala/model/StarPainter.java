package sonicala.model;

import javafx.scene.paint.Color;
import sonicala.app.Constants;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.RadiatedCircle;

public class StarPainter {

	private RadiatedCircle circle;
	
	private MusicTime currentTime;
	private PolarCanvas currentCanvas;
	private int starSpaceRange;
	
	private double STAR_SIDE_TIME;
	private double currentSpaceRadius;
	
	public StarPainter() {
		circle = new RadiatedCircle();
		currentTime = new MusicTime(0);
		currentCanvas = new PolarCanvas(0,0);
		STAR_SIDE_TIME = Constants.STAR_LIFE 
				* Constants.STAR_SPACE_DISTANCE_RATE
				/ (Constants.STAR_SPACE_VISIBLE_RATE + Constants.STAR_SPACE_DISTANCE_RATE - 1.0);
		currentSpaceRadius = 0;
		starSpaceRange = Constants.STAR_SPACE_END_DEGREE - Constants.STAR_SPACE_START_DEGREE;
	}
	
	public void paint(Star star) {
		double r = calcR(star.getX(currentTime));
		//System.out.println(r);
		double theta = star.getY()/16000.0
				*starSpaceRange +Constants.STAR_SPACE_START_DEGREE;
		setStarColor(star.getZ());
		circle.paint(r, theta, Constants.STAR_STANDARD_SIZE, currentCanvas);
	}
	
	public void setCurrentTime(MusicTime time) {
		currentTime = time;
	}
	
	public void setCurrentCanvas(PolarCanvas canvas) {
		currentCanvas = canvas;
		double windowLength = Math.sqrt(
				Math.pow(currentCanvas.getWidth()/2.0, 2)
				+Math.pow(currentCanvas.getHeight()/2.0, 2));
		currentSpaceRadius = windowLength*Constants.STAR_SPACE_VISIBLE_RATE
				* Constants.STAR_SPACE_DISTANCE_RATE;
	}
	
	private void setStarColor(double z) {
		double power = Math.max(Math.min(
				1.0/(Constants.STAR_POWER_MAX-Constants.STAR_POWER_MIN) 
				* (z-Constants.STAR_POWER_MIN),1),0);
		circle.setPaint(new Color(
				Constants.STAR_COLOR_R,
				Constants.STAR_COLOR_G, 
				Constants.STAR_COLOR_B,
				power));
	}
	
	private double calcR(double starX) {
		return currentSpaceRadius
				* (1.0 - (Constants.STAR_LIFE - starX) / (STAR_SIDE_TIME - starX));
	}
	
}
