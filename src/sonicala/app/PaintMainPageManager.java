package sonicala.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sonicala.controller.MainPageController;
import sonicala.model.painter.BackCanvasPainter;
import sonicala.model.painter.ForwardCanvasPainter;

public class PaintMainPageManager {
	
	private ScheduledExecutorService paintBackService;
	private ScheduledExecutorService paintForwardService;
	
	private BackCanvasPainter[] backPainter;
	private ForwardCanvasPainter[] forwardPainter;
	
	public PaintMainPageManager(AnalyzeDataManager dataManager, MainPageController controller) {
		paintBackService = Executors.newScheduledThreadPool(Constants.PAINT_BACK_THREAD_QUANTITY+5);
		paintForwardService = Executors.newScheduledThreadPool(1);
		backPainter = new BackCanvasPainter[Constants.PAINT_BACK_THREAD_QUANTITY];
		forwardPainter = new ForwardCanvasPainter[Constants.PAINT_FORWARD_THREAD_QUANTITY];
		for(int i=0;i<backPainter.length;i++) {
			backPainter[i] = new BackCanvasPainter(dataManager,controller);
			backPainter[i].setNumber(i);
		}
		for(int i=0;i<forwardPainter.length;i++) {
			forwardPainter[i] = new ForwardCanvasPainter(dataManager,controller);
			forwardPainter[i].setNumber(i);
		}
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
		for(int i=0;i<Constants.PAINT_FORWARD_THREAD_QUANTITY;i++) {
			paintForwardService.scheduleAtFixedRate(
				forwardPainter[i]::paint,
				Constants.PAINT_FORWARD_START_TIME 
					+ i*Constants.PAINT_FORWARD_FREQUENCY, 
				Constants.PAINT_FORWARD_FREQUENCY*Constants.PAINT_FORWARD_THREAD_QUANTITY, 
				TimeUnit.MILLISECONDS);
		}
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
}
