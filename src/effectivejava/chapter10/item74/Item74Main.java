package effectivejava.chapter10.item74;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

public class Item74Main {

	/*
	 * [아이템74] 메서드가 던지는 모든 예외를 문서화하라
	 * 
	 * 1.검사 예외는 항상 따로따로 선언하고, 각 예외가 발생하는 상황을 자바독의 @throws 태그를 사용하여 정확히 문서화하자.
	 * 
	 *  @ main은 오직 JVM만이 호출하므로 Exception을 던지도록 선언해도 괜찮다.
	 *  @ 비검사 예외도 검사 예외처럼 정성껏 문서화해두면 좋다.
	 *  
	 * 2.메서드가 던질 수 있는 예외를 각각 @throws 태그로 문서화하되, 비검사 예외는 메서드 선언의 throws 목록에 넣지 말자.
	 * 
	 * 3. 한 클래스에 정의된 많은 메서드가 같은 이유로 같은 예외를 던진다면 그 예외를 (각각의 메서드가 아닌) 클래스 설명에 추가하는 방법도 있다.
	 *  - 예) NullPointerException
	 *  
	 *  [핵심정리]
	 *  메메서드가 던질 가능성이 있는 모든 예외를 문서화하라.
	 */
	
	
	public static void main(String[] args) {
		
	}

}
