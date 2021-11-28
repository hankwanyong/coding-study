package effectivejava.chapter4.item24;

public class NutritionFacts {
	private final int servingSize; 
	private final int servings; 
	private final int calories; 
	private final int fat; 
	
	public static class Builder { 
		// 필수 매개변수
		private final int servingSize; 
		private final int servings; 
		
		// 선택 매개변수 - 기본값으로 초기화한다. 
		private int calories = 0; 
		private int fat = 0; 
		
		public Builder(int servingSize, int servings) { 
			this.servingSize = servingSize; 
			this.servings = servings; 
		} 

		public Builder calories(int val) { 
			calories = val; 
			return this; 
		} 
		
		public Builder fat(int val) { 
			fat = val; 
			return this; 
		} 
		
		public NutritionFacts build() { 
			return new NutritionFacts(this); 
		} 
	} 
	
	public NutritionFacts(Builder builder) { 
		servingSize = builder.servingSize; 
		servings = builder.servings; 
		calories = builder.calories; 
		fat = builder.fat; 
	} 
	
	public static void main(String[] args) { 
		NutritionFacts cocaCola = new NutritionFacts.Builder(240, 8).calories(100).build(); 
	}
}
