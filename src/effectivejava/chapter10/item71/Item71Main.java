package effectivejava.chapter10.item71;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

public class Item71Main {

	/*
	 * [아이템71] 필요 없는 검사 예외 사용은 피하라
	 * 
	 * 결과 코드, 검사 예외를 던질 수 있다고 선언됐다면, API사용자(호출자)에게 부담이 간다.
	 * 검사 예외와 비검사 예외 중 어느 것을 선택해야 할지는 프로그래머가 그 예외를 어떻게 다룰지 생각해야한다.
	 * 
	 * 검사 예외가 프로그래머에게 지우는 부담은 메서드가 단 하나의 검사 예외만 던질 때가 특히 크다.
	 * -> 많다면  catch 문 하나 추가정도.
	 * -> 한개라면 생각하지 않았던 예외처리 방안을 마련해야 하는 것.
	 * 
	 * 그렇다면 검사 예외를 회피하면 되지 않을까? -> 옵셔널을 반환하는 것(아이템55)
	 *  -단점 : 예외가 발생한 이유를 구체적으로 알려주는 부가 정보를 담을 수 없다.
	 * -> 반면 예외를 사용하면 구체적인 예외 타입과 그 타입이 제공하는 메서드들을 활용해 부가 정보를 제공 할 수 있다. 
	 */
	
	/*
	 * 검사 예외를 던지는 메서드를 2개로 쪼개 비검사 예외로 바꿀 수 있다. 
	 */
	public static void code71_1() {
		Collection<String> collection =  null;
		
		try {
			System.out.println("code71_1 size : " + collection.size()); //action
		} catch(Exception e) {
			//예외 처리	
			System.out.println("code71_1 Exception");
		}
		
	}
	
	public static void code71_2() {
		Collection<String> collection =  null;
		
		if(!isNull(collection)) { //Permitted
			System.out.println("code71_1 size : " + collection.size()); //action
		} else {
			System.out.println("code71_1 size : Empty" );
		}		
	}
	
	public static boolean isNull(Object a) {
		if(a != null) {
			return true;
		} else {
			return false;
		}
	}
	
	/*
	 *  Permitted는 상태 검사 메서드에 해당하므로 아이템69에서 말한 단점도 그대로 적용되니 주의해야 한다.
	 *  즉, 외부 동기화 없이 여러 스레드가 동시에 접근 할 수 있거나 외부 요인에 의해 상태가 변할 수 있다면 적절하지 않다.
	 *  Permitted 와 action 호출 사이에 객체의 상태가 변할 수 있기 때문이다.
	 *  또한 Permitted가 action메서드의 작업 일부를 중복 수행한다면 성능에서 손해이기 때문에 이 리페터링이 적절하지 않을 수 있다.
	 */

	/*
	 * [핵심정리]
	 * 검사 예외는 프로그램의 안정성을 높여주지만, 남용하면 쓰기 고통스러운 API를 낳는다.
	 * API호출자가 예외 상황에서 복구할 방법이 없다면 비검사 예외를 던지자. 
	 * 복구가 가능하고 호출자가 그 처리를 해주길 바란다면, 우선 옵셔널을 반환해도 될지 고민하자. 옵셔널만으로는 상황을 처리하기에 충분한
	 * 정보를 제공할 수 없을 때만 검사 예외를 던지자.
	 */
	
	public static void main(String[] args) {
		code71_1();
		code71_2();
	}

}
