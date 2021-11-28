package effectivejava.chapter7.item48;

import java.math.BigInteger;
import java.util.stream.LongStream;

public class Item48Main {

	/*
	 * Item 48 스트림 병렬화는 주의해서 적용하라.
	 * 
	 * 병렬스트림은 .parallel() 로 쉽게 제공된다.
	 * 동시성 프로그래밍을 할 때는 안전성과 응답 가능 상태를 유지하기 위해 힘써야한다.
	 * 데이터 소스가 Stream.iterate 거나 중간 연산으로 limit을 쓰면 파이프라인 병렬화로는 성능개선을 기대할 수 없다.
	 * 	(limit를 다룰 때, CPU코어가 남는다면 우너소 몇개를 더 처리하고 버려도 상관없다고 가정함)
	 * 스트림 파이프라인을 막 병렬화하면 성능이 오히려 나빠질 수가 있다. 병렬화는 오직 최후 성능 최적화의 수단임을 명심해야한다.
	 * 병렬 스트림 파이프라인도 공통의 포크-조인풀(ForkJoinPool.commonPool)에서 수행되므로, 잘못된 파이프라인 하나가 다른 부분에도 영향을 미칠 수 있다.
	 * 
	 * 아래는 병렬화에 적합한 코드상황이다. 코드 48-2에서 parallel 호출을 하나 추가함.
	 */
	//코드 48-3 소수 계산 스트림 파이프라인-병렬화 버전.
	static long pi(long n) { 
		return LongStream.rangeClosed(2, n) 
				.parallel() // 이 한줄의 추가로 31초 -> 9초 단축 
				.mapToObj(BigInteger::valueOf) 
				.filter(i -> i.isProbablePrime(50)) 
				.count(); 
	}
	
	/*
	 * 48.1. 병렬스트림에 좋은 데이터소스들
	 * 대체로 소스가 ArrayList, HashMap, HashSet, ConcurrentHashMap, 배열, int, long 일 때가 병렬화의 효과가 가장 좋다.
	 * 이들은 데이터를 원하는 크기로 정확하고 손쉽게 나눌 수 있어서 다수의 스레드에 분배하기 좋기 때문이다.
	 * 또한 이들은 순차실행할 때 참조 지역성이 뛰어나다. 
	 * 이웃한 원소의 참조들이 메모리에 연속해서 저장되어 있기 때문이다. 
	 * 참조 지역성이 낮으면 스레드는 데이터가 주 메모리에서 캐시 메모리로 전송되어 오기를 기다리며 대부분의 시간을 IO wait 로 소비할 것이다.
	 * 
	 * 48.2. 병렬스트림에 효과있는 종단 연산들
	 * 순차연산은 효과가 한계가 있으며, 축소(reduction) 연산이 가장 효과가 좋다.
	 * 축소는 모든 원소를 하나로 합치는 작업으로, reduce, min, max, count, sum, average 등의 메소드가 있다.
	 * (예외) 가변 축소(mutable reduction)에는 collect, toArray가 있는데, 병렬화에 효과적이지 않는다.
	 * anyMatch, allMatch, noneMatch 등(조건에 맞으면 바로 반환되는 메소드들)에도 효과가 좋다.
	 * 병렬화의 이점을 제대로 누리고 싶으면 spliterator 메소드를 재정의하라.
	 * 
	 */
	
	/*
	 * 핵심정리
	 * 계산도 올바르게 수행하고 성능도 빨라질 거라는 확신 없이는 스트림 파이프라인 병렬화는 시도조차 하지말라.
	 * 스트림을 잘못 병렬화하면 프로그램을 오작동하게 하거나 성능을 급격히 떨어뜨린다.
	 * 병렬화하는 편이 낫다고 믿더라도, 수정 후의 코드가 여전히 정확한지 확인하고 운영 환경과 유사한 조건에서 수행해보며 성능지표를 유심히 관찰하라.
	 * 그래서 계산도 정확하고 성능도 좋아졌음이 확실해졌을 떄, 오직 그럴 때만 병렬화 버전 코드를 운영 코드에 반영하라.
	 */

}
