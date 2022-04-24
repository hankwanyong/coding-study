package effectivejava.chapter6.item36;

import java.util.EnumSet;

import effectivejava.chapter6.item36.Text.Style;

/**
 * 비트필드 대신 EnumSet을 사용하라
 * @author 박민영
 *
 */
public class Item36Main {
	/*
	 * 열거한 값들이 집합으로 사용될 경우, 예전에는 각 상수에 서로 다른 2의 거듭제곱값을 할당한 정수 열거 패턴을 사용했다.
	 */
	public static void main(String[] args) {
		//다음과 같은 식으로 비트별 OR를 사용해 여러 상수를 하나의 집합으로 모을 수 있으며, 이렇게 만들어진 집합을 비트필드라 한다.
		Text.applyStyles(Text.STYLE_BOLD | Text.STYLE_ITALIC);
		
		/*
		 * 비트 필드는 정수 열거 상수의 단점을 그대로 지닌다.
		 * 비트 필드 값이 그대로 출력되면 단순한 정수 열서 상수를 출력할 때 보다 해석하기가 훨씬 어렵다. 
		 * API 작성시 미리 예측하여 적절한 타입(보통은 int 또는 long)을 선택해야 한다.
		 * 
		 * 비트필드 대신 EnumSet을 사용하자.
		 * */
		
		Text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
		Text.applyStylesOROper(EnumSet.of(Style.BOLD, Style.ITALIC));
		
		/*
		 * 열거할 수 있는 타입을 한데 모아 집합형태로 사용한다고 해도 비트 필드를 사용할 이유는 없다.
		 * EnumSet 클래스가 비트 필드 수준의 명료함과 성능을 제공하고 아이템 34에서 설명한 열거타입의 장점까지 선사하기 때문이다.
		 * EnumSet 의 유일한 단점이라면(자바 9까지는 아직) 불변 EnumSet을 만들 수 없다는 것이다.
		 * 그래도 향후 릴리스에서는 수정되리라 본다.
		 * 그때까지는 Collections.unmodifiableSet으로 EnumSet을 감싸 사용할 수 있다. 
		 * */
	}
}
