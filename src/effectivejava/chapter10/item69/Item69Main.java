package effectivejava.chapter10.item69;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

public class Item69Main {

	/*
	 * [아이템69] 예외는 진짜 예외 상황에만 사용하라
	 */
	
	public void code69_1() {
		try {
			int i =0;
			int ary[] = new int[10];
			while(true) {
				ary[i++] = i;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			
		}
		
		/* [문제점]
		 * 1. 예외는 예외 상황에 쓸 용도로 설계되었으므로 JVM 구현자 입장에서는 명확한
		 * 검사만큼 빠르게 만들어야 할 동기가 약하다 (최적화에 별로 신경 쓰지 않았을 가능성이 크다.)
		 *  <> 아이템67 최적화는 신중히 하라 ( 1.하지마라 2. 아직 하지 마라. 완전히 명백하고 최적화되지 않은 해법을 찾을 때까지는 하지마라.)
		 * 
		 * 2. 코드를 try-catch 블록 안에 넣으면 JVM이 적용할 수 있는 최적화가 제한된다.
		 *  Java는 더 빠른 실행을 위해 메소드에서 명령을 재배열하는 등의 최적화를 수행한다. 이러한 것들이 제한이 될 수 있다.
		 * 
		 * 3. 배열을 순회하는 표준 관용구는 앞서 걱정한 중복 검사를 수행하지 않는다. JVM이 알아서 최적화를 없애준다.
		 * 
		 * -> ArrayIndexOutOfBoundsException 외의 어떤 버그가 있을 경우 Exception에 묻힐수 있는 상황이 발생할 수 있다.
		 * 
		 *  @ 예외는 오직 예외 상황에서만 써야 한다. 절대로 일상적인 제어 흐름용으로 쓰여선 안 된다.
		 *   -> 표준적이고 쉽게 이해되는 관용구를 사용하고, 성능 개선을 목적으로 과하게 머리를 쓴 기법을 자제하라. 실제로 성능이 좋아지더라도
		 *   자바 플랫폼이 꾸준히 개선되고 있으니 최적화로 얻은 상대적인 성능 우위가 오래가지 않을 수 있다. 반면 과하게 영리한 기법에 숨겨진
		 *   미묘한 버그의 폐해와 어려워진 유지보수 문제는 계속 이어질 것이다.
		 *   
		 * 
		 */
	}
	
	public void exCode_Iterator() {
		Collection<String> collection = new LinkedHashSet<>();
		for(Iterator<String> i = collection.iterator(); i.hasNext();) {
			String a = i.next();
		}
		/*
		 * @ 잘 설계된  API라면 클라이언트가 정상적인 제어 흐름에서 예외를 사용할 일이 없게 해야 한다.
		 * -> 특정 상태에서만 호출할 수 있는 '상태 의존적'메서드를 제공하는 클래스는 '상태 검사' 메서드도 함께 제공해야 한다.		
		 * -> Iterator 인터페이스의 next와 hasNext가 각각 상태 의존적 메서드와 상태 검사 메서드에 해당한다.
		 */
		 
	}
	
	/*
	 * 상태 검사 메서드를 사용하는 방법 말고도 빈 옵셔널이나 null 같은 특수한 값을 반환하는 방법
	 * 상태 검사 메서드, 옵셔널, 특정 값 중 하나를 선택하는 지침
	 * 외부 동기화 없이 여러 스레드가 동시에 접근할 수 있거나 외부 요인으로 상태가 변할 수 있다면 옵셔널이나 특정 값을 사용한다. 
	 * 상태 검사 메서드와 상태 의존적 메서드 호출 사이에 객체의 상태가 변할 수 있기 때문이다.
	 * 성능이 중요한 상황에서 상태 검사 메서드가 상태 의존적 메서드의 작업 일부를 중복 수행한다면 옵셔널이나 특정 값을 선택한다.
	 * 다른 모든 경우엔 상태 검사 메서드 방식이 조금 더 낫다고 할 수 있다.
	 *  상태 검사 메서드 호출을 깜빡 잊었다면 상태 의존적 메서드가 예외를 던져 버그를 확실히 드러내지만 특정 값은 검사하지 않고 지나쳐도 발견하기가 어렵다.(옵셔널에는 해당하지 않음).
	 */
	
	// null 대신 optional 활용 아이템55
	public static <E extends Comparable<E>> Optional<E> max(Collection<E> c) {
		if (c.isEmpty()) return Optional.empty();
		E result = null; 
		for (E e : c) {
			if (result == null || e.compareTo(result) > 0) { 
				result = Objects.requireNonNull(e);
			}
		}		
		return Optional.of(result); 
	}

	/*
	 * [핵심정리]
	 * 예외는 예외 상황에서 쓸 의도로 설계되었다. 정상적인 제어 흐름에서 사용해서는 안 되며, 이를 프로그래머에게 강요하는 API를 만들어서도 안 된다. 
	 */
	
	public static void main(String[] args) {

	}

}
