package effectivejava.chapter10.item70;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

public class Item70Main {

	/*
	 * [아이템70] 복구할 수 있는 상황에는 검사 예외를, 프로그램 오류에는 런타임 예외를 사용하라
	 */
	
	/*
	 * 자바에는 throwable로 검사 예외, 런타임 예외, 에러 3가지를 제공한다.
	 * 
	 * 1. 호출하는 쪽에서 복구하리라 여겨지는 상황이라면 검사 예외를 사용하라. ( 검사와 비검사 예외를 구분하는 기본 규칙)
	 *  - API 설계자는 API 사용자에게 검사 예외를 던져주어 그 상황에서 회복하라고 요구하는 것이다.
	 *  - 비검사 throwable은 두 가지로, 런타임 예외와 에러다.
	 *    - 이 둘은 프로그램에서 비검사 예외나 에러를 던졌다는 것은 복구가 불가능하거나 더 실행해봐야 득보다 실이 많다는 뜻이다.
	 *    
	 * 2. 프로그래밍 오류를 나타낼 때는 런타임 예외를 사용하자.
	 *  - 런타임 예외의 대부분은 전제조건을 만족하지 못했을 때 발생한다. (API 명세에 기록된 제약을 어김)
	 *  
	 *  에러는 보통 JVM이 자원 부족, 불변식 깨짐 등 더 이상 수행을 계속할 수 없는 상황을 나타낼 떄 사용한다. Error클래스를
	 *  상속해 하위 클래스를 만드는 일은 자제한다. 우리가 구현하는 비검사 throwable은 모두 RuntimeException의 하위 클래스여야 한다.
	 *  
	 *  Excpetion, RuntimeException, Error를 상속하지 않는 throwable을 절대로 사용하지 않는다.
	 *  
	 *  
	 */
	
	/*
	 * [핵심정리]
	 * 복구할 수 있는 상황이라면 검사 예외, 프로그래밍 오류라면 비검사 예외를 던지자.
	 * 검사 예외도 아니고 런타임 예외도 아닌 throwable은 정의하지 말자.
	 * 검사 예외라면 복구에 필요한 정보를 알려주는 메서드도 제공하자.[아이템75]
	 */
	
	public static void main(String[] args) {

	}

}
