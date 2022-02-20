package effectivejava.v2.chapter3.item10;

import java.awt.Color;

public class ColorPoint extends Point{
	
	private final Color color;

	public ColorPoint(int x, int y, Color color) {
		super(x, y);
		this.color = color;
	}

	
	
	//대칭성을 위배한 코드
//	@Override
//	public boolean equals(Object o) {
//		if(!(o instanceof ColorPoint)) {
//			return false;
//		}
//		return super.equals(o) && ((ColorPoint)o).color == color;
//	}
	
	//추이성을 위배한 코드
	//구체 클래스를 확장해 새로운 값을 추가하면서 equals 규약을 만족시킬 방법은 존재하지 않는다.
	//ColorPoint는 Point를 상속받았기 때문에 Point 로써 활용할 수 있어야 한다. 따라서 equals 규약을 만족시킬  방법은 존재하지 않는다.
	//우회적인 방법으로 Point를 상속받지 않고 equals를 재정의할 방법은 있다. (ColorPoint2)
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof Point)) {
			return false;
		}
		
		if(!(o instanceof ColorPoint)) {
			return o.equals(this);
		}
		
		return super.equals(o) && ((ColorPoint)o).color == color;
	}
	
	
	
	
}
