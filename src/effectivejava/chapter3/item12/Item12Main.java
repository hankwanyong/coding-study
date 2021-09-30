package effectivejava.chapter3.item12;

/**
 * toString을 항상 재정의 하라.
 * @author 박민영
 *
 */
public class Item12Main {
	/*
	 * toString의 일반규약
	 * 	1. 간결하면서 사람이 읽기 쉬운 형태의 유익한 정보
	 * 	2. 모든 하위 클래스에서 이 메서드를 재정의하라.
	 * 
	 * 
	 * 작성시 주의사항
	 * 	1. 객체가 가진 주요 정보 모두를 반환하는게 좋다.
	 * 	2. 포멧을 명시하든 아니든 여러분의 의도는 명확히 밝혀야 한다.
	 * 	3. toString이 반환한 값에 포함된 정보를 얻어올 수 있는 API를 제공하자.
	 * 
	 * */
	public static void main(String[] args) {
		/*
		 * 모든 구체 클래스에서 Object의 toString을 재정의하자.
		 * 상위 클래스에서 이미 알맞게 재정의한 경우는 예외다.
		 * toString을 재정의한 클래스는 사용하기도 즐겁고 그 클래스를 사용한 시스템을 디버깅하기 쉽게 해준다.
		 * toString은 해당 객체에 관한 명확하고 유용한 정보를 읽기 좋은 형태로 반환해야 한다.
		 * */
	}
}
