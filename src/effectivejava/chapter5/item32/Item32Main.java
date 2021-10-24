package effectivejava.chapter5.item32;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Item32Main {
	/*
	 * 제네릭과 가변인수를 함께 쓸 때는 신중하라         
	 *  
	 */
	
	//가변인수
	public static void exVarargs(String ... str){
	  	//내부적으로 배열을 생성해서 사용한다.
		for(String a : str) {
			System.out.println(a);
		}
		 
	}	 
	
	//제네릭과 varargs(가변인수)를 혼용하면 타입 안전성이 깨진다.
	static void dangerous(List<String>...stringLists) {
		List<Integer> intList = List.of(42);
		Object[] objects = stringLists;
		objects[0] = intList; //힙 오염 발생
		String s = stringLists[0].get(0); //classCastException
		
	}
	
	// 제네릭 배열을 프로그래머가 직접 생성하는 건 허용하지 않으면서 varargs 매개변수를 받는 메서드는 선언 가능함.
	// 제네릭이나 매개변수화 타입의 varargs 매개변수를 받는 메서드가 실무에서 매우 유용하다.
	// Arrays.asList<T... a), collection.addAll(Collection<? super T> c, T... elemnets)
	
	// 자바7에서는 @SafeVarargs 애너테이션이 추가되어 제네릭 가변인수 메서드 작성자가 클라이언트 측에서 발생하는 경고를 숨길 수 있게 되었다.
	// @SafeVarargs 애너테이션은 메서드 작성자가 그 메서드가 타입 안전함을 보장하는 장치다.
	// 안전함의 기준 : 메서드가 이 배열에 아무것도 저장하지 않고 그 배열의 참조가 밖으로 노출되지 않는다면 안전하다. 순수하게 인수들을 전달만 하는 경우.
	
	//안전하지 않음.	
	public static <T> T[] toArray(T... args) {
		return args; //반환시점에 컴파일에 타입결정이 되므로 컴파일러에게 충분한 정보고 주어지지 않아 타입을 잘 못 판단할 수 있다.
	}

	public static <T> T[] pickTwo(T a, T b, T c) {
		switch(ThreadLocalRandom.current().nextInt(3)) {
		case 0: return toArray(a, b);
		case 1: return toArray(a, b);
		case 2: return toArray(a, b);
		}
		
		throw new AssertionError(); //도달 불가.
	}
		
	
	public static void main(String[] args) {
		try {
			String[] attributes = pickTwo("좋은", "빠른", "저렴한");
		} catch ( Exception e) {
			e.printStackTrace();
		}
		// 제네릭 varags 매개변수 배열에 다른 메서드가 접근하도록 허용하면 안전하지 않다!
		

	}
	
	//제네릭 varargs 매개변수를 안전하게 사용하는 메서드
	@SafeVarargs
	public static <T> List<T> flatten(List<? extends T>... lists) {
		List<T> result = new ArrayList<>();
		for(List<? extends T> list : lists) {
			result.addAll(list);
		}
		return result;
	}
	
	/*
	 * 1. 안전하지 않은 varargs 메서드는 절대 작성해서는 안된다.
	 * 2. 통제할 수 있는 메서드 중 제네릭 varargs 매개변수를 사용하면 힙 오염 경고가 뜨는 메서드가 있다면, 안전한지 점검해라.
	 *  - varargs 매개변수 배열에 아무것도 저장하지 않는다.
	 *  - 그 배열(혹은 복사본)을 신뢰할 수 없는 코드에 노출하지 않는다.
	 *  
	 *  @SafeVarargs 애너테이션은 재정의할 수 없는 메서드에만 달아야한다. 
	 *  자바8에서 이 애너테이션은 오직 정적 메서드, final 인스턴ㅇ스 메서드에서만 붙일 수 있다.
	 *  자바9부터는 private 인스턴스 메서드에도 허용된다.
	 *  
	 *  @SafeVarargs 애너테이션이 유일한 정답은 아니다. varargs 매개변수를 List 매개변수로 바꿔서 타입 안전하게 사용할 수 있다.
	 */
   
}
