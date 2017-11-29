package sonicala.model.painter;

import javafx.scene.paint.Color;
import sonicala.app.Constants;
import sonicala.model.parts.PitchRing;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.TrailingCircleOnPole;

public class PitchRingPainter {
	
	private TrailingCircleOnPole circle;
	private PolarCanvas currentCanvas;
	private double currentRadius;
	
	public PitchRingPainter() {
		circle = new TrailingCircleOnPole();
		Color color = new Color(
			Constants.PITCH_RING_COLOR_R,
			Constants.PITCH_RING_COLOR_G,
			Constants.PITCH_RING_COLOR_B,
			Constants.PITCH_RING_COLOR_A);
		circle.setMainPaint(color);
		circle.setAfterImageColor(color);
		circle.setAfterImageOpaque(
				Constants.PITCH_RING_AFTERIMAGE_START_OPAQUE,
				Constants.PITCH_RING_AFTERIMAGE_END_OPAQUE);
	}
	
	public void paint(PitchRing ring) {
		double powerRate = culcPowerRate(ring.getR());
		double prevPowerRate = culcPowerRate(ring.getPrevR())
				*Constants.PITCH_RING_AFTERIMAGE_END_RADIUS_RATE;
		double theta = culcTheta(ring.getY());
		double prevTheta = (prevPowerRate==0)? theta : culcTheta(ring.getPrevX());
		circle.paint(
				currentRadius, theta,
				Constants.PITCH_RING_RADIUS_MAX * powerRate, 
				Constants.PITCH_RING_WIDTH_MAX * powerRate, 
				Constants.PITCH_RING_SHINE_MAX * powerRate, 
				currentRadius, 
				Math.max(Math.min(prevTheta,theta+Constants.PITCH_RING_AFTERIMAGE_MAX_DEGREE)
						,theta-Constants.PITCH_RING_AFTERIMAGE_MAX_DEGREE),
				Constants.PITCH_RING_RADIUS_MAX * prevPowerRate, 
				Constants.PITCH_RING_WIDTH_MAX * prevPowerRate, 
				Constants.PITCH_RING_SHINE_MAX * prevPowerRate, 
				20, currentCanvas);
	}
	
	public void setCurrentCanvas(PolarCanvas canvas) {
		currentCanvas = canvas;
		double windowShortLength = Math.min(
				currentCanvas.getWidth()/2,
				currentCanvas.getHeight()/2);
		currentRadius = windowShortLength*Constants.SCALE_RING_POSITION_RATE;
	}
	
	private double culcTheta(double x) {
		return x * (-30);
	}
	
	private double culcPowerRate(double r) {
		double rate = Math.max(Math.min(
				(r-Constants.PITCH_RING_LOUDNESS_MIN)
				/(Constants.PITCH_RING_LOUDNESS_MAX
						-Constants.PITCH_RING_LOUDNESS_MIN), 1), 0);
		return 1 - Math.pow(1-rate,Constants.PITCH_RING_POWER_FILTER);
	}
}
