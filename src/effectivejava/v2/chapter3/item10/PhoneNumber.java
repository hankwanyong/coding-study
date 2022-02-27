package effectivejava.v2.chapter3.item10;

public class PhoneNumber implements Comparable<PhoneNumber>{
	private final short areaCode, prefix, lineNum;

	public PhoneNumber(int areaCode, int prefix, int lineNum) {
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
		if(!(o instanceof PhoneNumber)) {
			return false;
		}
		PhoneNumber pn = (PhoneNumber) o;
		return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
	}

	@Override
	public int compareTo(PhoneNumber pn) {
		int result = Short.compare(areaCode, pn.areaCode);
		if(result == 0) {
			result = Short.compare(prefix, pn.prefix);
			if(result == 0) {
				result = Short.compare(lineNum, pn.lineNum);
			}
		}
		return result;
	}
	
	
}
