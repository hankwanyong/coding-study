package effectivejava.chapter4.item20;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

public class Item20Main {

	/*
	 * Item20 추상 클래스보다는 인터페이스를 우선하라
	 * 
	 * - 자바가 제공하는 다중 구현 메커니즘은 인터페이스, 추상 클래스가 있다.
	 * 
	 * - 자바 8부터 인터페이스도 디폴트 메서드를 제공할 수 있다.
	 * 		> default키워드로 선언되면 메소드가 구현될 수 있다.
	 * 		> 인터페이스가 변경이 되면, 인터페이스를 구현하는 모든 클래스들이 해당 메소드를 구현해야 하는 문제가 있다. 이런 문제를 해결하기 위하여 인터페이스에 메소드를 구현해 놓을 수 있도록 하였다.
	 * 		> equals와 hashCode같은 Object의 메서드는 디폴트 메서드로 제공해서는 안 된다.
	 * - 인터페이스는 인스턴스 필드를 가질 수 없고 public이 아닌 정적 멤버도 가질 수 없다(단, private 정적 메서드는 예외)
	 * 
	 * - extends는 클래스 한 개만 상속 받을 수 있다. 
	 * 		> 추상 클래스 방식은 새로운 타입을 정의하는데 커다란 제약을 안게 되는셈이다.
	 * - implements는 여러개 사용 가능하다.
	 * 		> 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해넣을 수 있다.
	 * 		> 인터페이스는 믹스인(mixin)정의에 안성맞춤이다. (주된 타입 외에도 특정 선택적 행위를 제공 ex - Comparable)
	 * 		> 인터페이스로는 계층구조가 없는 타입 프로임워크를 만들 수 있다.
	 * 
	 * - 골격 구현 클래스 (템플릿 메서드 패턴)
	 * 		> ex) AbstractSet Class ( HashSet extends AbstractSet )
	 * 		public abstract class AbstractSet<E> extends AbstractCollection<E> implements Set<E>
	 * 		public interface Set<E> extends Collection<E>
	 * 		public abstract class AbstractCollection<E> implements Collection<E>
	 * 		public interface Collection<E> extends Iterable<E>
	 * 		public interface Iterable<T>
	 * 
	 * 		> ex) AbstractList Class ( ArrayList extends AbstractList )
	 * 		public abstract class AbstractList<E> extends AbstractCollection<E> implements List<E>
	 * 		public interface List<E> extends Collection<E>
	 * 		public abstract class AbstractCollection<E> implements Collection<E>
	 * 		public interface Collection<E> extends Iterable<E>
	 * 		public interface Iterable<T>
	 * 
	 */
	
	// List 구현체를 반환하는 정적 팩터리 메서드로, AbstractList 골격 구현으로 활용했다.
	static List<Integer> intArrayAsList(int[] a) {
		Objects.requireNonNull(a);
		
		return new AbstractList<Integer>() {

			@Override
			public Integer get(int i) {
				return a[i];
			}
			
			@Override
			public Integer set(int i, Integer val) {
				int oldVal = a[i];
				a[i] = val;
				return oldVal;
			}

			@Override
			public int size() {
				return a.length;
			}
			
		};
	}
	
	// 일반적으로 다중 구현용 타입으로는 인터페이스가 가장 적합하다.
	// 복잡한 인터페이스라면 구현하는 수고를 덜어주는 골격 구현을 함께 제공하는 방법을 고려해보자.
	// 골격 구현운 '가능한 한' 인터페이스의 디폴트 메서드로 제공하여 그 인터페이스를 구현한 모든 곳에서 활용하도록 하는 것이 좋다.
	// '가능한 한'이라고 한 이유는, 인터페이스에 걸려있는 구현상의 제약 때문에 골격 구현을 추상 클래스로 제공하는 경우가 더 흔하기 때문이다.
	
	

	public static void main(String[] args) {
		
		StarbucksExpand starbucks = new StarbucksExpand();
		
		starbucks.order();		
		starbucks.chooseCoffee();
		starbucks.pay();
		
		System.out.println("====================================");
		
		NewStartbucks newStarbucks = new NewStartbucks();
		newStarbucks.order();		
		newStarbucks.chooseCoffee();
		newStarbucks.pay();
		newStarbucks.printReceipt();
	}
}
