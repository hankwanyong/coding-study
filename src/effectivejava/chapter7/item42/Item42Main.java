package effectivejava.chapter7.item42;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleBinaryOperator;

public class Item42Main {

	/*
	 * Item42 익명 클래스보다는 람다를 사용하라
	 * 
	 * 람다 : 프로그래밍 언어에서 사용되는 개념으로 익명 함수를 지칭하는 용어 >> 단순하게 표현하는 방법
	 * 	- 사용 이유 : 코드의 간결성
	 *  - 람다 표현식이 길어지면 람다를 쓰지말자
	 *
	 * 자바 람다 : 
	 * 		1. JAVA SE 8부터 사용 가능
	 * 		2. 함수형 인터페이스의 메서드 정의에 사용
	 * 	- 함수형 인터페이스 : 
	 * 		1. 구현해야 할 추상 메소드가 하나만 정의된 인터페이스
	 * 		2. 애너테이션 : @FunctionalInterface
	 * 			구현해야할 인터페이스가 2개 이상이면 컴파일 오류
	 *  - 표현식 : 
	 *  	1.매개변수 화살표(->) 함수몸체
	 *  	2. 함수몸체가 단일 실행문이면 괄호{}를 생략 할 수 있다. (if문 처럼)
	 *  	3. 함수몸체가 return문으로만 구성되어있는 경우 괄호{}를 생략할 수 없다.
	 *  	4. 매개변수가 없는 경우 ()로 표현
	 *  	5. 매개변수가 한개인 경우 괄호() 생략 가능
	 *  	ex) x -> System.out.println(x);
	 *  	ex) () -> {return 1;}
	 *  	ex) x -> x+1
	 *  
	 *  - 사용 : 
	 *  	함수형 인터페이스는 구현해야할 메서드가 한개 이므로 이 메서드를 정의하는 형식
	 *  		
	 * 		 
	 */
	
	public static void main(String[] args) {
		
		List<String> words = Arrays.asList("aaaaa", "bb", "ccc", "ffff");
		
		// public static <T> void sort(List<T> list, Comparator<? super T> c)	>> 함수형 인터페이스를 매개변수로 받음
		// @FunctionalInterface public interface Comparator<T>	>> 함수형 인터페이스
		
		// 익명 클래식 표현
		Collections.sort(words, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return Integer.compare(s1.length(), s2.length());
			}
		});
		
		// 람다 표현
		Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
		// >> 코드가 더 간결하다!
		
		words.forEach(System.out::println);
		
		// 람다식의 매개변수에 타입은 컴파일러가 문맥을 살펴 추론해 준다
		// 타입추론이 안되는 경우를 제외하고 람다의 모든 매개변수 타입은 생략하자
		
		String thisTestStr = "thisTestStr";
		test(thisTestStr, new Consumer<String>() {

			@Override
			public void accept(String t) {
				System.out.println(t);
				System.out.println(this.getClass().getName());
			}
			
		});
		
		test(thisTestStr, s -> {
			System.out.println(s);
			// Cannot use this in a static context >> this >> Consumer???
//			System.out.println(this.getClass().getName());
		});
		
		// 람다에서의 this는 this키워드 바깥 인스턴스 
		// 익명 클래스의 this는 자신 인스턴스
		// >> 함수 객체가 자신을 참조해야 한다면 반드시 익명 클래스를 써야한다.
		
		// 람다를 직렬화하지 말자 >> 직렬화 형태가 구현별로 다를수 있다.
	}

	@SuppressWarnings("unchecked")
	public static void test(String s, Consumer<String> c) {
		c.accept(s);
	}
	
	public enum Operation {
		PULS("+") {
			public double apply(double x, double y) {return x+y;}
		},
		MINUS("-") {
			public double apply(double x, double y) {return x-y;}
		},
		TIMES("*") {
			public double apply(double x, double y) {return x*y;}
		},
		DIVIDE("/") {
			public double apply(double x, double y) {return x/y;}
		};
		
		private final String symbol;
		Operation(String symbol) {this.symbol = symbol;}
		@Override public String toString() {return symbol;}
		public abstract double apply(double x, double y);
	}
	
	public enum Operation2 {
		PULS("+", (x,y) -> x+y) ,
		MINUS("-", (x,y) -> x-y) ,
		TIMES("*", (x,y) -> x*y) ,
		DIVIDE("/", (x,y) -> x/y);
		
		private final String symbol;
		private final DoubleBinaryOperator op;
		// @FunctionalInterface public interface DoubleBinaryOperator >> 함수형 인터페이스
		Operation2(String symbol, DoubleBinaryOperator op) {
			this.symbol = symbol;
			this.op = op;
		}
		@Override public String toString() {return symbol;}
	}
	
	

}
