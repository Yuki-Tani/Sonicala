package sonicala.view.element;


import javafx.geometry.VPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

public class GuideMessage {
	
	private static final double BACK_MARGIN = 3;
	private Paint paint;
	private Paint back;
	private Font font;
	
	public GuideMessage() {
		paint = Color.WHITE;
		back = new Color(0,0,0,1);
		font = Font.font(20);
	}
	
	public void setFont(double size) {
		font = Font.font(size);
	}
	
	public void paint(double x, double y, double width, String message, Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(back);
		gc.fillRect(x-BACK_MARGIN, y-BACK_MARGIN, 
				width+BACK_MARGIN*2, font.getSize()*1.2+BACK_MARGIN*2);
		gc.setFill(paint);
		gc.setFont(font);
		gc.setTextAlign(TextAlignment.LEFT);
		gc.setTextBaseline(VPos.TOP);
		gc.fillText(message, x, y, width);
	}
}
