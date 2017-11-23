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
//		System.out.println("analyze");
		MusicTime time = musicSound.getPosition();
		Spectrum spectrum = musicSound.getSpectrum();
//System.out.println("sec:"+time.getAbsoluteSeconds());
//System.out.println("spectrum:"+(spectrum!=null));
		// starLineの作成
		StarLine starLine = new StarLine(spectrum, time);
//System.out.println("starLineSize:"+starLine.size());
		
		box.updateMusicTime(time);
		box.updateStarSpace(starLine);
		
		// パイプ状態の算出
		// 楽譜状態の算出
		// 判定円の算出
		// 
		
		//Pitch pitch = voiceSound.getPitch();
		//Loudness loudness = voiceSound.getLoudness();
	}
}
