package effectivejava.v2.chapter3.item10;

import java.util.Objects;
public final class CaseInsensitiveString  implements Comparable<CaseInsensitiveString>{
	
	private final String s;
	
	public CaseInsensitiveString(String s) {
		this.s = Objects.requireNonNull(s);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof CaseInsensitiveString) {
			return s.equalsIgnoreCase(((CaseInsensitiveString)o).s);
		}
		if(o instanceof String) { //서로 다른 클래스를 equals 로 비교하려 하지 말자.
			return s.equalsIgnoreCase((String) o);
		}
		
		return false;
	}

	//올바른 방법
//	@Override
//	public boolean equals(Object o) {
//		return o instanceof CaseInsensitiveString &&  ((CaseInsensitiveString) o).s.equalsIgnoreCase(s);
//	}
	
	
	@Override
	public int compareTo(CaseInsensitiveString o) {
		return String.CASE_INSENSITIVE_ORDER.compare(s, o.s) ;
	}
	
}
