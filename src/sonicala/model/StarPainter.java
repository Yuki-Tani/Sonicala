package sonicala.model;

import javafx.scene.paint.Color;
import sonicala.app.Constants;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.CircleOnPole;

public class StarPainter {

	private CircleOnPole circle;
	
	private MusicTime currentTime;
	private PolarCanvas currentCanvas;
	private int starSpaceRange;
	
	private double STAR_SIDE_TIME;
	private double currentSpaceRadius;
	
	public StarPainter() {
		circle = new CircleOnPole();
		currentTime = new MusicTime(0);
		currentCanvas = new PolarCanvas(0,0);
		STAR_SIDE_TIME = Constants.STAR_LIFE / Constants.STAR_SPACE_VISIBLE_RATE;
		currentSpaceRadius = 0;
		starSpaceRange = Constants.STAR_SPACE_END_DEGREE - Constants.STAR_SPACE_START_DEGREE;
	}
	
	public void paint(Star star) {
		double x = star.getX(currentTime);
		double r = calcR(x);
		double theta = star.getY()/12000.0
				*starSpaceRange +Constants.STAR_SPACE_START_DEGREE;
		double size = Constants.STAR_STANDARD_SIZE * STAR_SIDE_TIME / (STAR_SIDE_TIME - x);
		setStarColor(star.getZ());
		circle.paint(r, theta, size, currentCanvas);
	}
	
	public void setCurrentTime(MusicTime time) {
		currentTime = time;
	}
	
	public void setCurrentCanvas(PolarCanvas canvas) {
		currentCanvas = canvas;
		double windowLength = Math.sqrt(
				Math.pow(currentCanvas.getWidth()/2.0, 2)
				+Math.pow(currentCanvas.getHeight()/2.0, 2));
		currentSpaceRadius = windowLength;
	}
	
	private void setStarColor(double z) {
		double power = Math.max(Math.min(
				1.0/(Constants.STAR_POWER_MAX-Constants.STAR_POWER_MIN) 
				* (z-Constants.STAR_POWER_MIN),1),0);
		power = Math.pow(power, Constants.STAR_POWER_FILTER);
		circle.setPaint(new Color(
				Constants.STAR_COLOR_R + power * Constants.STAR_COLOR_POW_R,
				Constants.STAR_COLOR_G + power * Constants.STAR_COLOR_POW_G, 
				Constants.STAR_COLOR_B + power * Constants.STAR_COLOR_POW_B,
				Constants.STAR_COLOR_A + power * Constants.STAR_COLOR_POW_A
				));
	}
	
	private double calcR(double starX) {
		return currentSpaceRadius
				* (1.0 - (Constants.STAR_LIFE - starX) / (STAR_SIDE_TIME - starX));
	}
	
}
