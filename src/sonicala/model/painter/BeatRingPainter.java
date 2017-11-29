package sonicala.model.painter;

import javafx.scene.paint.Color;
import sonicala.app.Constants;
import sonicala.model.data.MusicTime;
import sonicala.model.parts.BeatRing;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.ShineRingOnPole;

public class BeatRingPainter {
	private ShineRingOnPole ring;
	private PolarCanvas currentCanvas;
	private MusicTime inTime;
	
	private double currentStartPosition;
	private double rCof;
	private double minCof;
	
	public BeatRingPainter() {
		ring = new ShineRingOnPole();
		ring.setPaint(new Color(
				Constants.BEAT_RING_COLOR_R,
				Constants.BEAT_RING_COLOR_G,
				Constants.BEAT_RING_COLOR_B,
				Constants.BEAT_RING_COLOR_A
				));
		rCof = (Constants.SCALE_RING_POSITION_RATE - Constants.NOTE_START_POSITION_RATE)
				/ (Constants.SCALE_RING_POSITION_RATE * Constants.NOTE_FUTURE_IN_TIME);
		minCof = rCof * Constants.NOTE_START_POSITION_RATE * 0.1;
	}
	
	public void paint(BeatRing beatRing) {
		double x = beatRing.getX(inTime);
		double radius = culcR(x);
		double width = culcW(x, beatRing.getY());
		ring.paint(0, 0, radius, width, 0, currentCanvas);
	}
	
	public void setTime(MusicTime inTime) {
		this.inTime = inTime;
	}
	
	public void setCurrentCanvas(PolarCanvas canvas) {
		currentCanvas = canvas;
		double windowShortLength = Math.min(
				currentCanvas.getWidth()/2,
				currentCanvas.getHeight()/2);
		currentStartPosition = windowShortLength*Constants.NOTE_START_POSITION_RATE;
	}
	
	private double culcR(double x) {
		return currentStartPosition / Math.max(1 - rCof * (-x) , minCof);
	}
	
	private double culcW(double x, double y) {
		return y * Constants.BEAT_RING_STANDARD_SIZE / Math.max(1 - rCof * (-x), minCof);
	}
}
