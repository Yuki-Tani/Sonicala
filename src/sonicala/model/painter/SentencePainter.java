package sonicala.model.painter;

import sonicala.app.Constants;
import sonicala.model.parts.Sentence;
import sonicala.view.element.GuideMessage;
import sonicala.view.element.PolarCanvas;

public class SentencePainter {
	
	private GuideMessage message;
	private PolarCanvas currentCanvas;
	
	public SentencePainter() {
		message = new GuideMessage();
	}
	
	public void paint(Sentence sentence) {
//		System.out.println("paint note");
		String text = sentence.getString();
		double width = Math.min(text.length() * Constants.LYRICS_FONT_SIZE,
				Constants.LYRICS_ZONE_WIDTH);
		double lyricsZoneX = currentCanvas.getWidth() - width
				-Constants.LYRICS_ZONE_MARGIN_X;
		double lyricsZoneY = currentCanvas.getHeight() - Constants.LYRICS_FONT_SIZE
				-Constants.LYRICS_ZONE_MARGIN_Y;
		message.setFont(Constants.LYRICS_FONT_SIZE);
		message.paint(lyricsZoneX, lyricsZoneY, 
				width, 
				text, currentCanvas);
//		System.out.println("end paint note");
	}
	
	public void setCurrentCanvas(PolarCanvas canvas) {
		currentCanvas = canvas;
	}
}
