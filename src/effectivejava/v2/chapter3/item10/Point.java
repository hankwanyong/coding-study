package effectivejava.v2.chapter3.item10;

public class Point {
	private final int x;
	private final int y;
	
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}


	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Point)) {
			return false;
		}
		Point p = (Point) o;
		return p.x == x && p.y == y;
	}
	
	//리스코프 치환 원칙을 위배한 코드
	//Point의 하위 클래스는 정의상 여전히 Point이므로 어디서는 Point로 활용할 수 있어야 하기 때문.
//	@Override
//	public boolean equals(Object o) {
//		if(o == null || o.getClass() != getClass()) {
//			return false;
//		}
//		Point p = (Point) o;
//		return p.x == x && p.y == y;
//	}
	
}
