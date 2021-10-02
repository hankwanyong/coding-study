package effectivejava.sample;

import java.util.Collection;
import java.util.HashSet;

/**
 * 잘못된 상위 메서드 재정의
 * @author han
 *
 * @param <E>
 */
public class InstrumentedHashSet_Err<E> extends HashSet<E> {
	// 추가된 원소의 수
	private int addCount = 0;
	
	public InstrumentedHashSet_Err() {}
	
	public InstrumentedHashSet_Err(int initCap, float loadFactor) {
		super(initCap, loadFactor);
	}
	
	@Override public boolean add(E e) {
		System.out.println("InstrumentedHashSet_Err add call >> addCount++");
		addCount++;
		return super.add(e);
	}
	
	@Override public boolean addAll(Collection<? extends E> c) {
		System.out.println("InstrumentedHashSet_Err addAll call >> addCount += c.size++");
		addCount += c.size();
		return super.addAll(c);
	}
	
	public int getAddCount() {
		return addCount;
	}
	
}