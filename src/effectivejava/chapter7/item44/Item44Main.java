package effectivejava.chapter7.item44;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Item44Main {

	/*
	 * Item 44 표준 함수형 인터페이스를 사용하라
	 * 
	 * 람다 표현식을 사용하기 위해서는 함수형 인터페이스가 필요하다
	 * 매번 함수형 인터페이스를 생성해서 사용하기 불편!!
	 * java.util.function >> 표준 함수형 인터페이스 활용하자
	 * 
	 * 
	 */
	
	UnaryOperator<String> unaryOperator; 		// T identity()
	BinaryOperator<String> binaryOperator; 		// T minBy(Comparator<? super T> comparator)
	Predicate<String> predicate;				// test(T t)
	Function<String, String> function;			// R apply(T t);
	Supplier<String> supplier;					// T get();
	Consumer<String> consumer;					// void accept(T t);
	// 필요한 용도에 맞는게 있다면 사용!
	
	// 다른 표준 함수형 인터페이스는 명명 위에 기본 인터페이스에서 명명 규칙으로 적당히 구분가능 
	// >> 보통 박스타입이 아닌 기본타입에 대한 명명규칙
	// ex Supplier			T get();
	// ex)IntSupplier	  	int getAsInt(); 
	  
	// Bi인터페이스 >> 두개의 인자를 받는다
	// ex) Consumer<T>			void accept(T t);
	// ex) BiConsumer<T, U>		void accept(T t, U u);
	
	// 직접 만든 함수형 인터페이스에는 항상 @FunctionalInterface 애너테이션을 사용하자!
	
//	@FunctionalInterface
//	interface AB {
//		int a();
//		int b();
//	}

}
