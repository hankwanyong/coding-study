package effectivejava.chapter5.item30;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;

public class Item30Main {
	/*
	 * 이왕이면 제네릭 메서드로 만들라              
	 */
	
	//제네릭 메서드
	public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
		Set<E> result = new HashSet<>(s1);		
		result.addAll(s2);		
		
		//java.util.List<E>
		return result;
	}

	
	public static void main(String[] args) {
		
		//제네릭 메서드 활용
		Set<String> guys = Set.of("톰", "딕", "해리");
		Set<String> stooges = Set.of("래리", "모에", "컬리");
		Set<String> aflCio = union(guys, stooges);
		System.out.println(aflCio);

	}
    
	//제네릭 싱글턴 팩터리 패턴
	private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;
	
	@SuppressWarnings("unchecked")
	public static <T> UnaryOperator<T> identityFunction() {
		return (UnaryOperator<T>) IDENTITY_FN;
	}

	//재귀적 타입 한정 
	// <E extends Comparable<E>> 모든 타입 E는 자신과 비교할 수 있다
	public static <E extends Comparable<E>> E max(Collection<E> c) {
		if(c.isEmpty()) {
			throw new IllegalArgumentException("c is empty");
		}
		
		E result = null;
		
		for(E e: c) {
			if(result == null || e.compareTo(result) > 0) {
				result = Objects.requireNonNull(e);
			}
		}
		
		return result;
	}
	
	/*
	 * 핵심정리
	 * 클라이언트에서 입력 매개변수와 반환값을 명시적으로 형변환해야 하는 메서드보다 제네릭 메서드가 더 안전하며
	 * 사용하기도 쉽다.
	 */
}
