package effectivejava.chapter10.item73;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

public class Item73Main {

	/*
	 * [아이템73] 추상화 수준에 맞는 예외를 던지라
	 * 
	 * 수행하려는 일과 관련없어 보이는 예외가 튀어나오면 당황스럽다.
	 * 메서드가 저수준 예외를 처리하지 않고 바깥으로 전파해버릴 때 종종 일어난다.
	 * 
	 * -> 상위 계층에서는 저수준 예외를 잡아 자신의 추상화 수준에 맞는 예외로 바꿔 던져야 한다. 
	 *    이를 예외 번역(exception translation)이라 한다.
	 */
	
	/*  코드73-1 예외 번역
	public static E get(int index) {
		ListIterator<E> i = listIterator(index);
		try {
			return i.next();
		} catch(NoSuchElementException e) {
			throw new IndexOutOfBoundsException("인텍스: " + index);
		}
	}
	*/
	
	/*
	 * 예외를 번역할 때, 저수준 예외가 디버깅에 도움이 된다면 예외 연쇄(exception chaining)를 사용하는 게 좋다.
	 * 예외 연쇄란 문제의 근본 원인인 저수준 예외를 고수준 예외에 실어 보내는 방식이다.
	 * try{
	 * 		//저수준 추상화
	 * } catch(LowerLevelException e) {
	 * 	throw new HigherLevelException(e); //저수준 예외를 고수준에 실어 보낸다.
	 * }
	 */
	
	/*
	 * 무턱대고 예외를 전파하는 것보다야 예외 번역이 우수한 방법이지만, 남용해서는 안된다.
	 * 가능하다면 저수준 메서드가 반드시 성공하도록 하여 아래 계층에서는 예외가 발생하지 않도록 하는 것이 최선이다.
	 * 
	 * 만약 아래 계층에서의 예외를 피할 수 없다면, 상위 계층에서 그 예외를 조용히 처리하여 문제를 API호출자에까지
	 * 전파하지 않는 방법도 있다. 이경우 예외는 java.util.logging 같은 적절한 로깅 기능을 활용하여 기록하면 좋다.
	 */
	
	
	/*[핵심정리]
	 * 아래 계층의 예외를 예방하거나 스스로 처리할 수 없고, 그 예외를 상위 계층에 그대로 노출하기 곤란하다면 예외 번역을 사용하라.
	 * 이때 예외 연쇄를 사용하면 상위 계층에는 맥락에 어울리는 고수준 예외를 던지면서 근본 원인도 함께 알려주어 오류 분석에 좋다.
	 * 
	 */
	public static void main(String[] args) {
		
	}

}
