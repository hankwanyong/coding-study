package effectivejava.chapter9.item62;

public class Item62Main {

	/*
	 * 아이템 62 다른 타입이 적절하다면 문자열 사용을 피하라
	 * 
	 * 문자열 : String Class
	 * 
	 * 문자 : char
	 * 문자 배열 : char[]
	 * 
	 * String은 문자 배열을 사용하기 편하도록 만든 Class
	 * 실제 내부 구현에 char[] 를 사용하고 있다
	 * 
	 * 따라서 String은 기본타입보다 성능이 좋지 않다
	 *
	 * String은 데이터가 진짜 문자열일때만 사용하자
	 * 
	 * 수치형이라면 int, float, BigInteger 등
	 * 예/아니오 열거타입이라면 boolean
	 * 
	 * 더 적합한 데이터 타입이 있거나 새로 작성할 수 있다면, 문자열을 쓰고 싶은 유혹을 뿌리쳐라.
	 * 문자열은 잘못 사용하면 번거롭고, 덜 유연하고, 느리고, 오류 가능성도 크다.
	 * 문자열을 잘못 사용하는 흔한 예로는 기본 타입, 열거 타입, 혼합 타입이 있다.
	 * 
	 * 
	 */
	public static void main(String[] args) {

		String a = "";
		
	}

}
