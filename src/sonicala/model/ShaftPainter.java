package sonicala.model;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.RadialGradient;
import javafx.scene.paint.Stop;
import sonicala.app.Constants;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.ShineLine;

public class ShaftPainter {
	
	private ShineLine line;
	private PolarCanvas currentCanvas;
	private double currentCenterX, currentCenterY;
	private double currentWindowLength;
	private int shaftRange;
	private RadialGradient gradient;
	private Stop centerStop, maxStop, endStop;
	
	public ShaftPainter() {
		line = new ShineLine();
		shaftRange = Constants.SHAFT_END_DEGREE - Constants.SHAFT_START_DEGREE;
		centerStop = new Stop(0,new Color(1,1,1,0));
		maxStop = new Stop(Constants.SHAFT_END_POSITION_RATE,new Color(1,1,1,1));
		endStop = new Stop(1, new Color(1,1,1,0));
	}
	
	public void paint(Shaft[] shafts) {
		double startR = currentWindowLength * Constants.SHAFT_START_POSITION_RATE;
		double endR = currentWindowLength;
		double dth = shaftRange / shafts.length;
		double frontSize = Constants.SHAFT_STANDARD_SIZE / Constants.SHAFT_START_POSITION_RATE;
		gradient = new RadialGradient(
				0, 0,
				currentCenterX, currentCenterY, endR, 
				false, CycleMethod.NO_CYCLE, 
				centerStop, maxStop, endStop);
		setShaftPaint();
		for(int i=0; i< shafts.length; i++) {
			line.paint(
				startR, Constants.SHAFT_STANDARD_SIZE, 
				endR, frontSize, 
				Constants.SHAFT_SHINE_RATE, 
				Constants.SHAFT_START_DEGREE + dth * i ,
				currentCanvas);
		}
	}

	public void setCurrentCanvas(PolarCanvas canvas) {
		currentCanvas = canvas;
		currentCenterX = currentCanvas.getWidth()/2.0;
		currentCenterY = currentCanvas.getHeight()/2.0;
		double windowLength = Math.sqrt(
				Math.pow(currentCenterX, 2)
				+Math.pow(currentCenterY, 2));
		currentWindowLength = windowLength;
	}
	
	private void setShaftPaint() {
		line.setSmokePaint(new Color(
				Constants.SHAFT_SMOKE_COLOR_R,
				Constants.SHAFT_SMOKE_COLOR_G, 
				Constants.SHAFT_SMOKE_COLOR_B,
				Constants.SHAFT_SMOKE_COLOR_A));
		line.setShinePaint(gradient);
	}
}
