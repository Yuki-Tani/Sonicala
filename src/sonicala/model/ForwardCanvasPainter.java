package sonicala.model;

import sonicala.app.AnalyzeDataManager;
import sonicala.app.Sonicala;
import sonicala.controller.MainPageController;
import sonicala.view.element.PolarCanvas;

public class ForwardCanvasPainter{
	
	private AnalyzeDataManager dataManager;
	private MainPageController controller;
	private int number;
	
	private ScaleRingPainter scaleRingPainter;
	private ShaftPainter shaftPainter;
	private PitchRingPainter pitchRingPainter;
	
	public ForwardCanvasPainter(AnalyzeDataManager dataManager, MainPageController controller) {
		this.controller = controller;
		this.dataManager = dataManager;
		number = 0;
		scaleRingPainter = new ScaleRingPainter();
		shaftPainter = new ShaftPainter();
		pitchRingPainter = new PitchRingPainter();
	}
	
	public void paint(PolarCanvas canvas){
		System.out.println("paint forward: "+number);
		AnalyzedDataBox box = dataManager.getAnalyzedDataBox();
		//PolarCanvas canvas = new PolarCanvas(Sonicala.getWidth(),Sonicala.getHeight());
		shaftPainter.setCurrentCanvas(canvas);
		shaftPainter.paint(box.getShafts());
		
		scaleRingPainter.setCurrentCanvas(canvas);
		scaleRingPainter.paint(box.getScaleRing());
		
		pitchRingPainter.setCurrentCanvas(canvas);
		pitchRingPainter.paint(box.getPitchRing());
		System.out.println(">>>>> "+box.getPitchRing().getR());
		//controller.setForwardCanvas(canvas);
	}
	
	public void setNumber(int i) {
		number = i;
	}
}
