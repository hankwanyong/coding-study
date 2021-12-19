package effectivejava.chapter9.item61;

public class Item61Main {

	/*
	 * 아이템 61 박싱된 기본 타입보다는 기본 타입을 사용하라
	 * 
	 * 박싱된 기본타입 > 래퍼 클래스 (wrapper class) 
	 * 		- 객체 > null 가능, 메모리 주소를 갖는다. 값을 갖는다
	 * 
	 * 기본타입 (primitive type)
	 * 		- 값만 갖는다 > 래퍼 클래스보다 시간과 메모리 사용면에서 더 효율적이다.
	 *
	 * 객체를 사용해야하는 경우가 아니라면 기본타입을 사용하자
	 * 	ex) 타입 매개변수 
	 * 		- List<int> X 
	 * 		- List<Integer> O
	 * 
	 */

}
