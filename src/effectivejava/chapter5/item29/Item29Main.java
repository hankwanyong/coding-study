package effectivejava.chapter5.item29;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Item29Main {
	/*
	 * 이왕이면 제네릭 타입으로 만들라
	 * 
	 *  1. 일반 클래스를 제네릭 클랙스로 만드는 첫 단계는 클래스 선언에 타입에 매개변수를 추가하는 것이다.
	 *                  
	 */
	
	public static class Stack<E> {
		private E[] elements;
		private int size = 0;
		private static final int DEFAULT_INITIAL_CAPACITY = 16;
		
		//배열을 사용한 코드를 제네릭으로 만드는 방법 1
		@SuppressWarnings("unchecked")
		public Stack() {
			// elements = new E[DEFAULT_INITIAL_CAPACITY];
			elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
			
		}
		
		public void push(E e) {
			ensureCapacity();
			elements[size++] = e;
		}
		
		public E pop() {
			if(size == 0) 
				throw new EmptyStackException();
			//배열을 사용한 코드를 제네릭으로 만드는 방법2
			// E result = elements[--size];
			@SuppressWarnings("unchecked") E result =(E) elements[--size];
			
			elements[size] = null;
			return result;
		}
		
		public boolean isEmpty() {
			return size==0;
		}
		
		private void ensureCapacity() {
			if(elements.length == size) 
				elements = Arrays.copyOf(elements, 2 * size +1);
		}
				
	}
	
	public static void main(String[] args) {
		Stack<String> stack = new Stack<>();
		for(String arg : args)
			stack.push(arg);
		while(!stack.isEmpty()) {
			System.out.println(stack.pop().toUpperCase());
		}

	}
    
	/*
	 * 핵심정리
	 * 클라이언트에서 직접 형변환해야 하는 타입보다 제네릭 타입이 더 안전하고 쓰기 편하다.
	 * 새로운 타입을 설계할 때는 형변환 없어도 사용할 수 있도록 하라.
	 * 그렇게 하려면 제네릭 타입으로 만들어야 할 경우가 많다.
	 */
	


}
