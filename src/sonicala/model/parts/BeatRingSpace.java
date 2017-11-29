package sonicala.model.parts;

import java.util.LinkedList;
import java.util.function.Consumer;

import sonicala.model.data.MusicTime;

@SuppressWarnings("serial")
public class BeatRingSpace extends LinkedList<BeatRing>{
	
	@Override
	public synchronized boolean add(BeatRing beatRing) {
		super.add(beatRing);
		return true;
	}
	
	public synchronized void sendAllNewBeatRing(MusicTime inTime, BeatRingSpace space) {
//		System.out.println("start send");
		BeatRing top = peek();
		while(top != null && top.shoudAppear(inTime)) {
			space.add(poll());
			top = peek();
		}
//		System.out.println("end send");
	}
	
	public synchronized void removeAllOldBeatRing(MusicTime outTime) {
//		System.out.println("start remove");
		BeatRing top = peek();
		while(top != null && top.shoudDisappear(outTime)) {
			poll();
			top = peek();
		}
//		System.out.println("end remove");
	}
	
	public synchronized void forEach(Consumer<? super BeatRing> action) {
		for (BeatRing beatRing : this) {
			action.accept(beatRing);
		}
	}
}
