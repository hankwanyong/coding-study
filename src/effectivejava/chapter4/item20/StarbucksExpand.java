package effectivejava.chapter4.item20;

public class StarbucksExpand extends AbstractCaffe {
	/*
	 * 구현 클래스마다 달라지는 메소드
	 */
	@Override
	public void chooseCoffee() {
		System.out.println("Choose starbucks coffee!");
	}
}
