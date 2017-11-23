package sonicala.model;

import java.nio.file.Path;

public class Song {
	private SongInformation songInfo;
	private Music music;
	private Score score;
	private Lyrics lyrics;

	public Song(Path infoFile) {
		this.songInfo = new SongInformation(infoFile);
	}
	
	public SongInformation getInformation() {
		if(songInfo==null) 
			songInfo = new SongInformation(songInfo.getInformationFileURL());
		return songInfo;
	}
	
	public Music getMusic() {
		if(music==null) 
			music = new Music(songInfo.getMusicFileURL());
		return music;
	}
	
	public Score getScore() {
		if(score==null) 
			score = new Score(songInfo.getScoreFileURL());
		return score;
	}
	
	public Lyrics getLyrics() {
		if(lyrics==null) 
			lyrics = new Lyrics(songInfo.getLyricsFileURL());
		return lyrics;
	}
}
