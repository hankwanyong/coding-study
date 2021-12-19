package effectivejava.chapter9.item57;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Item57Main {

	/*
	 * 아이템 57 지역변수의 범위를 최소화하라
	 * 
	 * 관습적으로 지역변수 선언을 코드 블록의 첫 머리에 선언하는 경우가 많다.
	 * 1. 지역변수는 '가장 처음 쓰일 때 선언하자'
	 * 		- 미리 선언부터 해두면 코드가 어수선해져 가독성이 떨어진다
	 * 		- 실제 사용 시점에 타입과 초깃값이 기억나지 않을 수도 있다.
	 * 2. 거의 모든 지역변수는 선언과 동시에 초기화해야 한다.
	 * 		- try-chtch나 반복문에서는 지역변수 선언을 주의하자
	 * 		- 반복문(for, while) 복붙 주의!
	 * 3. 메서드를 작게 유지하고 한 가지 기능에 집중하자.
	 * 		- 메서드를 기능별로 쪼개자
	 */
	
	
	public static void main(String[] args) {
		List<Integer> c = new ArrayList<>();
		List<Integer> c2 = new ArrayList<>();
		
		// 전통적인 for문
		for(Iterator<Integer> i = c.iterator(); i.hasNext();) {
			Integer e = i.next();
			// 로직...
		}
		
		

	}

}
