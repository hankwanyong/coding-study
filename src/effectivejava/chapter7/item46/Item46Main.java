package effectivejava.chapter7.item46;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Item46Main {
	
	/*
	 * Item 46 스트림에서는 부작용 없는 함수를 사용하라.
	 * 
	 * stream은 단순한 API가 아니라 함수형 프로그래밍에 기초한 패러다임이다. 
	 * 따라서 API만 익히는 것이 아니라 패러다임도 받아들여야 한다. 
	 * 각 변환 단계에서는 재구성을 하는데, 이전 단계의 결과를 받아 처리하는 순수 함수이어야 한다. 
	 * 순수함수란 입력만이 결과에 영향을 주는 함수를 말하며, 다른 가변 상태를 참조하지 않고, 함수도 외부의 상태를 변경하지 않는다.
	 * 
	 * 아래 코드는 스트림 패러다임을 이해하지 못한 채 API만 사용한 경우이다.
	 */
//	코드 46-1 스트림 패러다임을 이해하지 못한 채 API만 사용했다 - 따라하지 말 것!
//	Map<String, Long> freq = new HashMap<>(); 
//	try (Stream<String> words = new Scanner(file).tokens()) { // tokens은 java9부터 지원
//		words.forEach(word -> { 
//			freq.merge(word.toLowerCase(), 1L, Long::sum); 
//		}); 
//	}

	/*
	 * 이 코드는 스트림을 가장한 반복적 코드이며, 스트림의 이점을 살리지 못했다. 
	 * 종단연산인 forEach는 스트림 계산 결과를 보고할 때만 사용하고, 계산할 때는 사용치 말자. 
	 * - 종단연산(terminal operation) : forEach(Consumer<? super T> consumer) : Stream의 요소를 순회
	 * 아래는 스트림 패러다임을 이해하고 제대로 활용한 코드이다.
	 */
//	코드 46-2 스트림을 제대로 활용해 빈도표를 초기화한다.
//	Map<String, Long> freq; 
//	try (Stream<String> words = new Scanner(file).tokens()) {
//		freq = words.collect(groupingBy(String::toLowerCase, counting())); 
//	}
	/*
	 * 코드 46-2에서는 Scanner의 스트림메서드인 tokens를 사용해 스트림을 얻었다.
	 * tokens는 자바 9부터 지원하므로, 그 이전 버전을 쓰는 사람은 어댑터를 이용하여 (Iterator를 구현한) Scanner를 스트림으로 변환할 수 있다.
	 * 코드 47-3에서 사용하는 streamOf(Iterable<E>)가 좋은 예이다.
	 */

	/*
	 * 46.1. Collectors 
	 * 스트림을 잘 사용하려면 알아야하는 개념이다. 
	 * 종단연산으로 foreach같은 계산보단 Collectors를 권장한다. 
	 * Collector 를 사용하면 스트림의 원소를 컬렉션으로 쉽게 모을 수 있다. 
	 * 간단한 API로는 toList, toSet, toCollection 등이 있다. 이외에는 아래에서 살펴보자.
	 */
	//46-3 빈도표에서 가장 흔한 단어 10개를 뽑아내는 파이프라인
//	List<String> topTen = freq.keySet().stream().sorted(comparing(freq::get).reserved()).limit(10).collect(toList());
	//마지막 toList는 Collectors의 메서드다. 이처럼 Collectors의 멤버를 정적 임포트하여 쓰면 스트림 파이프라인 가독성이 좋아져, 흔히들 이렇게 사용한다.
	
	/*
	 * 46.1.1. toMap
	 * Collector<T,?,Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T,? extends U> valueMapper)
	 * keyMapper : 스트림의 요소 중 Key를 생성하는 함수
	 * keyMapper : 스트림의 요소 중 Value를 생성하는 함수
	 * 스트림 요소들이 key를 중복해서 사용하면 IllegalStateException 을 던진다.
	 * Collector<T,?,Map<K,U>> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction)
	 * mergeFunction : 위처럼 충돌이 날 때 해결해주는 병합 함수이다. key값이 중복되는 값들은 이 함수을 이용해 결과를 낸다.
	 */
	 Stream<String> s = Stream.of("apple", "banana", "apricot", "orange", "apple");
	 Map<Character, String> m = s.collect(Collectors.toMap(s1 -> s1.charAt(0), s1 -> s1, (oldVal, newVal) -> oldVal + "|" + newVal));
	 // {a=apple|apricot|apple, b=banana, o=orange}
	 /*
	  * Collector<T,?,M> toMap(Function<? super T, ? extends K> keyMapper, Function<? super T,? extends U> valueMapper, BinaryOperator<U> mergeFunction, Supplier<M> mapSupplier)
	  * mapSupplier : Map Factory로서 Map 구현체를 직접 정할 수 있다. (기본적으론 HashMap 인 듯)
	  */
	 
	 /*
	  * 46.1.2. groupingBy
	  * SQL의 group by 와 유사한 기능을 제공한다.
	  * Collector<T,?,Map<K,List<T>>> groupingBy(Function<? super T, ? extends K> classifier)
	  * classifier : 분류 함수로 각 스트림의 요소를 입력받아 Map의 Key로 사용한다. 값은 List이다.
	  * Collector<T,?,Map<K,D>> groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T,A,D> downstream)
	  * downstream : 위에서 값이 List 이외의 타입을 갖게하고 싶을 때 사용한다.
	  */
	  Stream<String> s1 = Stream.of("apple", "banana", "orange");
	  Map<Integer, Long> map = s1.collect(Collectors.groupingBy(String::length, Collectors.counting()));
	  // {5=1, 6=2} . 만약 위의 경우였다면 {5=[apple], 6=[banana, orange]}
	  /*
	   * Collector<T,?,M> groupingBy(Function<? super T, ? extends K> classifier, Supplier<M> mapFactory, Collector<? super T,A,D> downstream)
	   * mapFactory : Map Factory로서 Map 구현체를 직접 정할 수 있다.
	   */
	  
	  /*
	   * 46.1.3. 기타
	   * groupingByConcurrent : HashMap 이 아닌 ConcurrentHashMap 으로 만들어준다.
	   * partitioningBy : groupingBy 와 비슷하지만, 분류함수(classifier) 자리에 Predicate를 받으며 Map의 Key 타입이 Boolean 이다.
	   * minBy, maxBy : Comparator를 이용해 스트림에서 가장 값이 작거나 큰 원소를 찾아 반환한다.
	   * joining : 문자열(CharSequence)에만 사용된다. 인자없는 joining은 단순히 연결을 하며, 1개 인자를 넣으면 구분자를 찍는다.
	   */

  /*
   * 핵심 정리
   * 스트림 파이프라인 프로그래밍의 핵심은 부작용 없는 함수 객체에 있다.
   * 스트림 뿐 아니라 스트림 관련 객체에 건네지는 모든 함수 객체가 부작용이 없어야 한다.
   * 종단 연산 중 forEach는 스트림이 수행한 계산 결과를 보고할 때만 이용해야 한다.
   * 계산 자체에는 이용하지 말자.
   * 스트림을 올바로 사용하려면 수집기를 잘 알아둬야한다.
   * 가장 중요한 수집기 팩터리는 toList, toSet, toMap, groupingBy, joining이다. 
   */
	
	/*
	 * 종단연산과 관련하여
	 * https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=spdlqjdudghl&logNo=220757598355 참고
	 */
}
