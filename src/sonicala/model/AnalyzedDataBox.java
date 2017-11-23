package sonicala.model;

import java.util.function.Consumer;

public class AnalyzedDataBox extends DataBox{
	
	private MusicTime time;
	private StarSpace space;
	
	public AnalyzedDataBox() {
		space = new StarSpace();
		time = new MusicTime(0);
	}
	
	public void updateStarSpace(StarLine newStarLine) {
		space.add(newStarLine);
	}
	
	public void updateMusicTime(MusicTime time) {
		this.time = time;
	}
	
	public void operateStars(Consumer<? super Star> operation){
		space.forEach(line -> line.forEach(operation));
	}
	
	public MusicTime getCurrentTime() {
		return time;
	}
}
