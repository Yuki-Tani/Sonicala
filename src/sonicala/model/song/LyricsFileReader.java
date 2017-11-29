package sonicala.model.song;

import java.nio.file.Path;

import sonicala.model.data.MusicTime;
import sonicala.model.parts.Sentence;
import sonicala.model.parts.SentenceSpace;

public class LyricsFileReader {
	private Path lyricsFile;
	
	public LyricsFileReader(Path lyricsFile) {
		this.lyricsFile = lyricsFile;
	}
	
 // TODO make lyrics file reader
	public SentenceSpace read() {
		
		//test
		SentenceSpace dataLine = new SentenceSpace();
		dataLine.add(new Sentence(new MusicTime(5.0),new MusicTime(10.0),"まわり続ける　歯車には成り下がらない"));
		dataLine.add(new Sentence(new MusicTime(10.0),new MusicTime(15.0),"平均演じる　感情から始まった地獄"));
		dataLine.add(new Sentence(new MusicTime(20.0),new MusicTime(25.0),"遊び半分で　神が導いた"));
		dataLine.add(new Sentence(new MusicTime(25.0),new MusicTime(30.0),"盤上の世界　No No No Game No Life"));
		return dataLine;
	}
}
