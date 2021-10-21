package effectivejava.chapter5.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Item28Main {
	/*
	 *배열보다는 리스트를 사용하라
	 * 배열 : 공변, 런타임 안정, 컴파일 불안정
	 * 제네릭 : 불공변, 런타임 불안정, 컴파일 안정
	 *                  
	 */

	
	public static void main(String[] args) {
	  
		//런타임에 실패
		try {
			Object[] ObjectArray = new Long[1];
			ObjectArray[0] = "문자열"; //ArrayStoreException
		} catch ( ArrayStoreException e) {
			e.printStackTrace();
		}
		
		//컴파일이 되지 않는다.
		/*
		List<Object> ol = new ArrayList<Long>();
		ol.add("타입이 달라 넣을 수 없다.";)
		*/
		
		//===================== 컴파일 단계에서 알 수 있다 ====================
		
		/* 배열은 제네릭 타입, 매개변수화 타입, 타입 매개변수로 사용할 수 없다.
		 * new List<E>[], new List<String>[], new E[] 컴파일 오류
		 * 
		 * 런타임에 ClassCast Exception이 발생하는 일을 막는 제네릭 타입 시스템의 취지에 어긋난다.
		 */
		
	}
    
	
	public static class Chooser<T> {
		private final List<T> choiceList;
		
		public Chooser(Collection<T> choices) {
			choiceList = new ArrayList<>(choices);
		}
		
		public T choose() {
			Random rnd = ThreadLocalRandom.current();
			return choiceList.get(rnd.nextInt(choiceList.size()));
		}
		
	}

}
