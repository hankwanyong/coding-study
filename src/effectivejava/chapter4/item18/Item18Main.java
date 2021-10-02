package effectivejava.chapter4.item18;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import effectivejava.sample.InstrumentedHashSet_Err;
import effectivejava.sample.InstrumentedSet;

public class Item18Main {
	
	/*
	 * Item18 상속보다는 컴포지션을 사용하라
	 * 
	 * - 메서드 호출과 달리 상속은 캡슐화를 깨뜨린다. 
	 * 		> 상위 클래스가 어떻게 구현되느냐에 따라 하위 클래스위 동작에 이상이 생길 수 있다. 
	 * 		(상위 클래스가 수정되었을때, 하위 클래스에서 오류가 발생 할 수 있다)
	 */
	
	
	public static void main(String[] args) {

		List<String> aaa = new ArrayList<>();
		aaa.add("틱");
		aaa.add("탁탁");
		aaa.add("팡");
		
//		Set<String> seta = new HashSet<>(aaa);
		
		InstrumentedHashSet_Err<String> s = new InstrumentedHashSet_Err<>();
		s.addAll(aaa);
		
		
		System.out.println(s.getAddCount());	// 6
		// 3 사이즈의 리스트를 더했지만 값은 6이 나온다
		// HashSet의 addAll은 add메서드를 이용했다.
		// s.addAll > AbstractCollection.addAll > add 사용 > add 메서드가 InstrumentedHashSet_Err 재정의 > count++ :::: allAll count++, add count++ >> count +2
		// 상위 클래스에 따라 재정의 한 하위 클래스에서 의도대로 작동하지 않을수 있다.
		
		// 컴포지션 : 기존 클래스가 새로운 클래스의 구성요소로 쓰임
		InstrumentedSet<String> s2 = new InstrumentedSet<>(new HashSet<String>());
		s2.addAll(aaa);
		System.out.println(s2.getAddCount());	// 3
		// s.addAll > AbstractCollection.addAll > add 사용 > add 메서드를 ForwardingSet 구현 > count++(X) :::: allAll count++ >> count +1
		// 다른 Set 인스턴스를 감싸고(wrap) 있다고해서 InstrumentedSet 같은 클래스를 래퍼 클래스 라고한다.
		// 데코레이터 패턴!!!
		
		// 상속은 강력하지만 캡슐화를 해친다는 문제가 있다. 상속은 상위 클래스와 하위 클래스가 순수한 is-a 관계일 때만 써야한다.
		// 상속의 취약점을 피하려면 컴포지션을 사용하자.
		
		
	}
	
}
