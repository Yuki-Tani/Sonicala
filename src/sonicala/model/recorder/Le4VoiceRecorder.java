package sonicala.model.recorder;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Mixer;

import jp.ac.kyoto_u.kuis.le4music.Recorder;
import sonicala.app.Constants;

public class Le4VoiceRecorder extends VoiceRecorder{
	private Recorder recorder;
	private VoiceSound lastSound;
	
	public Le4VoiceRecorder() {
		super();
		
		try {
			recorder = Recorder.builder()
					.mixer(AudioSystem.getMixerInfo()[Constants.RECORDER_MIXIER_INDEX])
					.daemon()
					.build();
		} catch (Exception e) {
			System.out.println("!! Recorder Setup Error[VoiceRecorder]");
			e.printStackTrace();
		}
		recorder.addAudioFrameListener(
				(frame, position) -> {
					//MusicTime voicePos = new MusicTime(1.0*position/recorder.getSampleRate());
					lastSound = new VoiceSound(frame,getSampleRate());
					//System.out.println(">>>"+frame.length+" pos:"+position);
				}
		);
	}
	
	@Override
	public void start() {
		recorder.start();
	}
	
	@Override
	public void pose() {
		recorder.stop();
	}

	@Override
	public void stop() {
		recorder.stop();
	}

	@Override
	public VoiceSound getLastVoiceSound() {
		if(lastSound == null) {
			//MusicTime musicPos = new MusicTime(0);
			lastSound = new VoiceSound(new double[10],getSampleRate());
		}
		return lastSound;
	}
	
	public int getSampleRate() {
		return (int)recorder.getSampleRate();
	}

}
