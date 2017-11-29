package sonicala.app;

import sonicala.model.recorder.Le4VoiceRecorder;
import sonicala.model.recorder.VoiceRecorder;
import sonicala.model.recorder.VoiceSound;

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
