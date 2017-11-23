package sonicala.app;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import sonicala.model.AnalyzedDataBox;
import sonicala.model.DataAnalyzer;
import sonicala.model.Song;

public class AnalyzeDataManager {
	
	private ScheduledExecutorService analyzeService;
	private DataAnalyzer analyzer;
	private AnalyzedDataBox box;
	private PlayMusicManager musicManager;
	private RecordVoiceManager voiceManager;
	
	public AnalyzeDataManager(Song song, PlayMusicManager musicManager, RecordVoiceManager voiceManager) {
		analyzeService = Executors.newScheduledThreadPool(
				Constants.ANALYZE_DATA_MANAGER_THREAD_QUANTITY);
		box = new AnalyzedDataBox();
		analyzer = new DataAnalyzer(song,box);
		this.musicManager = musicManager;
		this.voiceManager = voiceManager;
	}
	
	public void start() {
		// analyzeService 1フレームにつき、１つの有効データ群が生成される
		analyzeService.scheduleAtFixedRate(
				this::analyze,
				Constants.ANALYZE_DATA_START_TIME, 
				Constants.ANALYZE_DATA_FREQUENCY, 
				TimeUnit.MILLISECONDS);
	}
	
	public void pose() {
		analyzeService.shutdown();
	}
	
	public void stop() {
		analyzeService.shutdown();
	}
	
	public AnalyzedDataBox getAnalyzedDataBox() {
		return box;
	}
	
	private void analyze() {
//		System.out.println("startAnalyze");
		analyzer.analyze(
				musicManager.getMusicSound(),
				voiceManager.getVoiceSound());
	}
}
