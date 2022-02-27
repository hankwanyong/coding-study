package effectivejava.chapter4.item20;

public class Starbucks implements caffe{
	@Override
	public void order() {
		System.out.println("order!");
	}
	
	/*
	 * 구현 클래스마다 달라지는 메소드
	 */
	@Override
	public void chooseCoffee() {
		System.out.println("Choose starbucks coffee!");
	}
	
	@Override
	public void pay() {
		System.out.println("pay");
	}
}
