package effectivejava.chapter6.item36;

import java.util.Set;

public class Text {
	public static final int STYLE_BOLD = 1 << 0;
	public static final int STYLE_ITALIC = 1 << 1;
	public static final int STYLE_UNDERLINE = 1 << 2;
	public static final int STYLE_STRIKETHROUGH = 1 << 3;
	
	public static void applyStyles(int styles) {
		System.out.println(styles);
	}
	
	//비트 필드를 대체하는 기법
	public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}
	
	public static void applyStyles(Set<Style> styles) {
		System.out.println(styles);
	}
}
