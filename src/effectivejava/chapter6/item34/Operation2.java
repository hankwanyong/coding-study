package effectivejava.chapter6.item34;

public enum Operation2 {
	PLUS {
		@Override
		public double apply(double x, double y) {
			return x+y;
		}
	}, MINUS {
		@Override
		public double apply(double x, double y) {
			return x-y;
		}
	}, TIMES {
		@Override
		public double apply(double x, double y) {
			return x*y;
		}
	}, DIVIDE {
		@Override
		public double apply(double x, double y) {
			return x/y;
		}
	};
	
	public abstract double apply(double x, double y);
}
