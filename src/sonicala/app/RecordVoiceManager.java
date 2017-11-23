package sonicala.app;

import sonicala.model.Le4VoiceRecorder;
import sonicala.model.VoiceRecorder;
import sonicala.model.VoiceSound;

public class RecordVoiceManager {
	
	private VoiceRecorder recorder;
	
	public RecordVoiceManager() {
		recorder = new Le4VoiceRecorder();
		// Le4VoiceRecorderはthreadを内臓
	}
	
	public void start() {
		recorder.start();
	}
	
	public void pose() {
		recorder.stop();
	}
	
	public void stop() {
		recorder.stop();
	}
	
	public VoiceSound getVoiceSound() {
		return recorder.getLastVoiceSound();
	}
}
