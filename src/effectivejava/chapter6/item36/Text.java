package effectivejava.chapter6.item36;

import java.util.Set;

public class Text {
	public static final int STYLE_BOLD = 1 << 0; // 0001// 1
	public static final int STYLE_ITALIC = 1 << 1; // 0010 //2
	public static final int STYLE_UNDERLINE = 1 << 2; // 0100  //4
	public static final int STYLE_STRIKETHROUGH = 1 << 3; // 1000 //8
	
	public static void applyStyles(int styles) {
		System.out.println("1.applyStyles : " + styles);
	}
	
	//비트 필드를 대체하는 기법
	public enum Style {
		BOLD(1 << 0), ITALIC(1 << 1), UNDERLINE(1 << 2), STRIKETHROUGH(1 << 3);
		
		private final int bitVal; //질량 (단위 : kg)
		Style(int bitVal) {
			this.bitVal = bitVal;
		}
		
		public int bitVal() {return bitVal;}
	};

	public static void applyStyles(Set<Style> styles) {
		System.out.println("2.applyStyles : " + styles);
	}
	
	public static void applyStylesOROper(Set<Style> styles) {
		int res=0;
		for(Style a : styles) {
			res = res | a.bitVal();
		}
		System.out.println("3.applyStyles : " + res);
	}
}
