package effectivejava.chapter9.item64;

import java.util.LinkedHashSet;
import java.util.Set;

public class Item64Main {

	/*
	 * 아이템 64 객체는 인터페이스를 사용해 참조하라.
	 * 
	 * 적합한 인터페이스만 있다면 매개변수뿐 안이라 반환값, 변수, 필드를 전부 인터페이스 타입으로 선언하라. 
	 *  
	 */

	//좋은예. 인터페이스를 타입으로 사용했다.
//	Set<Son> sonSet = new LinkedHashSet<>();

	//나쁜예. 클래스를 타입으로 사용했다.
//	LinkedHastSet<Son> sonSet = new LinkedHashSet();
	
	/*
	 * 인터페이스를 타입으로 사용하는 습관을 길러두면 프로그램이 훨씬 유연해 질 것이다.
	 *
	 * 적합한 인터페이스가 없다면 당연히 클래스로 참조해야 한다.
	 *  - String, BinInteger 등
	 *  - 클래스 기반으로 작성된 프레임워크가 제공하는 객체인 경우
	 *  - 인터페이스에 없는 특별한 메소드를 제공하는 클래스
	 *  
	 *  적합한 인터페이스가 없다면 클래스의 계층구조 중 필요한 기능을 만족하는 가장 덜 구체적인(상위의) 클래스를 타입으로 사용하자. 
	 */
	
	
}
