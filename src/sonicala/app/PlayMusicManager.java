package sonicala.app;

import sonicala.model.player.Le4MusicPlayer;
import sonicala.model.player.MusicPlayer;
import sonicala.model.player.MusicSound;
import sonicala.model.song.Song;

public class PlayMusicManager {
	
	private MusicPlayer player;
	
	public PlayMusicManager(Song song) {
		player = new Le4MusicPlayer(song.getMusic());
		// Le4MusicPlayerはスレッドを内臓
	}
	
	public void start() {
		player.play();
	}
	
	public void pose() {
		player.pose();
	}
	
	public void stop() {
		player.stop();
	}
	
	public MusicSound getMusicSound() {
		return player.getLastMusicSound();
	}
}
