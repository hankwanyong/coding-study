package effectivejava.chapter5.item31;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Item31Main {
	/*
	 * 한정적 와일드카드를 사용해 API 유연성을 높이라           
	 */
	
	//pushAll 메서드
	public static class Stack<E> {
		public Stack() {}
		public void push(E e) {}
		@SuppressWarnings("unchecked") 
		public E pop() {
			return (E) new Object();
		}
		public boolean isEmpty() {return  true;}
		
		//와일드카드 타입을 사용하지 않은 메서드
		public void pushAll_1 (Iterable<E> src) {
			for(E e:src) {
				push(e);
			}
		}
		
		//E 생산자 매개변수에 와일드카드 타입 적용
		// E의 Iterable이 아니라 E의 하위 타입의 Iterable
		public void pushAll_2 (Iterable<? extends E> src) {
			if(src != null) {
				for(E e:src) {
					push(e);
				}
			}
		}
		
		//와일드카드 타입을 사용하지 않는 popAll 메서드
		public void popAll_1(Collection<E> dst) {
			while(!isEmpty()) {
				dst.add(pop());
			}
		}
		
		//E 소비자 매개변수에 와일드카드 타입 적용
		public void popAll_2(Collection<? super E> dst) {
			while(!isEmpty()) {
				dst.add(pop());
			}
		}
	}
	
	public static void main(String[] args) {
		
		try {
			Stack<Number> stack = new Stack<Number>();
			Iterable<Integer> a= null;
			//오류
			//stack.pushAll_1(a);
			stack.pushAll_2(a);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		try {
			Stack<Number> numberStack = new Stack();
			Collection<Object> objects = new ArrayList<Object>();
			//type 오류!
			//numberStack.popAll_1(objects);
			//정상
			numberStack.popAll_2(objects);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	/* 유연성을 극대화하려면 원소의 생산자나 소비자용 입력 매개변수에 와일드카드 타입을 사용하라.
	 * 
	 *  PECS : producer-extends, consumer-super
	 *  매개변수화 타입 T가 생산자라면 <? extends T> 를 사용
	 *                  소비자라면 <? super T>를 사용
	 *  반환 타입에는 한정적 와일드카드 타입을 사용하면 안 된다.
	 * 
	 */
	
	
	//생산자 매개변수에 와일드카드 타입적용
	// public Chooser (Collection <? extends T> choices)
	
	// public static <E> Set<E> union(Set<E>, Set<E> s2)
	// -> public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) 
	
	//자바 7까지는 명시적 타입 인수를 사용해야한다.
	// Set<Number> numbers = Union.<Number>union(integer, doubles);
	
	// 메서드 선언데 타입 매개변수가 한 번만 나오면 와일드카드로 대체하라.
	public static <E> void swap1(List<E> list, int i , int j){};
	public static void swap2(List<?> list, int i, int j) {
		//	list.set(i, list.set(j, list.get(i)));
		swapHelper(list, i, j);
	};
	//swap2 : List<?> 에는 null 이외에는 어떤 값도 넣을 수 없다.
	//-> 도우미 메서드를 이용
	
	private static <E> void swapHelper(List<E> list, int i, int j) {
		list.set(i, list.set(j, list.get(i)));
	}
	
	/*
	 *  핵심정리
	 *  와일드카드 타입을 적용하면 API가 훨씬 유연해진다.
	 *  널리 쓰일 라이브러리를 작성한다면 반드시 와일드카드 타입을 적절히 사용해라.
	 *  PECS !!
	 */
   
}
