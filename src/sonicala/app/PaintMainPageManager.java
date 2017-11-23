package sonicala.app;

import java.util.Arrays;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.scene.canvas.Canvas;
import sonicala.controller.MainPageController;
import sonicala.model.BackCanvasPainter;
import sonicala.model.ForwardCanvasPainter;

public class PaintMainPageManager {
	
	private ScheduledExecutorService paintBackService;
	private ScheduledExecutorService paintForwardService;
	
	private MainPageController controller;
	private BackCanvasPainter[] backPainter;
	private ForwardCanvasPainter forwardPainter;
	
	public PaintMainPageManager(AnalyzeDataManager dataManager, MainPageController controller) {
		paintBackService = Executors.newScheduledThreadPool(Constants.PAINT_BACK_THREAD_QUANTITY+5);
		paintForwardService = Executors.newScheduledThreadPool(1);
		this.controller = controller;
		backPainter = new BackCanvasPainter[Constants.PAINT_BACK_THREAD_QUANTITY];
		for(int i=0;i<backPainter.length;i++) {
			backPainter[i] = new BackCanvasPainter(dataManager,controller);
			backPainter[i].setNumber(i);
		}
		forwardPainter = new ForwardCanvasPainter();
	}
	
	public void start() {
		
		for(int i=0;i<Constants.PAINT_BACK_THREAD_QUANTITY;i++) {
			paintBackService.scheduleAtFixedRate(
				backPainter[i]::paint,
				Constants.PAINT_BACK_START_TIME 
					+ i*Constants.PAINT_BACK_FREQUENCY, 
				Constants.PAINT_BACK_FREQUENCY*Constants.PAINT_BACK_THREAD_QUANTITY, 
				TimeUnit.MILLISECONDS);
		}
/*		
		paintForwardService.scheduleAtFixedRate(
				this::paintForward,
				Constants.PAINT_FORWARD_START_TIME, 
				Constants.PAINT_FORWARD_FREQUENCY, 
				TimeUnit.MILLISECONDS);
*/
	}
	
	public void pose() {
		paintBackService.shutdown();
		paintForwardService.shutdown();
	}
	
	public void stop() {
		paintBackService.shutdown();
		paintForwardService.shutdown();
	}
/*	
	private void paintForward() {
		Canvas canvas = forwardPainter.paint(dataManager.getAnalyzedDataBox());
		controller.setForwardCanvas(canvas);
	}
*/
}
