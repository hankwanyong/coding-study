package effectivejava.v2.chapter2.item7;

import java.util.Arrays;
import java.util.EmptyStackException;

/*
 * 아이템7 다 쓴 객체 참조를 해제하라
 * 
 * 메모리 누수는 겉으로 잘 드러나지 않아 시스템에 수년간 잠복하는 사례도 있다.
 * 이런 누수는 철저한 코드 리뷰나 힙 프로파일러 같은 디버길 도구를 동원해야만 발견되기도 한다.
 * 그래서 이런 종류의 문제는 예방법을 익혀두는 것이 매우 중요하다.
 * 
 * 프로프알러 > 이클립스도 제공하는것 같다. (출처 : https://expert0226.tistory.com/296)
 * jvm monitor
 * 
 * 
 * 
 */
public class Item7Main {


	public class Stack {
		private Object[] elements;
		private int size = 0;
		private static final int DEFAULT_INITIAL_CAPACITY = 16;
		
		public Stack() {
			elements =  new Object[DEFAULT_INITIAL_CAPACITY];
		}
		
		public void push(Object e) {
			ensureCapacity();
			elements[size++] = e;
		}
		
		public Object pop() {
			if(size == 0) throw new EmptyStackException();
			Object result = elements[--size];
//			elements[size] = null;	// 다 쓴 참조 해제
			return result;
		}
		
		
		private void ensureCapacity() {
			if(elements.length == size)
				elements = Arrays.copyOf(elements, 2 * size +1);
		}
	}
	
}
