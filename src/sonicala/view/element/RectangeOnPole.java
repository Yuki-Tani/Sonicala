package sonicala.view.element;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class RectangeOnPole {
	
	private static final int RR = 0, RT = 1, LR = 2, LT = 3;
	private Paint paint;
	
	public RectangeOnPole() {
		paint = Color.BLUE;
	}
	
	public void setPaint(Paint paint) {
		this.paint = paint;
	}
	
	public void paint(
			double beginR, 
			double beginW, 
			double endR, 
			double endW, 
			double theta, 
			PolarCanvas canvas) 
	{
		GraphicsContext gc = canvas.getGraphicsContext2D();
		double[] begin = culcEdgeEnd(beginR,theta,beginW);
		double[] end = culcEdgeEnd(endR,theta,endW);
		double[] x = {
				canvas.getX(begin[RR], begin[RT]),canvas.getX(end[RR], end[RT]),
				canvas.getX(end[LR], end[LT]),canvas.getX(begin[LR], begin[LT])
				};
		double[] y = {
				canvas.getY(begin[RR], begin[RT]),canvas.getY(end[RR], end[RT]),
				canvas.getY(end[LR], end[LT]),canvas.getY(begin[LR], begin[LT])
				};
		gc.setFill(paint);
		gc.fillPolygon(x, y, 4);
	}
	
	private double[] culcEdgeEnd(double r, double theta, double width) {
		double[] ans = new double[4];
		double edgeR = Math.sqrt(Math.pow(r, 2)+Math.pow(width/2.0, 2));
		double dTheta = Math.toDegrees(Math.atan(width/2.0/r));
		ans[RR] = edgeR;
		ans[RT] = theta - dTheta;
		ans[LR] = edgeR;
		ans[LT] = theta + dTheta;
		return ans;
	}
}
