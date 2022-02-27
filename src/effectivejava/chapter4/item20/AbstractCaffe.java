package effectivejava.chapter4.item20;

public abstract class AbstractCaffe implements caffe{
	@Override
	public void order() {
		System.out.println("order!");
	}
	
	@Override
	public void pay() {
		System.out.println("pay");
	}
}
