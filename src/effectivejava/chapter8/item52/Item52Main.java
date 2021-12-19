package effectivejava.chapter8.item52;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Item52Main {
	/* 
	 *  아이템52
	 *  다중정의는 신중히 사용하라
	 */
	

	public static void main(String[] args) {
		
		//예) 1.Overloading
		Collection<?>[] collections = {
				new HashSet<String>()
				, new ArrayList<BigInteger>()
				, new HashMap<String, String>().values()
		};
		
		for(Collection<?> c : collections) {
			System.out.println(CollectionClassifier.classify(c));
			
			//오버로딩 된 세 classify 중 어느 메서드를 호출할지 컴파일타임에 정해지기 때문에 
			// for문안의 c는 항상 Collection<?> 타입이다.		
		}
		System.out.println("=======================================");
		
		// 2. 재정의한 매서드는 동적으로 선택되고, 다중정의한 메서드는 정적으로 선택된다.
		//예) Wine Overriding
		List<Wine> wineList = List.of(new Wine(), new SparklingWine(), new Champagne());
		
		for(Wine wine : wineList) {
			System.out.println(wine.name());
		}
		System.out.println("=======================================");
		
		
		//3. CollectionClassifier 해결책
		// 모든 classify 메서드를 하나로 합친 후 instanceof로 명시적 검사를 한다.
		
		for(Collection<?> c : collections) {
			System.out.println(CollectionClassifier.classify2(c));		
		}
		System.out.println("=======================================");
		
		/* 다중정의가 혼돈을 일으키는 상황을 피해야 한다.
		 * 
		 * 정확히 어떻게 사용했을 때 다중정의가 혼란을 주느냐에 대해서는 논란의 여지가 있다.
		 * 안전하고 보수적으로 가려면 매개변수 수가 같은 다중정의는 만들지 말자.
		 * 가변인수를 사용하는 매서드라면 다중정의를 아예 하지 말아야 한다.
		 * -> 간편하게 다중정의하는 대신 메서드 이름을 다르게 지어주면 된다.
		 * 
		 */
		
		//4. List.remove 문제
		
		Set<Integer> set = new TreeSet<>();
		List<Integer> list = new ArrayList<>();
		
		for(int i = -3; i< 3; i++) {
			set.add(i);
			list.add(i);
		}
		
		for (int i = 0; i < 3; i++) {
			set.remove(i);
			list.remove(i);
		}
		
		System.out.println(set + " " + list);
		System.out.println("=======================================");
		
		// set.remove(i)는 remove(Object)이다.
		// list.remove(i) 다중정의된 remove(int index)를 선택한다.
		// -> list.remove 인수를 Integer로 형변환하여 object 삭제를 한다.
		
		Set<Integer> set2 = new TreeSet<>();
		List<Integer> list2 = new ArrayList<>();
		
		for(int i = -3; i< 3; i++) {
			set2.add(i);
			list2.add(i);
		}
		
		for (int i = 0; i < 3; i++) {
			set2.remove(i);
			list2.remove(Integer.valueOf(i));
		}
		
		System.out.println(set2 + " " + list2);
		System.out.println("=======================================");
		
		// 다중정의 x 다중정의 문제
		new Thread(System.out::println).start();
		ExecutorService exec = Executors.newCachedThreadPool();
		//exec.submit(System.out::println);  //submit 와 println 다중정의 충돌.현상
		
		// 자바 라이브러리도 실패한 클래스가 있다.
		// String클래스의 valueOf
		char[] a = {'a', 'b', 'c'};
		Object b = (Object) a;
		System.out.println("String.valueOf(char[]) : " + String.valueOf(a));
		System.out.println("String.valueOf(Object) : " + String.valueOf(b));
		
		/*핵심저일
		 *  일반적으로 매개변수 수가 같을 때는 다중정의를 피하는게 좋다.
		 *  특히 생성자라면 이 조언을 따르기가 불가능할 수 있다.
		 *  그럴 때는 헷갈릴 만한 매개변수는 형변환하여 정확한 다중정의 메서드가 선택되도록 해야 한다.
		 *  이것이 불가능하면, 기존 클래스를 수정해 새로운 인터페이스를 구현해야 할 때는 같은 객체를 입력받는
		 *  다중정의 메서드들이 모두 동일하게 동작하도록 만들어야 한다.
		 */
		
	}   
}
