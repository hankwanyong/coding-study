package effectivejava.chapter6.item40;

import java.util.HashSet;
import java.util.Set;

/**
 * @Override 애너테이션을 일관되게 사용하라
 * @author 박민영
 *
 */
public class Item40Main {
	public static void main(String[] args) {
		
		/*
		 * @Override는 자바가 기본적으로 제공하는 애너테이션중 보통의 프로그래머에게 가장 중요한 애너테이션
		 * 메서드 선언에만 달 수 있으며, 이 애터네이션이 달렸다는 것은 상위타입의 메서드를 재정의했음을 뜻한다.
		 */
		
		
		Set<Bigram> s = new HashSet<>();
		for(int i = 0; i<10; i++) {
			for(char ch = 'a'; ch<='z';ch++) {
				s.add(new Bigram(ch, ch));
			}
		}
		System.out.println(s.size());
		
		/*
		 * 재정의한 모든 메서드에 @Override 애너테이션을 의식적으로 달면 여러분이 실수했을 때 컴파일러가 바로 알려줄 것이다.
		 * 예외는 한 가지 뿐이다.
		 * 구체 클래스에서 상위 클래스의 추상 메서드를 재정의한 경우엔 이 애너테이션을 달지 않아도 된다(단다고 해서 해로울 것도 없다).
		 */
	}
}
