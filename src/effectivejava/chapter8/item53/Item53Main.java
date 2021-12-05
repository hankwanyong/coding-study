package effectivejava.chapter8.item53;

/**
 * 가변인수는 신중히 사용하라.
 * 
 * @author 박민영
 *
 */
public class Item53Main {
	
	/*
	 * 간단한 가변인수 활용 예
	 * */
	
	static int sum(int... args) {
		int sum = 0;
		for(int arg : args)
			sum+=arg;
		return sum;
	}
	
	/*
	 * 인수가 1개 이상이어야 하는 가변인수 메서드 - 잘못구현한 예
	 * 
	 * 아래 코드의 문제점
	 * 1. 인수를 0개 넣어 호출하면 (컴파일 타임이 아닌) 런타임에 실패하게 된다.
	 * 2. args 유효성 검사를 명시적으로 해야한다.
	 * 3. min의 초깃값을 Integer.MAX_VALUE로 설정하지 않고는 (더 명료한) for-each문도 사용할 수 없다.
	 * 
	 * */
	static int min(int... args) {
		if(args.length == 0) {
			throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
		}
		int min = args[0];
		for(int i = 0;i<args.length;i++) {
			if(args[i] < min) {
				min = args[i];
			}
		}
		return min;
	}
	
	
	/*
	 * 인수가 1개 이상이어야 할 때 가변인수를 제대로 사용하는 방법
	 * */
	
	static int min(int firstArg, int... remainingArgs) {
		int min = firstArg;
		for(int arg : remainingArgs)
			if(arg < min)
				min = arg;
		return min;
	}
	
	/*
	 * 성능에 민감한 상황이라면 가변인수가 걸림돌이 될 수 있다.
	 * 가변인수 메서드는 호출될 때마다 배열을 새로 하나 할당하고 초기화 하기 때문.
	 * 비용을 감당 할 수는 없지만 가변인수의 유연성이 필요할 때 선택하는 패턴
	 * */
	
	public void foo() {}
	public void foo(int a1) {}
	public void foo(int a1, int a2) {}
	public void foo(int a1, int a2, int a3) {}
	public void foo(int a1, int a2, int a3, int... rest) {}
	
	/*
	 * 위 메서드의 95%가 인수 3개 이하로 사용한다고 하면
	 * 메서드 호출중 5%만이 배열을 생성한다.
	 * 꼭 필요한 상황에서만 사용하자.
	 * EnumSet의 정적 팩터리도 이 기법을 사용해 열거 타입 집합 생성 비용을 최소화 한다.
	 * */
	
	public static void main(String[] args) {
		/* 인수 개수가 일정하지 않은 메서드를 정의해야 한다면 가변인수가 반드시 필요하다.
		 * 메서드를 정의할 때 필수 매개변수는 가변인수 앞에 두고, 가변인수를 사용할 때는 성능 문제까지 고려하자. 
		 */
	}
	
}
