package sonicala.model.painter;

import javafx.scene.paint.Color;
import sonicala.app.Constants;
import sonicala.model.data.MusicTime;
import sonicala.model.parts.Note;
import sonicala.view.element.PolarCanvas;
import sonicala.view.element.ShineFanOnPole;

public class NotePainter {
	
	private ShineFanOnPole fan;
	private PolarCanvas currentCanvas;
	private MusicTime currentInTime;
	private MusicTime currentTime;
	private MusicTime currentOutTime;
	
	private double currentStartPosition;
	private double rCof;
	private double minCof;
	private double halfRange;
	
	public NotePainter() {
		fan = new ShineFanOnPole();
		fan.setFramePaint(new Color(
				Constants.NOTE_FRAME_COLOR_R,
				Constants.NOTE_FRAME_COLOR_G,
				Constants.NOTE_FRAME_COLOR_B,
				Constants.NOTE_FRAME_COLOR_A));
		fan.setinnerPaint(new Color(
				Constants.NOTE_INNER_COLOR_R,
				Constants.NOTE_INNER_COLOR_G,
				Constants.NOTE_INNER_COLOR_B,
				Constants.NOTE_INNER_COLOR_A));
		
		
		rCof = (Constants.SCALE_RING_POSITION_RATE - Constants.NOTE_START_POSITION_RATE)
				/ (Constants.SCALE_RING_POSITION_RATE * Constants.NOTE_FUTURE_IN_TIME);
		minCof = rCof * Constants.NOTE_START_POSITION_RATE * 0.1;
		halfRange = Constants.NOTE_SINGLE_RANGE / 2;
	}
	
	public void paint(Note note) {
//		System.out.println("paint note");
		double theta = culcTheta(note.getY());
		double startR = culcR(note.getStartX(currentInTime,currentOutTime));
		double endR = culcR(note.getEndX(currentInTime,currentOutTime));
		fan.paint(endR, startR, theta-halfRange, theta+halfRange, 
				Constants.NOTE_FRAME_WIDTH, 
				Constants.NOTE_FRAME_SHINE_RATE, 
				currentCanvas);
//		System.out.println("end paint note");
	}
	
	public void setTime(MusicTime inTime, MusicTime currentTime, MusicTime outTime) {
		this.currentInTime = inTime;
		this.currentTime = currentTime;
		this.currentOutTime = outTime;
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
	
	private double culcTheta(double y) {
		return y * (-30);
	}
}
