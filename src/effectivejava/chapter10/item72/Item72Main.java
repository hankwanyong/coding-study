package effectivejava.chapter10.item72;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Optional;

public class Item72Main {

	/*
	 * [아이템72] 표준 예외를 사용하라
	 * 
	 * IllegalArgumentException
	 * 호출자가 인수로 부적절한 값을 넘길 때 던지는 예외.
	 * 
	 * IllegalStateException
	 * 대상 객체의 상태가 호출된 메서드를 수행하기에 적합하지 않을 때 주로 던진다.
	 * 예컨대 제대로 초기화되지 않은 객체를 사용하려 할 때 던질 수 있다.
	 * 
	 * null -> NullPointerException
	 * 허용하지 않은 시퀀스 -> IndexOutOfBoundsException
	 * 
	 * ConcurrentModificationException
	 * 단일 스레드에서 사용하려고 설계한 객체를 여러 스레드가 동시에 수정하려 할 때.
	 * 
	 * UnsuppertedOperationException
	 * 클라이언트가 요청한 동작을 대상 객체가 지원하지 않을 때.
	 * 
	 * Exception, RuntimeException, Throwable, Error는 직접 재사용하지 말자.
	 * 다른 예외들의 상위 클래스이므로, 여러 성격의 예외들을 포괄하는 클래스이므로 안정적으로 테스트 할 수 없다.
	 */

	public static void main(String[] args) {
		
	}

}
