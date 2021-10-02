package effectivejava.chapter4.item19;

import java.time.Instant;

public class Item19Main {

	/*
	 * Item19 상속을 고려해 설계하고 문서화하라. 그러지 않았다면 상속을 금지하라
	 * 
	 * - item 15 ~ 18 클래스의 API로 공개된 메서드, 멤버변수에 대한 주의 사항이 있다.
	 * 		> 재정의 가능(public, protected - final이 아님) 메서드라면 API 설명에 내부 구현방식을 자세히 적자
	 * - 상속용 클래스를 만들었다면, 직접 하위 클래스를 만들어서 검증하자!
	 * - 상속용 클래스의 생성자는 직접적으로든 간접적으로든 재정의 가능 메서드를 호출해서는 안된다.
	 */
	
	
	public static void main(String[] args) {
		
		class Super {
			public Super() {
				overrideMe();
			}
			public void overrideMe() {}
		}

		final class Sub extends Super {
			private final Instant instant;
			
			Sub() {instant = Instant.now();}
			
			@Override public void overrideMe() {
				System.out.println(instant);
			}
		}
		
		Sub sub = new Sub();	// sysout > null
		sub.overrideMe();		// sysout > instant
		// 첫번째 null인 이유는 인스턴스 필드를 초기화하기도 전에 overriveMe를 호출하기 때문이다.
		
		// 이처럼 상속 가능 클래스는 프로그래머에게 부담을 준다.
		// > 상속용으로 설계하지 않은 클래스는 상속을 금지하자
		// 1. 클래스를 final로 선언
		// 2. 모든 생성자를 private OR package-private로 선언 > public 정적 팩터리를 만들어주자
		
		// 상속을 금지하면 사용 하기에 상당히 불편해 질수 있다.
		// > 재정의 가능 메서드를 사용하지 않게 만들고 문서로 남기다.(책임은 사용자)
	}
}
