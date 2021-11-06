package effectivejava.chapter6.item37;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ordinal 인덱싱 대신 EnumMap을 사용하라.
 * @author 박민영
 *
 */
public class Item37Main {
	
	
	public static void main(String[] args) {
		
		//정원에 심은 식물을 배열 하나로 관리하고 이들을 생애주기별로 묶어보자
		//ordinal을 사용한 코드
		Plant[] garden = new Plant[0];
		
		Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];
		for(int i = 0 ; i<plantsByLifeCycle.length;i++){
			plantsByLifeCycle[i]  = new HashSet<>();
		}
		
		for(Plant p : garden) {
			plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
		}
		
		//결과출력
		for(int i = 0; i<plantsByLifeCycle.length;i++) {
			System.out.printf("%s : %s%n",Plant.LifeCycle.values()[i],plantsByLifeCycle[i]);
		}
		
		/*
		 * ordinal 값을 index로 사용하면 생기는 문제점
		 * 
		 * 1. 배열은 제네릭과 호환되지 않으니 비검사 형변환을 수행해야 하고 깔끔히 컴파일 되지 않을 것이다.
		 * 2. 배열은 각 인덱스의 의미를 모르니 출력결과에 직접 레이블을 달아야 한다.
		 * 3. 정확한 정숫값을 사용한다는 것을 직접 보증해야 한다.
		 * 
		 */
		
		//EnumMap을 사용한 코드
		Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle2 = new EnumMap<>(Plant.LifeCycle.class);
		for(Plant.LifeCycle lc : Plant.LifeCycle.values()) {
			plantsByLifeCycle2.put(lc, new HashSet<>());
		}
		
		for(Plant p : garden) {
			plantsByLifeCycle2.get(p.lifeCycle).add(p);
		}
		
		System.out.println(plantsByLifeCycle2);
		
		/*
		 * EnumMap을 사용하지 않고 Stream을 이용할 수 도 있다.
		 * 
		 * 배열의 인덱스를 얻기 위해 ordinal을 쓰는 것은 일반적으로 좋지 않으니, 대신 EnumMap을 사용하라.
		 * 다차원 관계는 EnumMap<..., EnumMap<...>> 으로 표현하라.
		 * "애플리케이션 프로그래머는 Enum.ordianl을 (웬만해서는) 사용하지 말아야 한다."는 일반 원칙의 특수한 사례다.
		 */
	}
}
