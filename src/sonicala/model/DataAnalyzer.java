package sonicala.model;

public class DataAnalyzer {
	
	private Song song;
	private AnalyzedDataBox box;
	
	public DataAnalyzer(Song song, AnalyzedDataBox box) {
		this.song = song;
		this.box = box;
	}
	
	/**
	 * 与えられたmusicSoundとvoiceSoundを使ってboxを更新
	 * @param musicSound
	 * @param voiceSound
	 */
	public void analyze(MusicSound musicSound, VoiceSound voiceSound) {
		MusicTime time = musicSound.getPosition();
		Spectrum musicSpectrum = musicSound.getSpectrum();
		StarLine starLine = new StarLine(musicSpectrum, time);
		
		Pitch voicePitch = voiceSound.getPitch();
		Loudness voiceLoud = voiceSound.getLoudness();
		
		box.updateMusicTime(time);
		box.updateStarSpace(starLine);
		
		box.updatePitchRing(voicePitch, voiceLoud);
		
		box.updateScaleRing(false,1.0);
	}
}
