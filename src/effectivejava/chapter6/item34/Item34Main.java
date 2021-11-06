package effectivejava.chapter6.item34;

/**
 * int 상수 대신 열거 타입을 사용하라
 * @author 박민영
 *
 */
public class Item34Main {
	//정수 열거패턴 - 상당히 취약하다
	public static final int APPLE_FUJI = 0;
	public static final int APPLE_PIPPIN = 1;
	public static final int APPLE_GRANNY_SMITH = 2;
	
	public static final int ORANGE_NAVEL = 0;
	public static final int ORANGE_TEMPLE = 1;
	public static final int ORANGE_BLOOD = 2;
	
	//문자열 열거패턴
	public static final String ALPHABAT_A = "A";
	public static final String ALPHABAT_B = "B";
	public static final String ALPHABAT_C = "C";
	
	/*
	 * 정수 열거패턴, 문자열 열거패턴의 단점
	 * 1. 타입안전을 보장할 방법이 없으며 표현력도 좋지않음.
	 * 2. 정수 열거 패턴을 사용한 프로그램은 깨지기 쉽다.
	 * 3. 정수 상수는 문자열로 출력하기가 다소 까다롭다.
	 * 4. 문자열 열거패턴은 상수의 의미를 출력할 순 있지만, 문자열 이름대신 하드코딩할 경우가 있다. 
	 * 
	 * */
	
	//열거타입
	public enum Apple{FUJI, PIPPIN, GRANNY_SMITH}
	public enum Orange{NAVEL, TEMPLE, BLOOD}
	
	
	/*
	 * 열거타입 자체는 클래스이며, 상수 하나당 자신의 인스턴스를 하나씩 만들어 public static final 필드로 공개한다.
	 * 열거타입은 생성자를 제공하지 않으므로 사실상 final이다.
	 * 
	 * 열거타입의 장점
	 * 1. 컴파일 타임 타입 안정성을 제공한다.
	 * 2. 각자의 이름공간이 있어서 이름이 같은 상수도 평화롭게 공존한다.
	 * 3. 열거타입의 toString 메서드는 출력하기에 적합한 문자열을 내어준다.
	 * 
	 * */
	public static void main(String[] args) {
		//열거타입은 메서드나 필드를 추가할 수 있다.
		//데이터와 메서드를 갖는 열거 타입
		double earthWeight = 185d;
		double mass = earthWeight / Planet.EARTH.surfaceGravity();
		for(Planet p : Planet.values()) {
			System.out.printf("%s에서의 무게는 %f이다.%n",p,p.surfaceWeight(mass));
		}
		
		//값에 따라 다르게 동작하는 열거타입.
		Operation1.PLUS.apply(1, 2);
		Operation2.PLUS.apply(1, 2);
		
		//toString 재정의한 열거타입
		System.out.println(Operation2.PLUS);
		System.out.println(Operation3.PLUS);
		
		/*
		 * 상수별 메서드 구현에는 열거 타입 상수끼리 코드를 공유하기 어렵다는 단점이 있다.
		 * 아래는 전략 열거 타입 패턴
		 * */
		System.out.println(PayrollDay.MONDAY.pay(60, 60));
		
		/*
		 * switch문은 열거타입의 상수별 동작을 구현하는 데 적합하지 않지만, 기존 열거타입에 상수별 동작을 혼합해 넣을 때는 switch문이 좋은 선택이 될 수 있다.
		 * 추가하려는 메서드가 의미상 열거타입에 속하지 않는다면 직접만든 열거타입이라도 switch문을 적용하는게 좋다.
		 * */
		
		Operation3 minus = Operation3.inverse(Operation3.PLUS);
		
		
		/*
		 * 열거타입을 사용할 상황
		 * 1. 필요한 원소를 컴파일타임에 다 알 수 있는 상수 집합이라면 항상 열거 타입을 사용하자.
		 * 2. 열거타입에 정의된 상수 개수가 영원이 고정 불변일 필요는 없다.
		 * 
		 * 
		 * 열거타입은 정수 상수보다 뛰어나다.
		 * 더 읽기 쉽고 안전하고 강력하다.
		 * 대다수 열거타입이 명시적 생성자나 메서드 없이 쓰이지만, 각 상수를 특정 데이터와 연결짓거나 상수마다 다르게 동작하게 할 깨는 필요하다.
		 * 드물게는 하나의 메서드가 상수별로 다르게 동작해야 할 때도 있다.
		 * 이런 열거타입에서는 switch문 대신 상수별 메서드 구현을 사용하자.
		 * 열거 타입 상수 일부가 같은 동작을 공유한다면 전략 열거 타입 패턴을 사용하자
		 * 
		 * */
	}
}
