package effectivejava.chapter8.item49;

import java.math.BigInteger;
import java.util.Objects;

public class Item49Main {
	/* < 8장 메서드>
	 * - 메서드 설계할때 주의할 점
	 * 
	 *  아이템49
	 *  매개변수가 유효한지 검사하라   
	 *  
	 *  - 입력매개변수가 원하는 조건에 맞는지 검사, 문서화 해야한다.
	 */
	
	
	public static BigInteger mod(BigInteger x) {
		Objects.requireNonNull(x, "requireNonNull");
		
		if(x.signum() <= 0) { //x.signum() -> throw nullPointException
			throw new IllegalArgumentException("파라미터는 양수이어야 합니다. => " + x);
		}
		
		return x;
	}
	
	//자바7에 추가된 java.util.Objects.requireNonNull 메서드는 유연하고 사용하기 편하다.
	public static BigInteger mod2(BigInteger x) {
		Objects.requireNonNull(x, "requireNonNull");
		return x;
	}
	
	// public이 아닌 메서드라면 단언문(assert)을 사용해 매개변수 유효성을 검증할 수 있다.
	private static void sort (long a[], int offset, int length) {
		//AssertionError 
		assert a != null;
		assert offset >= 0 && offset <= a.length;
		assert length >= 0 && length <= a.length - offset;
	}
	
	public static void main(String[] args) {
		
		BigInteger a = null;
		try {
			
			mod(a);
			mod2(a);
			
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		} catch (NullPointerException e) {
			System.out.println(e.getMessage());
		}
		
	}
	
	/* 생성자 매개변수의 유효성 검사는 클래스 불변식을 어기는 객체가 만들어지지 않게 하는 데 꼭 필요하다.
	 * 
	   메서드 몸체 실행 전 유효성을 검사해야 한다는 규칙에 예외 
	 	- 유유성 검사 비용이 높다.
	 	- 실용적이지 않을 때.
	 	- 계산과정에서 암묵적으로 검사가 수행될 때.
	 	
	 	Collections.sort(List) 
	 	- 리스트 안의 객체는 모두 상호 비교 가능.
	 	- 만약 비교불가능 타입 객체가 들어온다면 ClassCastException을 던진다.
	 	- 따라서 유효성 검사하는게 실익이 없다.
	 
	 */
	
	/* 핵심정리
	 * 매서드는 최대한 범용적으로 설계해야한다.
	 * 매개변수 제약은 적을수록 좋다.
	 * 제약들을 문서화하고, 메서드 시작부분에 명시적 검사해야한다.
	 */
   
}
