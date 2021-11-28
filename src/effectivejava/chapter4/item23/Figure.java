package effectivejava.chapter4.item23;

public class Figure {
	enum Shape { RECTANGLE, CIRCLE }; 
	final Shape shape; // 태그 
	
	// 사각형일 때만 필요한 필드 
	double length; 
	double width; 
	
	// 원일 때만 필요한 필드 
	double radius; 
	
	// 원 생성자
	Figure(double radius) { 
		shape = Shape.CIRCLE; 
		this.radius = radius; 
	} 
	
	// 사각형 생성자 
	Figure(double length, double width) { 
		shape = Shape.RECTANGLE; 
		this.length = length; 
		this.width = width; 
	}
	
	double area() { 
		switch(shape) { 
			case RECTANGLE: 
				return length * width; 
			case CIRCLE: 
				return Math.PI * (radius * radius); 
			default: throw new AssertionError(shape); 
		} 
	} 
}
