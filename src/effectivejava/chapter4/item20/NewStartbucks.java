package effectivejava.chapter4.item20;

/*
 * 골격 구현 클래스를 우회한 다중 상속
 */
public class NewStartbucks extends Receipt implements caffe{
	InnerChooseCoffe innerChooseCoffe = new InnerChooseCoffe();
	
	@Override
	public void order() {
		innerChooseCoffe.order();
	}

	@Override
	public void pay() {
		innerChooseCoffe.pay();
	}
	
	@Override
	public void chooseCoffee() {
		innerChooseCoffe.chooseCoffee();
	}
	
	private class InnerChooseCoffe extends AbstractCaffe {
		/*
		 * 구현 클래스마다 달라지는 메소드
		 */
		@Override
		public void chooseCoffee() {
			System.out.println("Choose starbucks coffee!");
		}
	}
}
