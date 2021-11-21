package effectivejava.chapter8.item52;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionClassifier {
	public static String classify(Set<?> s) {
		return "집합";
	}
	
	public static String classify(List<?> lst) {
		return "리스트";
	}
	
	public static String classify(Collection<?> c) {
		return "그 외";
	}
	
	public static String classify2(Collection<?> c) {
		return c instanceof Set ? "집합" :
			  c instanceof List ? "리스트" : "그 외";
	}
	
	
	
   
}
