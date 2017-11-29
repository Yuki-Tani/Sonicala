package sonicala.model.parts;

import java.util.LinkedList;
import java.util.function.Consumer;

import sonicala.model.data.MusicTime;

@SuppressWarnings("serial")
public class NoteSpace extends LinkedList<Note>{

	@Override
	public synchronized boolean add(Note note) {
		super.add(note);
		return true;
	}
	
	public synchronized void sendAllNewNote(MusicTime inTime, NoteSpace space) {
//		System.out.println("start send");
		Note top = peek();
		while(top != null && top.shoudAppear(inTime)) {
			space.add(poll());
			top = peek();
		}
//		System.out.println("end send");
	}
	
	public synchronized void removeAllOldNote(MusicTime outTime) {
//		System.out.println("start remove");
		Note top = peek();
		while(top != null && top.shoudDisappear(outTime)) {
			poll();
			top = peek();
		}
//		System.out.println("end remove");
	}
	
	public synchronized void forEach(Consumer<? super Note> action) {
		for (Note note : this) {
			action.accept(note);
		}
	}
}
