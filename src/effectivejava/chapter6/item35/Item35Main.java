package effectivejava.chapter6.item35;

/**
 * ordinal 메서드 대신 인스턴스 필드를 사용하라.
 * @author 박민영
 *
 */
public class Item35Main {
	/*
	 * 대부분의 열거타입 상수는 하나의 정숫값에 대응된다.
	 * 그리고 모든 열거 타입은 해당 상수가 그 열거타입의 몇 번째 위치인지 반환하는 ordinal 메서드를 제공한다.
	 * */
	
	public static void main(String[] args) {
		
		System.out.println(Ensemble.DOUBLE_QUARTET.numberOfMusicians());
		System.out.println(Ensemble.DOUBLE_QUARTET.numberOfMusicians_useOrdinal());
		
		/*
		 * ordinal메서드 사용시
		 * 1. 동작은 하지만 상수의 순서가 바뀌면 오작동함.
		 * 2. 8중주와 똑같이 8명이 연주하는 복4중주를 추가할 수 없다.
		 * 3. 값을 중간에 비워둘 수 없다.
		 * 
		 * 해결책 : 열거 타입 상수에 연결된 값은 ordinal 메서드로 얻지 말고, 인스턴스 필드에 저장하자.
		 * 
		 * ordinal은 사용하지 말자!
		 * 
		 * */
		
	}
}
