package sonicala.model;

public abstract class VoiceRecorder {
	
	public VoiceRecorder() {
	}
	
	public abstract void start();
	public abstract void pose();
	public abstract void stop();
	public abstract VoiceSound getLastVoiceSound();
}
