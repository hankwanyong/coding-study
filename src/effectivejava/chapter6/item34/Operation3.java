package effectivejava.chapter6.item34;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public enum Operation3 {
	PLUS("+") {
		@Override
		public double apply(double x, double y) {
			return x+y;
		}
	}, MINUS("-") {
		@Override
		public double apply(double x, double y) {
			return x-y;
		}
	}, TIMES("*") {
		@Override
		public double apply(double x, double y) {
			return x*y;
		}
	}, DIVIDE("/") {
		@Override
		public double apply(double x, double y) {
			return x/y;
		}
	};
	
	private final String symbol;
	
	Operation3(String symbol) {
		this.symbol = symbol;
	}
	
	@Override
	public String toString() {return symbol;}
	
	public abstract double apply(double x, double y);
	
//	private static final Map<String, Operation3> stringToEnum = Stream.of(values()).collect(toMap(Object::toString, e -> e));
//	
//	public static Optional<Operation3> fromString(String symbol){
//		return Optional.ofNullable(stringToEnum.get(symbol));
//	}
	
	public static Operation3 inverse(Operation3 op) {
		switch (op) {
		case PLUS: return Operation3.MINUS;
		case MINUS: return Operation3.PLUS;
		case TIMES: return Operation3.DIVIDE;
		case DIVIDE: return Operation3.TIMES;
			
		default: throw new AssertionError("알 수 없는 연산: "+op);
		}
	}
}
