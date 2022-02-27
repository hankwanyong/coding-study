package effectivejava.v2.chapter3.item11;

import java.util.Comparator;

public class PhoneNumberHashCode implements Comparable<PhoneNumberHashCode>{
	private final short areaCode, prefix, lineNum;
	private int hashCode; //자동으로 0 초기화

	public PhoneNumberHashCode(int areaCode, int prefix, int lineNum) {
		this.areaCode = rangeCheck(areaCode,999,"지역코드");
		this.prefix = rangeCheck(prefix,999,"프리픽스");
		this.lineNum = rangeCheck(lineNum,9999,"가입자 번호");
	}
	
	private static short rangeCheck(int val, int max, String arg) {
		if(val < 0 || val > max) {
			throw new IllegalArgumentException(arg +" : "+val);
		}
		return (short) val;
	}

	@Override
	public boolean equals(Object o) {
		if(o == this) {
			return true;
		}
		if(!(o instanceof PhoneNumberHashCode)) {
			return false;
		}
		PhoneNumberHashCode pn = (PhoneNumberHashCode) o;
		return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
	}


	//적법하지만 최악의 코드
//	@Override
//	public int hashCode() {
//		return 42;
//	}
	
	//전형적인 hashCode
//	@Override
//	public int hashCode() {
//		int result = Short.hashCode(areaCode);
//		result = 31 * result + Short.hashCode(prefix);
//		result = 31 * result + Short.hashCode(lineNum);
//		return result;
//	}
	
	//한줄짜리 메서드. 성능을 조금 떨어진다.
//	@Override
//	public int hashCode() {
//		return Objects.hash(lineNum, prefix, areaCode);
//	}
	
	//해시코드를 지연 초기화하는 메서드
	@Override
	public int hashCode() {
		int result = hashCode;
		if(result == 0) {
			result = Short.hashCode(areaCode);
			result = 31 * result + Short.hashCode(prefix);
			result = 31 * result + Short.hashCode(lineNum);
		}
		return result;
	}
	
	
	private static final Comparator<PhoneNumberHashCode> COMPARATOR = 
			Comparator.comparingInt((PhoneNumberHashCode pn) -> pn.areaCode)
			.thenComparingInt(pn -> pn.prefix).thenComparingInt(pn -> pn.lineNum);

	@Override
	public int compareTo(PhoneNumberHashCode pn) {
		return COMPARATOR.compare(this, pn);
	}
	
	
	
}
