package effectivejava.chapter7.item43;

import java.util.function.Consumer;

public class Item43Main {

	/*
	 * Item43 람다보다는 메서드 참조를 사용하라
	 * 
	 * 메서드 참조 표현식( :: - 이중 콜론 연산자)
	 * 		- 람다식에서 파라미터를 중복해서 사용하지 않기 위해 사용
	 * 
	 * 사용 방법 : 
	 * 		[인스턴스]::[메소드명(또는 new)]
	 * 
	 */
	public static void main(String[] args) {

		String aa = "test String";
		// ex
		test(aa, x -> System.out.println(x));
		test(aa, System.out::println);
		
		// 메서드 참조 유형은 261page 참고 (e-book 281)
	}

	@SuppressWarnings("unchecked")
	public static void test(String s, Consumer<String> c) {
		c.accept(s);
	}
}
