package sonicala.model;

import javafx.scene.paint.Color;
import sonicala.app.Constants;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.ShineRingOnPole;

public class ScaleRingPainter {
	
	private ShineRingOnPole circle;
	private PolarCanvas currentCanvas;
	private double currentRadius;
	
	public ScaleRingPainter() {
		circle = new ShineRingOnPole();
		setScaleRingColor();
	}
	
	public void paint(ScaleRing scaleRing) {
		circle.paint(
			0, 
			0,
			currentRadius,
			Constants.SCALE_RING_WIDTH, 
			Constants.SCALE_RING_BRIGHTNESS_WIDTH, 
			currentCanvas
		);
	}

	public void setCurrentCanvas(PolarCanvas canvas) {
		currentCanvas = canvas;
		double windowShortLength = Math.min(
				currentCanvas.getWidth()/2,
				currentCanvas.getHeight()/2);
		currentRadius = windowShortLength*Constants.SCALE_RING_POSITION_RATE;
	}
	
	private void setScaleRingColor() {
		circle.setPaint(new Color(
				Constants.SCALE_RING_COLOR_R,
				Constants.SCALE_RING_COLOR_G, 
				Constants.SCALE_RING_COLOR_B,
				Constants.SCALE_RING_COLOR_A));
	}
	
}
