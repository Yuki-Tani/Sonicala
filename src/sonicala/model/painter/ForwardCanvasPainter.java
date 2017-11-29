package sonicala.model.painter;

import sonicala.app.AnalyzeDataManager;
import sonicala.controller.MainPageController;
import sonicala.model.data.AnalyzedDataBox;
import sonicala.view.element.PolarCanvas;

public class ForwardCanvasPainter{
	
	private AnalyzeDataManager dataManager;
	private MainPageController controller;
	private int number;
	
	private ScaleRingPainter scaleRingPainter;
	private ShaftPainter shaftPainter;
	private PitchRingPainter pitchRingPainter;
	private NotePainter notePainter;
	private BeatRingPainter beatRingPainter;
	private SentencePainter sentencePainter;
	
	public ForwardCanvasPainter(AnalyzeDataManager dataManager, MainPageController controller) {
		this.controller = controller;
		this.dataManager = dataManager;
		number = 0;
		scaleRingPainter = new ScaleRingPainter();
		shaftPainter = new ShaftPainter();
		notePainter = new NotePainter();
		pitchRingPainter = new PitchRingPainter();
		beatRingPainter = new BeatRingPainter();
		sentencePainter = new SentencePainter();
	}
	
	public void paint(PolarCanvas canvas){
//		System.out.println("paint forward: "+number);
		AnalyzedDataBox box = dataManager.getAnalyzedDataBox();
		//PolarCanvas canvas = new PolarCanvas(Sonicala.getWidth(),Sonicala.getHeight());
		shaftPainter.setCurrentCanvas(canvas);
		shaftPainter.paint(box.getShafts());
		
		scaleRingPainter.setCurrentCanvas(canvas);
		scaleRingPainter.paint(box.getScaleRing());
		
		notePainter.setCurrentCanvas(canvas);
		notePainter.setTime(box.getInTime(), box.getCurrentTime(), box.getOutTime());
		box.operateNotes(notePainter::paint);
		
		beatRingPainter.setCurrentCanvas(canvas);
		beatRingPainter.setTime(box.getInTime());
		box.operateBeatRing(beatRingPainter::paint);
		
		pitchRingPainter.setCurrentCanvas(canvas);
		pitchRingPainter.paint(box.getPitchRing());
		
		sentencePainter.setCurrentCanvas(canvas);
		box.operateSentenceSpace(sentencePainter::paint);
//		System.out.println(">>>>> "+box.getPitchRing().getR());
		//controller.setForwardCanvas(canvas);
	}
	
	public void setNumber(int i) {
		number = i;
	}
}
