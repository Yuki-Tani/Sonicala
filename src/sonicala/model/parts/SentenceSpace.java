package sonicala.model.parts;

import java.util.LinkedList;
import java.util.function.Consumer;

import sonicala.model.data.MusicTime;

@SuppressWarnings("serial")
public class SentenceSpace extends LinkedList<Sentence>{
	@Override
	public synchronized boolean add(Sentence sentence) {
		super.add(sentence);
		return true;
	}
	
	public synchronized void sendAllNewNote(MusicTime inTime, SentenceSpace space) {
//		System.out.println("start send");
		Sentence top = peek();
		while(top != null && top.shoudAppear(inTime)) {
			space.add(poll());
			top = peek();
		}
//		System.out.println("end send");
	}
	
	public synchronized void removeAllOldNote(MusicTime outTime) {
//		System.out.println("start remove");
		Sentence top = peek();
		while(top != null && top.shoudDisappear(outTime)) {
			poll();
			top = peek();
		}
//		System.out.println("end remove");
	}
	
	public synchronized void forEach(Consumer<? super Sentence> action) {
		for (Sentence sentence: this) {
			action.accept(sentence);
		}
	}
}
