package effectivejava.chapter11.item83;

public class Item83Main {

	/*
	 * Item83 지연 초기화는 신중히 사용하라
	 */
	public static void main(String[] args) {
		/*
		 * 지연 초기화(lazy initialization) 
		 *  : 필드의 초기화 시점을 그 값이 처음 필요할 때까지 늦추는 기법
		 *  
		 *  반대로 웹 서버 기동시 초기화 되는 필드
		 *  	- 커넥션 풀(DB, redis)
		 *  	- Constant 클래스에 명시한 상수 필드
		 *  	- 스프링 Bean (스프링 컨테이너를 초기화 할때 스프링 컨테이너는 bean 객체를 생성하고 의존 주입한다)
		 *  		>> (동시성)Bean 의 전역변수는 상수가 불변 상수가 아니면 다른 스레드에 영향을 줄 수 있다.
		 *  
		 *  자바에서 지연 초기화 사용하는 방법
		 *  	> static class에서 static 필드로 선언
		 *  	> 해당 필드 호출시 null check > 없으면 초기화
		 */

	}
	
	private static class lazyInit {
		static String str = null;
		
		public static String getField() {
			if(null == str) {
				str = computeFieldValue();
			}
			return str;
		}
		
		private static String computeFieldValue() {
			return "init string";
		}
	}

}
