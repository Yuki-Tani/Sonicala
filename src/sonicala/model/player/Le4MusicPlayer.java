package sonicala.model.player;

import javax.sound.sampled.AudioSystem;

import jp.ac.kyoto_u.kuis.le4music.Player;
import sonicala.app.Constants;
import sonicala.model.data.MusicTime;
import sonicala.model.song.Music;

/**
 * モノラルのみ・jre1.8でビルドする必要あり
 * @author Le4
 *
 */
public class Le4MusicPlayer extends MusicPlayer{
	private Player player;
	private MusicSound lastSound;
	
	public Le4MusicPlayer(Music music) {
		super(music);
		//Arrays.stream(AudioSystem.getMixerInfo()).forEach(t -> System.out.println(">>>" +  t));
		try {
			player = Player.builder(music.getFile())
					.mixer(AudioSystem.getMixerInfo()[Constants.PLAYER_MIXIER_INDEX])
					.daemon()
					.build();
			player.addAudioFrameListener(
					(frame, position) -> {
						MusicTime musicPos = new MusicTime(1.0*position/music.getSampleRate());
						lastSound = new MusicSound(frame,music.getSampleRate(),musicPos);
						//System.out.println(">>>"+frame.length+" pos:"+position);
					}
			);
		} catch (Exception e) {
			System.out.println("!! Player Setup Error[MusicPlayer]");
			e.printStackTrace();
		}
	}
	
	public void play() {
		player.start();
	}
	
	public void pose() {
		player.stop();
	}
	
	public void stop() {
		player.stop();
	};
	
	public MusicSound getLastMusicSound() {
		if(lastSound == null) {
			MusicTime musicPos = new MusicTime(0);
			lastSound = new MusicSound(new double[10],music.getSampleRate(),musicPos);
		}
		return lastSound;
	}
}
