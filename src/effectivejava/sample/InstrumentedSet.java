package effectivejava.sample;

import java.util.Collection;
import java.util.Set;

public class InstrumentedSet<E> extends ForwardingSet<E> {

	private int addCount = 0;
	
	public InstrumentedSet(Set<E> s) {
		super(s);
	}

	@Override public boolean add(E e) {
		System.out.println("InstrumentedSet add call >> addCount++");
		addCount++;
		return super.add(e);
	}
	
	@Override public boolean addAll(Collection<? extends E> c) {
		System.out.println("InstrumentedSet addAll call >> addCount += c.size++");
		addCount += c.size();
		return super.addAll(c);
	}
	
	public int getAddCount() {
		return addCount;
	}
	
}
