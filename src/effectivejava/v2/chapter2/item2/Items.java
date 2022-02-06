package effectivejava.v2.chapter2.item2;

public class Items {
	private final int item1;
	private final int item2;
	private final int item3;
	private final int item4;
	
	public static class Builder {
	
		private int item1 = 0;
		private int item2 = 0;
		private int item3 = 0;
		private int item4 = 0;
		
		public Builder item1(int val) {
			item1 = val;
			return this;
		}
		
		public Builder item2(int val) {
			item2 = val;
			return this;
		}
		
		public Builder item3(int val) {
			item3 = val;
			return this;
		}
		
		public Builder item4(int val) {
			item4 = val;
			return this;
		}
		
		public Items build() {
			return new Items(this);
		}
		
	}
	
	private Items(Builder builder) {
		item1 = builder.item1;
		item2 = builder.item2;
		item3 = builder.item3;
		item4 = builder.item4;
	}

	@Override
	public String toString() {
		return "Items [item1=" + item1 + ", item2=" + item2 + ", item3=" + item3 + ", item4=" + item4 + "]";
	} 
	

}
