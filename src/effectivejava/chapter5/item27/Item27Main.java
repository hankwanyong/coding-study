package effectivejava.chapter5.item27;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Item27Main {
	/*
	 *비검사 경고를 제거하라
	 * javac 인수 => -xLint:uncheck
	 * 타입 안전하다고 확신 : @SuppressWarnings("unchecked")
	 *                   - 항상 가능한 한 좁은 범위에 적용!
	 *                   - 경고를 무시해도 안전한 이유를 항상 주석으로 남겨야한다.
	 *                  
	 */

	
	public static void main(String[] args) {
		//사용 예
		Set<Integer> ex1 = new HashSet<>();
		ArrayList a= new ArrayList();
		a.toArray();
	}
    

	/*
	 * **** 핵심정리 ****
	 * 비검사 경고는 중요하니 무시하지 말자. 
	 * 모든 비검사 경고는 런타임에 classCastException을 일으킬 수 있는 잠재적 가능성을 뜻하니 최선을 다해 제거하라.
	 * 안되면 최대한 제한적으로 @SuppressWarnings("unchecked") 애너테이션으로 경고를 숨겨라.
	 */
}
