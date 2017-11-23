package sonicala.model;

public abstract class MusicPlayer {
	protected Music music;
	
	public MusicPlayer(Music music) {
		this.music = music;
	}
	
	public abstract void play();
	public abstract void pose();
	public abstract void stop();
	public abstract MusicSound getLastMusicSound();
		
}
