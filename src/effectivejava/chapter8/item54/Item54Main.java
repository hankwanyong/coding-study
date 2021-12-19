package effectivejava.chapter8.item54;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * null이 아닌, 빈 컬렉션이나 배열을 반환하라.
 * @author 박민영
 *
 */
public class Item54Main {
	
	/*
	 * null을 반환하는 메서드는, 클라이언트에서 null상황을 처리하는 코드를 추가로 작성하여야 한다.
	 * 클라이언트에서 방어코드를 빼먹으면 오류가 발생할 수 있다.
	 * 
	 * 때로는 빈 컨테이너를 할당하는 데도 비용이 드니 null을 반환하는 쪽이 낫다는 주장도 있다.
	 * 이는 두가지 측면에서 틀린 주장이다.
	 * 1. 성능분석 결과 이 할당이 성능 저하의 주범이라고 확인되지 않는 한, 이 정도의 성능 차이는 신경쓸 수준이 못된다.
	 * 2. 빈 컬랙션과 배열은 굳이 새로 할당하지 않고도 반환할 수 있다.
	 * 
	 * 아래는 빈 컬렉션을 반환하는 올바른 예.
	 * */
	private final List<Cheese> cheesesInStock = null;
	
	public List<Cheese> getCheeses(){
		return new ArrayList<>(cheesesInStock);
	}
	
	//최적화 한다면
	public List<Cheese> getCheeses2(){
		return cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
	}
	
	/*
	 * 배열을 쓸 때도 마찬가지다. 절대 null을 반환하지 말고 길이가 0인 배열을 반환하라.
	 * 보통은 단순히 정확한 길이의 배열을 반환하기만 하면 된다.
	 * 그 길이가 0일 수도 있을 뿐이다.
	 * */
	
	public Cheese[] getCheeses3() {
		return cheesesInStock.toArray(new Cheese[0]);
	}
	
	/*
	 * 위 방식이 성능을 떨어뜨릴 것 같다면 길이 0짜리 배열을 미리 선언해두고 매번 그 배열을 반환하면 된다.
	 * */
	
	private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];
	
	public Cheese[] getCheeses4() {
		return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
	}
	
	/*
	 * 아래는 나쁜 예 : 배열을 미리 할당하면 성능이 낮아진다.
	 * */
	public Cheese[] getCheeses5() {
		return cheesesInStock.toArray(new Cheese[cheesesInStock.size()]);
	}
	
	public static void main(String[] args) {
		/*
		 * null이 아닌, 빈 배열이나 컬렉션을 반환하라.
		 * null을 반환하는 API는 사용하기 어렵고 오류 처리코드도 늘어난다.
		 * 그렇다고 성능이 좋은것도 아니다.
		 * */
		
		List<String> test = Collections.emptyList();
		System.out.println(test.size());
		test.add("test");
		test.add("test");
	}
	
	
}


