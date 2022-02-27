package effectivejava.chapter3.item13;

public class PhoneNumberClone implements Cloneable{
	private final short areaCode, prefix, lineNum;

	public PhoneNumberClone(int areaCode, int prefix, int lineNum) {
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
		if(!(o instanceof PhoneNumberClone)) {
			return false;
		}
		PhoneNumberClone pn = (PhoneNumberClone) o;
		return pn.lineNum == lineNum && pn.prefix == prefix && pn.areaCode == areaCode;
	}

	@Override
	public PhoneNumberClone clone(){
		//재정의한 메서드의 반환타입은 상위 클래스의 메서드사 반환하는 타입의 하위타입일 수 있다.
		//아래와 같은 방식으로 클라이언트가 형변환하지 않아도 되게끔 해주자.
		try {
			return (PhoneNumberClone) super.clone();
		} catch (CloneNotSupportedException e) {
			throw new AssertionError(); //일어날 수 없는 일.
		}
	}
	
	

	@Override
	public String toString() {
		return "PhoneNumberClone [areaCode=" + areaCode + ", prefix=" + prefix + ", lineNum=" + lineNum + "]";
	}

	
	
}
