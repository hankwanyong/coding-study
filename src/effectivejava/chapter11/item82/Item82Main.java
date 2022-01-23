package effectivejava.chapter11.item82;

public class Item82Main {

	/*
	 * Item82 스레드 안전성 수준을 문서화하라
	 */
	public static void main(String[] args) {
		/*
		 * 멀티스레드 환경에서도 API를 안전하게 사용하게 하려면 
		 * 클래스가 지원하는 스레드 안전성 수준을 정확히 명시해야한다.
		 * 
		 * 불변
		 * 무조건적 스레드 안전
		 * 조건부 스레드 안전
		 * 스레드 안전하지 않음
		 * 스레드 적재적
		 * 
		 * 조건부 스레드 안전한 클래스는 주의해서 문서화해야 한다.
		 *  - 어떤 순서로 호출할 때 외부 동기화가 필요한지, 
		 *    그리고 그 순서로 호출하려면 어떤 락 혹은 락들을 얻어야 하는지 알려줘야한다
		 */

	}

}
