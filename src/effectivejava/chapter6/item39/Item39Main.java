package effectivejava.chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 명명 패턴보다 애너테이션을 사용하라
 * @author 박민영
 *
 */
public class Item39Main {
	/*
	 * 도구나 프레임워크가 특별히 다뤄야 할 프로그램 요소에는 딱 구분되는 명명패턴을 적용 해 왔다.
	 * 명명패턴의 단점
	 * 1. 오타가 나면 안된다
	 * 2. 올바른 프로그램 요소에서만 사용되리라 보증할 방법이 없다.
	 * 3. 프로그램 요소를 매개변수로 전달할 마땅한 방법이 없다.
	 * 
	 * 애너테이션은 명명패턴의 단점을 해결 해 준다.
	 */
	public static void main(String[] args) throws Exception {
		//특정 애너테이션을 달고있는 메서드 테스트
		int tests = 0;
		int passed = 0;
		Class<?> testClass = Class.forName("effectivejava.chapter6.item39.Sample");
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(Test.class)) {
				tests ++;
				try {
					m.invoke(null);
					passed++;
				} catch (InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					System.out.println(m+" 실패:" +exc);
				} catch (Exception e) {
					System.out.println("잘못 사용한 @Test: "+m);
				}
			}
		}
		System.out.printf("성공: %d, 실패: %d%n",passed,tests-passed);
		
		//매개변수 하나를 받는 애너테이션 타입
		tests = 0;
		passed = 0;
		testClass = Class.forName("effectivejava.chapter6.item39.Sample2");
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(ExceptionTest.class)) {
				tests ++;
				try {
					m.invoke(null);
					System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n",m);
				} catch (InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();
					if(excType.isInstance(exc)) {
						passed++;
					} else {
						System.out.printf("테스트 %s 실패: 기대한 예외 %s, 발생한 예외 %s%n",m,excType.getName(),exc);
					}
				} catch (Exception e) {
					System.out.println("잘못 사용한 @ExceptionTest: "+m);
				}
			}
		}
		System.out.printf("성공: %d, 실패: %d%n",passed,tests-passed);
		
		//배열 매개변수를 받는 애너테이션 타입
		tests = 0;
		passed = 0;
		testClass = Class.forName("effectivejava.chapter6.item39.Sample3");
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(ExceptionTest2.class)) {
				tests ++;
				try {
					m.invoke(null);
					System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n",m);
				} catch (InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					int oldPass = passed;
					Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionTest2.class).value();
					for(Class<? extends Throwable> excType : excTypes) {
						if(excType.isInstance(exc)) {
							passed++;
							break;
						}
					}
					if(passed == oldPass) {
						System.out.printf("테스트 %s 실패: %s%n",m,exc);
					}
				} catch (Exception e) {
					System.out.println("잘못 사용한 @ExceptionTest2: "+m);
				}
			}
		}
		System.out.printf("성공: %d, 실패: %d%n",passed,tests-passed);
		
		//반복가능한 애너테이션
		tests = 0;
		passed = 0;
		testClass = Class.forName("effectivejava.chapter6.item39.Sample4");
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(ExceptionTest3.class) || m.isAnnotationPresent(ExceptionTestContainer.class)) {
				tests ++;
				try {
					m.invoke(null);
					System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n",m);
				} catch (InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					int oldPass = passed;
					ExceptionTest3[] excTests = m.getAnnotationsByType(ExceptionTest3.class);
					for(ExceptionTest3 excTest : excTests) {
						if(excTest.value().isInstance(exc)) {
							passed++;
							break;
						}
					}
					if(passed == oldPass) {
						System.out.printf("테스트 %s 실패: %s%n",m,exc);
					}
				} catch (Exception e) {
					System.out.println("잘못 사용한 @ExceptionTest3: "+m);
				}
			}
		}
		System.out.printf("성공: %d, 실패: %d%n",passed,tests-passed);
		
		/*
		 * 애너테이션으로 할 수 있는 일을 명명패턴으로 처리할 이유는 없다.
		 * 자바 프로그래머라면 예외없이 자바가 제공하는 애너테이션 타입들은 사용해야한다.
		 */
	}
}
