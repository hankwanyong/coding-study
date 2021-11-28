package effectivejava.chapter4.item25;

public class Item25Main{

	/*
	 * Item25 톱레벨 클래스는 한 파일에 하나만 담으라
	 * 
	 * 하나의 java 파일에는 하나의 class를 생성하자.라는 말이라고 한다.
	 * 
	 * 자바 개발을 하다보면 기술적으로 가능하지만, 관례적으로 사용하지 않는 몇 가지가 있다. 
	 * 간단한 예로 변수는 Camel case(ex. userName)로 표기하고, 
	 * 상수는 Snake case(ex. USER_TYPE)로 표현하는 것이 있다. 
	 * 같은 맥락으로 하나의 자바 파일에는 하나의 클래스 생성을 원칙으로 한다. 이유는 다음과 같다.
	 * 1.패키지 트리구조로 파악되지 않는 클래스가 생긴다.
	 * 2.수동 컴파일 시 컴파일하는  자바 파일의 순서에 따라 상이한 동작을 초래할 수 있다.
	 * 
	 * 책 150~151페이지의 예제들을 살펴보면 자바 파일을 열어보기 전까지 두 클래스의 존재를 알아채기는 쉽지않다.
	 * 물론 요즘에는 보통 IDE에서 Auto complie을 기본으로 지원하기 때문에 수동으로 컴파일할 일도 없을뿐더러
	 * 숨어있는 클래스까지 파악 가능하다.
	 * 책이 쓰이던 시기에는 이러한 문제가 실제로 업무현장에서 빈번하게 있었을 거라고 예상한다고 한다.
	 * 
	 * 해결방법은 간단하다. '하나의 자바 파일에는 하나의 클래스' 원칙을 지키면 된다. 
	 * 꼭 같은 자바파일 내에 여러 클래스를 만들어야 한다면  private static inner class로 선언하여 Scope를 제한한다는 조건 하에 생성해도 된다.
	 *  
	 */

	/*
	 * 핵심 정리
	 * - 교훈은 명확하다. 
	 *   소스 파일 하나에는 반드시 톱레벨 클래스(혹은 톱레벨 인터페이스)를 하나만 담자.
	 *   이 규칙만 따른다면 컴파일러가 한 클래스에 대한 정의를 여러 개 만들어내는 일은 사라진다.
	 *   소스 파일을 어떤 순서로 컴파일하든 바이너리 파일이나 프로그램의 동작이 달라지는 일은 결코 일어나지 않을 것이다.
	 */
	
}
