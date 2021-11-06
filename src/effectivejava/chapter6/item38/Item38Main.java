package effectivejava.chapter6.item38;

import java.util.Arrays;
import java.util.Collection;

/**
 * 확장할 수 있는 열거 타입이 필요하면 인터페이스를 사용하라
 * @author 박민영
 *
 */
public class Item38Main {
	
	/*
	 * 열거타입은 확장이 불가능하다.
	 * 확장이 가능한 열거타입을 만들 필요가 있다면 인터페이스를 활용할 수 있다.
	 */
	
	public static void main(String[] args) {
		
		System.out.println(BasicOperation.MINUS.apply(10, 2));
		System.out.println(ExtendedOperation.EXP.apply(10, 2));
		
		/*
		 * 열거타입인 BasicOperation은 확장할 수 없지만 인터페이스인 Operation은 확장이 가능하고, 이 인터페이스를 연산의 타입으로 사용하면 된다.
		 * 
		 * 개별 인스턴스 수준에서뿐 아니라 타입수준에서도, 기본 열거타입 대신 확장된 열거타입을 넘겨 확장된 열거타입의 원소를 모두 사용하게 할 수도 있다.
		 */
		
		double x = 10d;
		double y = 2d;
		test(ExtendedOperation.class, x, y);
		test(Arrays.asList(ExtendedOperation.values()), x, y);
		
		/*
		 * 인터페이스를 이용했을때의 문제점
		 * 1. 열거타입끼리 구현을 상속할 수 없다. - Operation 예는 연산 기호를 저장하고 찾는 로직이 BasicOperation, ExtendedOperation모두 들어가야만 한다.
		 * 
		 */
		
		
		
		/*
		 * 열거 타입 자체는 확장할 수 없지만, 인터페이스와 그 인터페이스를 구현하는 기본 열거 타입을 함께 사용해 같은 효과를 낼 수 있다.
		 * 이렇게 하면 클라이언트는 이 인터페이스를 구현해 자신만의 열거 타입(혹은 다른 타입)을 만들 수 있다.
		 * 그리고 API가 (기본 열거 타입을 직접 명시하지 않고) 인터페이스 기반으로 작성되었다면 기본 열거 타입의 인스턴스가 쓰이는 모든 곳을 새로 확장한 열거타입의 인스턴스로 대체해 사용할 수 있다.
		 */
	}
	
	//제네릭을 활용
	private static <T extends Enum<T> & Operation> void test(
			Class<T> opEnumType, double x, double y) {
		for(Operation op : opEnumType.getEnumConstants()) {
			System.out.printf("%f %s %f = %f%n",x,op,y,op.apply(x, y));
		}
	}
	
	//한정적 와일드카드 타입 사용
	private static void test(Collection<? extends Operation> opSet, double x, double y ) {
		for(Operation op : opSet) {
			System.out.printf("%f %s %f = %f%n",x,op,y,op.apply(x, y));
		}
	}
}
