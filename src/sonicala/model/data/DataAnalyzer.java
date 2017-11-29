package sonicala.model.data;

import sonicala.model.parts.BeatRingSpace;
import sonicala.model.parts.NoteSpace;
import sonicala.model.parts.StarLine;
import sonicala.model.player.MusicSound;
import sonicala.model.recorder.VoiceSound;
import sonicala.model.song.Song;

public class DataAnalyzer {
	
	private Song song;
	private AnalyzedDataBox box;
	private NoteSpace noteDataLine;
	private BeatRingSpace beatRing;
	
	public DataAnalyzer(Song song, AnalyzedDataBox box) {
		this.song = song;
		this.box = box;
		this.noteDataLine = song.getScore().getNoteLine();
		this.beatRing = song.getScore().getBeatRingLine();
	}
	
	/**
	 * 与えられたmusicSoundとvoiceSoundを使ってboxを更新
	 * @param musicSound
	 * @param voiceSound
	 */
	public void analyze(MusicSound musicSound, VoiceSound voiceSound) {
//System.out.println(">>>start analyze");
		MusicTime time = musicSound.getPosition();
		Spectrum musicSpectrum = musicSound.getSpectrum();
		StarLine starLine = new StarLine(musicSpectrum, time);
		
		Pitch voicePitch = voiceSound.getPitch();
		Loudness voiceLoud = voiceSound.getLoudness();
		
		box.updateMusicTime(time);
		box.updateStarSpace(starLine);
		box.updateNoteSpace(noteDataLine);
		box.updateBeatRingSpace(beatRing);
		box.updatePitchRing(voicePitch, voiceLoud);
		
		box.updateScaleRing(false,1.0);
//System.out.println(">>>end analyze");
	}
}
