package sonicala.model.painter;

import javafx.scene.paint.Color;
import sonicala.app.Constants;
import sonicala.model.data.MusicTime;
import sonicala.model.parts.Star;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.CircleOnPole;

public class StarPainter {

	private CircleOnPole circle;
	
	private MusicTime currentTime;
	private PolarCanvas currentCanvas;
	private double rangeUnit;
	private double STAR_SIDE_TIME;
	private double currentSpaceRadius;

	// 計算高速化のための分離変数
	private double rangeGap;
	private double sizeCoefficient;
	private double colorCoefficient;
	private double colorGap;
	
	public StarPainter() {
		circle = new CircleOnPole();
		currentTime = new MusicTime(0);
		currentCanvas = new PolarCanvas(0,0);
		STAR_SIDE_TIME = Constants.STAR_LIFE / Constants.STAR_SPACE_VISIBLE_RATE;
		currentSpaceRadius = 0;
		rangeUnit = (Constants.STAR_SPACE_END_DEGREE - Constants.STAR_SPACE_START_DEGREE)
				/ (Constants.STAR_SPACE_END_FREQUENCY - Constants.STAR_SPACE_START_FREQUENCY);
		rangeGap = - Constants.STAR_SPACE_START_FREQUENCY * rangeUnit + Constants.STAR_SPACE_START_DEGREE;
		sizeCoefficient = Constants.STAR_STANDARD_SIZE * STAR_SIDE_TIME;
		colorCoefficient = 1.0/(Constants.STAR_POWER_MAX-Constants.STAR_POWER_MIN);
		colorGap = -Constants.STAR_POWER_MIN * colorCoefficient;
	}
	
	public void paint(Star star) {
		double x = -star.getX(currentTime);
		double r = calcR(x);
		double theta = star.getY()*rangeUnit + rangeGap;
		double size =  sizeCoefficient / (STAR_SIDE_TIME - x);
		setStarColor(star.getZ());
		circle.paint(r, theta, size, currentCanvas);
//		System.out.println(">"+star.getY()+" ("+theta+") >"+star.getZ());
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
		double power = Math.max(Math.min(colorCoefficient * z + colorGap,1),0);
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
