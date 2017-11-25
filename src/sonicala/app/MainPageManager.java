package sonicala.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sonicala.controller.MainPageController;
import sonicala.model.Song;
import sonicala.view.element.PolarCanvas;

public class MainPageManager {
	
	private MainPageController controller;
	private ScheduledExecutorService mainService;
	
	private PlayMusicManager playManager;
	private RecordVoiceManager recordManager;
	private AnalyzeDataManager analyzeManager;
	private PaintMainPageManager paintManager;
	
	public MainPageManager(Song song, MainPageController controller) {
		this.controller = controller;
		mainService = Executors.newScheduledThreadPool(1);
		playManager = new PlayMusicManager(song);
		recordManager = new RecordVoiceManager();
		analyzeManager = new AnalyzeDataManager(song,playManager,recordManager);
		paintManager = new PaintMainPageManager(analyzeManager,this.controller);
		// 5秒後に自動スタート
		mainService.schedule(this.controller::startAction, 2, TimeUnit.SECONDS);
	}
	
	public void start() {
		recordManager.start();
		playManager.start();
		analyzeManager.start();
		paintManager.start();
	}
	
	public void pose() {
		recordManager.pose();
		playManager.pose();
		analyzeManager.pose();
		paintManager.pose();
	}
	
	public void stop() {
		recordManager.stop();
		playManager.stop();
		analyzeManager.stop();
		paintManager.stop();
	}
}
