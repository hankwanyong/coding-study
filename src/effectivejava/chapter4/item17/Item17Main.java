package effectivejava.chapter4.item17;

import effectivejava.sample.Complex;

public class Item17Main {

	/*
	 * Item17 변경 가능성을 최소화하라
	 * 
	 * - 불변클래스 : 인스턴스의 내부 값을 수정할 수 없는 클래스
	 * 		ex) String, 기본 타입의 박싱된 클래스(BigInteger, BigDecimal ...)
	 * 		> 가변 클래스보다 설계하고 구현하고 사용하기 쉬우며, 오류가 생길 여지도 적고 훨씬 안전 하다.
	 * - 불변 클래스 만들기 
	 * 		1. 객체의 상태를 변경하는 메서스(변경자)를 제공하지 않는다
	 * 		2. 클래스를 확장할 수 없도록 한다
	 * 			2-1. 클래스를 final로 선언 > 상속할 수 없다 ex) String
	 * 			2-2. 모든 생성자를 private OR default 로 만들고 public 정적 팩터리를 제공 (아이템1)
	 * 		3. 모든 필드를 final로 선언한다 > 시스템 강제 > 새로 생성된 인스턴스를 동기화 없이 다른 스레드로 건네도 문제없이 동작????
	 * 		4. 모든 필드를 private으로 선언 한다. > 직접 접근해 수정하는 일을 막아준다.
	 * 		5. 자신 외에는 내부의 가변 컴포넌트에 접근할 수 없도록 한다.> 
	 * 
	 * # 불변 객체는 아주 좋다. but 값이 다르면 반드시 독립된 객체로 만들어야 한다. (값의 가지수가 많다면 이를 모두 만들어야한다)
	 *  
	 */
	
	public static void main(String[] arg) {
		
		
		Complex c1 = new Complex(2, 3);
		Complex c2 = new Complex(5, 7);
		
		System.out.println("c1 hashcode : " + c1.hashCode());
		System.out.println("c2 hashcode : " + c2.hashCode());
		
		Complex c3 = c1.plus(c2);
		Complex c4 = c1.minus(c2);
		Complex c5 = c1.times(c2);
		Complex c6 = c1.divideBy(c2);
		
		
		System.out.println("c1 hashcode : " + c1.hashCode());
		System.out.println("c2 hashcode : " + c2.hashCode());
		System.out.println("c3 hashcode : " + c3.hashCode());
		System.out.println("c4 hashcode : " + c4.hashCode());
		System.out.println("c5 hashcode : " + c5.hashCode());
		System.out.println("c6 hashcode : " + c6.hashCode());
		
		// 함수형 프로그래밍 패턴 : 피연산자에 함수를 적용해 그 결과를 반환하지만, 피연산자 자체는 그대로인 프로그래밍
		// c1, c2의 hashcode는 변경되지 않음.
		// Complex 는 불변 클래스
		
		// 클래스는 꼭 필요한 경우가 아니라면 불변이어야 한다
		// 불변으로 만들 수 없는 클래스라도 변경할 수 있는 부분을 최소한으로 줄이자.
		// >>>>>> 다른 합당한 이유가 없다면 모든 필드는 private final로 만들자
		// 생성자는 불변식 설정이 모두 완료된, 초기화가 완벽히 끝난 상태의 객체를 생성해야 한다 
		// >>>>>> 확실한 이유가 없다면 생성자와 정적 팩터리 외에는 그 어떤 초기화 메서드도 public으로 제공해서는 안 된다.
		
		// 불변 클래스 단점!!
		// 값이 다르면 반드시 독립된 객체로 만들어야한다.
		// >>>>>> 클라이언트들이 원하는 복잡한 연산들을 정확히 예측할 수 있다면 가변 동반 클래스만으로 충분
		// ex) String의 가변 동반 클래스 StringBuilder, StringBuffer
		// /img/StringBuffer.PNG
		// /img/StringBuffer2.PNG
		
	}
}
