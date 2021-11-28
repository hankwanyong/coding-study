package effectivejava.chapter4.item23;

public class Item23Main{

	/*
	 * Item23 태그 달린 클래스보다는 클래스 계층구조를 활용하라.
	 * 
	 * 태그달린 클래스는 2가지 이상의 값을 표현하는 클래스이다. 값의 의미 분기처리를 태그로 한다.
	 */

	//코드 23-1 태크달린 클래스 - 클래스 계층구조보다 훨씬 나쁘다!
//	Figure;
	//불필요한 코드들이 너무 많고, 장황하며, 오류를 내기 쉽고 비효율적이다.
	//이런것들은 클래스 계층구조로 바꾸는 것이 좋다.

	//코드 23-2 태크달린 클래스를 클래스 계층구조로 변환
	abstract class Figure { 
		abstract double area(); 
	} 
	
	class Circle extends Figure { 
		final double radius; 
		Circle(double radius) { this.radius = radius; } 
		
		@Override 
		double area() { 
			return Math.PI * (radius * radius); 
		} 
	} 
	
	class Rectangle extends Figure { 
		final double length; 
		final double width; 
		
		Rectangle(double length, double width) { 
			this.length = length; 
			this.width = width; 
		} 
		
		@Override 
		double area() { return length * width; } 
		
	}

	//위에서 열거한 단점들이 해소되었으며, 컴파일 타임의 검사를 최대한 활용(추상메소드 구현 여부 등)하게 되었다. 
	//또한 다른 도형을 추가할때도 확장성있는 형태가 되었다.
	

	/*
	 * 핵심 정리
	 * - 태그 달린 클래스를 써야하는 상황은 거의 없다.
	 *   새로운 클래스를 작성하는 데 태그 필드가 등장한다면 태그를 없애고 계층구조로 대체하는 방법을 생각해보자.
	 *   기존 클래스가 태그 필드를 사용하고 있다면 계층구조로 리펙터링하는 걸 고민해보자. 
	 */

	
}
