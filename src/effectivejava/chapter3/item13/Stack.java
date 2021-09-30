package effectivejava.chapter3.item13;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack implements Cloneable {
	private Object[] elements;
	private int size = 0;
	private static final int DEFAULT_INITIAL_CAPACITY = 16;
	
	public Stack() {
		this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
	}
	
	public void push(Object e) {
		ensureCapacity();
		elements[size++] = e;
	}
	
	public Object pop() {
		if(size == 0) {
			throw new EmptyStackException();
		}
		Object result = elements[--size];
		elements[size] = null;//다 쓴 참조객체 해제
		return result;
	} 

	//원소를 위한 공간을 적어도 하나 이상 확보한다.
	private void ensureCapacity() {
		if(elements.length == size){
			elements = Arrays.copyOf(elements, 2 * size + 1);
		}
		
	}
	
	
	//super.clone 만 호출하게 된다면 가변객체인 elements가 같은 배열을 참조하게 된다.
	//원본이나 복제본 중 하나를 수정하면 다른 하나도 수정되어 불변식을 해치게 된다.
	//따라서 elements또한 clone을 재귀적으로 호출해주어야 한다.
	@Override
	public Stack clone(){
		try {
			Stack result = (Stack) super.clone();
			result.elements = elements.clone(); //Object[] 로 형변환 할 필요는 없다. 배열의 clone은 런타임 타입과 컴파일타임 타입 모두가 원본 배열과 똑같은 배열을 반환한다.
			//위 코드에서
			//Cloneable 아키텍처는 '가변 객체를 참조하는 필드는 final로 선언하라'는 일반 용법과 출동한다.
			//그래서 복제할 수 있는 클래스를 만들기 위해 일부 필드에서 final 한정자를 제거해야 할 수도 있다.
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
	
	
}
