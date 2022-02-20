package effectivejava.v2.chapter3.item10;

import java.awt.Color;

/**
 * equals 규약을 지키면서 값 추가하기
 * @author 박민영
 *
 */
public class ColorPoint2{
	private final Point point;
	private final Color color;

	public ColorPoint2(int x, int y, Color color) {
		point = new Point(x, y);
		this.color = color;
	}
	
	public Point asPoint() {
		return point;
	}

	
	@Override
	public boolean equals(Object o) {
		if(!(o instanceof ColorPoint2)) {
			return false;
		}
		ColorPoint2 cp = (ColorPoint2) o;
		return cp.point.equals(point) && cp.color.equals(color);
	}
	
	
	
	
}
