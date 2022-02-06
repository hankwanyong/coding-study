package effectivejava.v2.chapter2.item2;

/*
 * 아이템2 생성자에 매개변수가 많다면 빌더를 고려하라
 * 
 * 생성자나 정적 팩터리가 처리해야 할 매개변수가 많다면 빌더 패턴을 선택하는 게 더 낫다.
 * 
 * 확장성을 고려하면 매개변수는 늘어날 가능성이 높다.
 */
public class Item2Main {

	public static void main(String[] args) {

		
		Items items = new Items.Builder().item1(1).item2(2).item3(3).item4(4).build();
		System.out.println(items.toString());
		
	}
	
}

