package sonicala.model.parts;

import java.util.LinkedList;
import java.util.function.Consumer;

import sonicala.app.Constants;

@SuppressWarnings("serial")
public class StarSpace extends LinkedList<StarLine>{
	
	private int starLineQuantity;
	
	public StarSpace() {this(Constants.STAR_LINE_QUANTITY_IN_STAR_SPACE);}
	public StarSpace(int starLineQuantity) {
		this.starLineQuantity = starLineQuantity;
	}
	
	/**
	 * add new StarLine to StarSpace and remove one StarLine when it is full.
	 * this operation is synchronized.
	 * @param starLine
	 */
	@Override
	public synchronized boolean add(StarLine starLine) {
		super.add(starLine);
		if(size()>starLineQuantity) {
			remove(0);
		}
		return true;
	}

	@Override
	public synchronized void forEach(Consumer<? super StarLine> action) {
		for (StarLine line : this) {
			action.accept(line);
		}
	}
}
