package effectivejava.chapter5.item26;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Item26Main {
	/*
	 * ****제네릭(Generic)****
	 * - 데이터 형식에 의존하지 않고, 하나의 값이 여러 다른 데이터 타입들을 가질 수 있도록 하는 방법.
	 * 
	 * 일상에서 제네릭이 아닌 형태를 쓰는 경우 (유연성이 떨어짐)
	 * ArrayList<String> arr = new ArrayList<String>();  
	 * 
	 * 지정된 타입이 아니라 타입의 경계를 지정하고 컴파일러가 자동 형변환을 하도록하는 방식.
	 * 다소 코드가 복잡해질 수 있다.
	 * 
	 * <T> : Type
	 * <E> : Element
	 * <K> : Key
	 * <V> : Value
	 * <N> : Number
	 * -> 보통 사람들이 많이 쓰는 형태
	 * 
	 * 예)
	 * public class ClassName<T, K>{}
	 * public Interface InterfaceName<T, K>{}
	 * public class HashMap <K, V> 
	 * 
	 */
	public static class ClassName<T, K> {};
	
	public static void main(String[] args) {
		//사용 예
		ClassName<String, Integer> a = new ClassName<String, Integer>();
	}
    
	/*
	 * **** 로타입(raw type)****
	 * List<E> --로타입--> List
	 * 제네릭이 도래하기전 호환을 위한 것.
	 */
	
	
	/*
	 * 비한정적 와일드카드 <?>
	 * 와일드카드 타입은 안전하고 로 타입은 안전하지 않다.
	 * 
	 * class 리터럴에는 로 타입을 써야 한다.
	 * 허용 : List.class, String[].class, int.class 
	 * 미허용 : List<String>.class, List<?>.class 
	 */
	
	//비한정적 와일드카드 타입을 사용하라
	static void numElementsInCommon(Set<?> s1, Set<?> s2) {}
	
	//로 타입을 써도 좋은 예 instanceof
	public static void rawTypeInstanceof(Set o) {
		if(o instanceof Set) {
			Set<?> s = (Set<?>) o;
		}
	}
	
	/*
	 *  **** 핵심정리 ****
	 *  로 타입을 사용하면 런타임에 예외가 일어날 수 있으니 사용하면 안 된다.
	 *  Set<Object> Set<?> 안전
	 *  Set 안전하지 않음
	 */


	/**
	 * Object 와 와일드 카드 차이
	 */

	public void test(List<Object> list){
		for(Object o : list){
			System.out.println(o);
		}
	}

	public void test2(List<?> list){
		for(Object o : list){
			System.out.println(o);
		}
	}

	public void sample() {
		List<Object> listO = new ArrayList<>();
		List<String> listS = new ArrayList<>();
		List<?> lista = new ArrayList<>();


//		test(listS);		// 컴파일 에러
		test2(listS);		// 컴파일 성공

		/*
		String은 Object의 하위 타입이지만
		List<String>은 List<Object>의 하위 타입이 아니다.
		그렇다고 와일드카드 제네릭이 List<String>의 상위 타입은 아니다.
		어떤 타입이든 사용가능 하도록 만든것이 와일드카드.
		 */

	}


}
