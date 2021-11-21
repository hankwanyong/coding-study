package effectivejava.chapter8.item50;

import java.util.Date;
public final class Period {
	private final Date start;
	private final Date end;
	
	public Period(Date start, Date end) {
		if(start.compareTo(end) > 0) {
			throw new IllegalArgumentException(start + " after " + end);
		}
		this.start = start;
		this.end = end;
	}
	
	public Date start() {
		return start;
	}
	
	public Date end() {
		return end;		
	}
	
	public String toString() {
		return ("start : " + this.start + "\nEnd : " + this.end);			
	}
	
	//방어적 복사본 - 첫 번째 공격 대응
//	public Period(Date start, Date end) {
//		this.start = new Date(start.getTime());
//		this.end = new Date(end.getTime());
//		
//		if(start.compareTo(end) > 0) {
//			throw new IllegalArgumentException(start + " after " + end);
//		}
//	}
	
	//두 번째 공격 대응
//	public Date start() {
//		return new Date(start.getTime());
//	}
//	
//	public Date end() {
//		return new Date(end.getTime());
//	}
}
