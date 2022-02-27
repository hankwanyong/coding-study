package effectivejava.v2.chapter3.item10;

public class MyType {
	private String s;

	public String getS() {
		return s;
	}

	public void setS(String s) {
		this.s = s;
	}

	@Override
	public boolean equals(Object obj) {
		if(!(obj instanceof MyType)) {
			return false;
		}
		MyType mt = (MyType) obj;
		return mt.getS().equals(s);
	}
	
	
}
