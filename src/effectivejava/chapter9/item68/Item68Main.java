package effectivejava.chapter9.item68;

public class Item68Main {

	/*
	 * 아이템 68 일반적으로 통용되는 명명 규칙을 따르라.
	 * 
	 * 
	 * 자바의 명명 규칙은 크게 철자, 문법 두 범주로 나뉜다.
	 * 
	 * 철자규칙은 패키지, 클래스, 인터페이스, 메소드, 필드, 타입 변수의 이름을 다룬다.
	 * 패키지와 모듈 이름은 각 요소를 점(.) 으로 구분하여 계층적으로 짓는다.
	 * 패키지 이름의 나머지는 해당 패키지를 설명하는 하나 이상의 요소(일반적으로 8자 이하의 짧은 단어)로 이뤄진다.
	 * (열거 타입과 애너테이션을 포함한) 클래스와 인터페이스의 이름은 하나 이상의 단어로 이뤄지며, 각 단어는 대문자로 시작한다.
	 * 메소드와 필드 이름은 첫 글자를 소문자로 한다는 것을빼곤 클래스 명명 규칙과 같다.
	 * 상수필드는 모두 대문자로 쓴다.
	 * 타입 매개변수의 이름은 보통 한 문자로 표현한다. 
	 *  (임의의 타입 : T - Type, 컬렉션 원소의 타입 : E - Element, 맵의 키와 값 : K - Key / V - Value, 예외 : X - eXception, 메서드의 반환 타입 : R - Return
	 * 
	 *  식별자 타입               | 예
	 *  패키지와 모듈            | org.junit.jupiter.api , com.google.common.collect
	 *  클래스와 인터페이스   | Stream, FutureTask, LinkedHashMap, HttpClient
	 *  메서드와 필드            | remove, groupingBy, getCrc
	 *  상수 필드                  | MIN_VALUE, NEGETIVE_INFINITY
	 *  지역변수                   | i, denom, houseNum
	 *  타입 매개변수            | T, E, K, V, R, U, T1, T2
	 *  
	 *  문법 규칙은 철자 규칙과 비교하면 더 유연하고 논란도 많다.
	 *  패키지에 대한 규칙은 따로 없다.
	 *  객체를 생성할 수 있는 클래스의 이름은 보통 단수 명사나 명사구를 사용한다.
	 *   ex. Thread, ChessPiece
	 *  객체를 생성할 수 없는 클래스는 복수형 명사로 짓는다.
	 *   ex. Collectors, Collections
	 *  인터페이스는 클래스 이름과 똑같이 짓너나, able 또는 ible 로 끝나는 형용사로 짓는다.
	 *  어떤 동작을 수행하는 메소드의 이름은 동사나 동사구로 짓는다.
	 *  
	 *  반환 타입이 boolean 이 아니거나 해당 인스턴스의 속성을 반환하는 경우 명사, 명사구 또는 get 으로 시작하는 동사구로 짓는다.
	 *  
	 *  객체 타입을 바꿔서 다른 타입의 또 다른 객체로 반환하는 인스턴스 메소드는 toType 으로 짓는다.
	 *  객체의 내용을 다른 뷰로 보여주는 메소드의 이름은 asType 으로 짓는다.
	 *  객체의 값을 기본 타입 값으로 반환하는 메서드의 이름은 보통 typeValue 형태로 짓는다.
	 *  
	 *  핵심정리
	 *   표준 명명 규칙을 체화하여 자연스럽게 베어 나오도록 하자.
	 *   철자 규칙은 직관적이라 모호한 부분이 적은데 반해, 문법 규칙은 더 복잡하고 느슨하다.
	 *   자바 언어 명세 [JLS, 6.1]의 말을 인용하자면 "오랫동안 따라온 규칙과 충돌한다면 그 규칙을 맹종해서는 안된다." 상식이 이끄는 대로 따르자.
	 */


}
