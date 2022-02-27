package effectivejava.v2.chapter3.item11;

import java.util.HashMap;
import java.util.Map;

import effectivejava.chapter3.item10.PhoneNumber;

/**
 * equals를 재정의하려거든 hashCode도 재정의하라 
 * @author 박민영
 *
 */
public class Item11Main {
	/*
	 * equals를 재정의한 클래스 모두에서 hashCode도 재정의 해야한다.
	 * 
	 * hashCode 메서드의 일반 규약
	 * 	1. equals 비교에 사용되는 정보가 변경되지 않았다면, hashCode메서드는 몇번을 호출해도 항상 같은 값을 반환해야 한다.
	 * 	2. equals가 두 객체를 같다고 판단했다면, 두 객체의 hashCode는 같은 값을 반환해야 한다.
	 * 	3. equals가 두 객체를 다르다고 판단했더라도, 두 객체의 hashCode가 서로 다른값을 반환할 필요는 없다. 단, 다른 객체에 대해서는 다른값을 반환해야 해시테이블의 성능이 좋아진다.
	 * 
	 * */
	public static void main(String[] args) {
		
		//논리적으로 같다고 판단되는 두 객체의 hashCode가 다를경우 생기는 문제
		Map<PhoneNumber, String> m = new HashMap<PhoneNumber, String>();
		PhoneNumber pn = new PhoneNumber(707, 867, 5309);
		m.put(pn, "제니");
		pn = new PhoneNumber(707, 867, 5309);
		String value = m.get(pn);
		System.out.println("value : "+value); //"제니"가 나와야 할 것 같지만 null을 반환한다.
		
		
		/*
		 * 해시코드 작성 요령
		 * 
		 * 1. int 변수 result를 선언한 후 값 c로 초기화한다. 이때 c는 해당 객체의 첫번째 핵심 필드를 단계 2.a 방식으로 계산한 해시코드다(여기서 핵심 필드란 equals비교에 사용되는 필드)
		 * 2. 해당 객체의 나머지 핵심필드 f 각각에 대해 다음 작업을 수행한다.
		 * 		a. 해당 필드의 해시코드 c를 계산한다.
		 * 			i. 기본타입 필드라면 Type.hashCode(f)를 수행한다. 여기서 Type은 해당 기본 타입의 박싱 클래스다.
		 * 			ii. 참조 타입 필드면서 이 클래스의 equals 메서드가 이 필드의 equals를 재귀적으로 호출해 비교한다면, 이 필드의 hashCode를 재귀적으로 호출한다.
		 * 			    필드의 값이 null이면 0을 사용한다.
		 * 			iii. 필드가 배열이라면, 핵심 원소 각각을 별도 필드처럼 다룬다. 이상의 규칙을 재귀적으로 적용해 각 핵심 원소의 해시코드를 계산한 다음, 단계 2.b 방식으로 갱신한다.
		 * 				 배열에 핵심 원소가 하나도 없다면 단순히 상수(0을 추천한다)를 사용한다. 모든 원소가 핵심원소라면 Arrays.hashCode를 사용한다.
		 * 		b. 단계 2.a에서 계산한 해시코드 c로 result를 갱신한다. result = 31 * result + c;
		 * 3. result를 반환한다.
		 * 
		 * 
		 * 파생필드는 무시해도 된다.
		 * equals비교에 사용되지 않은 필드는 '반드시' 제외해야한다.(일반규약 2번)
		 * 단계 2.b의 곱셈 31 * result는 필드를 곱하는 순서에 따라 값이 달라지게 한다.
		 * 31을 곱하는 이유는 홀수이면서 소수이기 때문이다. 짝수라면 오버플로 발생시 정보를 잃게된다. (2를 곱하는 것은 시프트 연산과 같은 결과를 내기 때문이다. )
		 * 소수를 곱하는 이유는 명확하진 않지만 전통적으로 그리 해왔다. 
		 * ( 결과적으로 31을 이용하면, 이 곱셈을 시프트 연산과 뺄셈으로 대체해 최적화할 수 있다.(31*i=(i<<5)-i))
		 * (- 해시 충돌이 더욱 적은 방법을 써야한다면 Guava Hashing을 참고하자.)
		 * 
		 * */
		
		
		//HashCode가 재정의되었을 경우
		Map<PhoneNumberHashCode, String> m2 = new HashMap<PhoneNumberHashCode, String>();
		PhoneNumberHashCode pnhc = new PhoneNumberHashCode(707, 867, 5309);
		m2.put(pnhc, "제니");
		pnhc = new PhoneNumberHashCode(707, 867, 5309);
		String value2 = m2.get(pnhc);
		System.out.println("value2 : "+value2);
		
		
		
		/*
		 * 성능을 높인답시고 해시코드를 계산할 때 핵심필드를 생략해서는  안 된다.
		 * hashCode가 반환하는 값의 생성 규칙을 API 사용자에게 자세히 공표하지 맣자. 그래야 클라이언트가 이 값에 의지하지 않게 되고, 추후에 계산 방식을 바꿀 수도 있다.
		 * 
		 * 
		 * equals를 재정의할 때는 hashCode도 반드시 재정의해야 한다.
		 * 그렇지 않으면 프로그램이 제대로 동작하지 않을 것이다.
		 * 재정의한 hashCode는 Object의 API문서에 기술된 일반 규약을 따라야 하며, 서로 다른 인스턴스라면 되도록 해시코드도 서로 다르게 구현해야 한다.
		 * 이렇게 구현하기가 어렵지는 않지만 구글의 AutoValue프레임워크의 도움을 받을 수 있다.
		 * IDE들도 이런 기능을 일부 제공한다.
		 * 
		 * */
		
	}
}
