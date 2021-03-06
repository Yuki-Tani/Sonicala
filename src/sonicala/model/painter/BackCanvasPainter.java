package sonicala.model.painter;

import sonicala.app.AnalyzeDataManager;
import sonicala.app.Sonicala;
import sonicala.controller.MainPageController;
import sonicala.model.data.AnalyzedDataBox;
import sonicala.view.element.PolarCanvas;

public class BackCanvasPainter extends CanvasPainter{
	
	private AnalyzeDataManager dataManager;
	private MainPageController controller;
	private StarPainter starPainter;
	private int number;
	
	ForwardCanvasPainter fp;
	
	public BackCanvasPainter(AnalyzeDataManager dataManager, MainPageController controller) {
		starPainter = new StarPainter();
		this.controller = controller;
		this.dataManager = dataManager;
		number = 0;
		fp = new ForwardCanvasPainter(dataManager, controller);
	}
	
	public void paint(){
//		System.out.println("paint back: "+number);
		AnalyzedDataBox box = dataManager.getAnalyzedDataBox();
		PolarCanvas canvas = new PolarCanvas(Sonicala.getWidth(),Sonicala.getHeight());
		starPainter.setCurrentTime(box.getCurrentTime());
		starPainter.setCurrentCanvas(canvas);
		box.operateStars((star)->starPainter.paint(star));
		
		fp.paint(canvas);
		
		controller.setBackCanvas(canvas);
	}
	
	public void setNumber(int i) {
		number = i;
		
		fp.setNumber(i);
	}
}
