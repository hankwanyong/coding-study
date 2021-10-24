package effectivejava.chapter5.item33;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class Item33Main {
	/*
	 * 타입 안전 이종 컨테이너를 고려하라      
	 *  타입과 키의타입 2가지를 보장하는 설계를 타입 안전 이종 컨테인어 패턴
	 */
	
	//타입 안전 이종 컨테이너 패턴
	public static class Favorites {
		
		private Map<Class<?>, Object> favorites = new HashMap<>();
		//Map<Class<?>, Oject> 비한정적 와일드카드 타입이라 이 맵안에 아무것도 넣을 수 없다고 생각되지만,
		// 맵이아니라 키다 와일드카드 타입이라 가능하다.
		
		//값과 키의 불일치
		public <T> void putFavorite(Class<T> type, T instance) {
			favorites.put(Objects.requireNonNull(type), instance);
		}
		
		
		public <T> T getFavorite(Class<T> type) {
			return type.cast(favorites.get(type)); //형변환 연산자의 동적 버전
		}
		
		//타입 안전성 확보
		public <T> void putFavorite2(Class<T> type, T instance) {
			favorites.put(Objects.requireNonNull(type), type.cast(instance));
		}
		
		//List<String> 같은 실체화 불가 타입은 사용불가
		//List<String>.class 문법오류
		//이것을 해결하려는 시도 : 슈퍼 타입 토큰
	}
	
	public static void main(String[] args) {
		
		Favorites f = new Favorites();
		
		f.putFavorite(String.class, "Java");
		f.putFavorite(Integer.class, 0xcafebabe);
		f.putFavorite(Class.class, Favorites.class);
				
		String favoriteString = f.getFavorite(String.class);
		int favoriteInteger = f.getFavorite(Integer.class);
		Class<?> favoriteClass = f.getFavorite(Class.class);
		
		System.out.printf("%s\n %x\n %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
		
		
		
	}
	
	//한정적 타입 토큰 사용
	// Class<? extends Annotation> 컴파일 경고 
	// -> asSubclass : 호출된 인스턴스 자신의 Class 객체를 인수가 명시한 클래스로 형변환한다.(이 클래스가 인수로 명시한 클래스의 하위 클래스라는 뜻)

   /*
    * 핵심정리
    * 일반적인 제네릭 형태에서는 한 컨테이너가 다룰 수 있는 타입 매개변수의 수가 고정되어있다.
    * 하지만 키를 타입 매개변수로 바꾸면 이런 제약이 없는 타입 안전 이종 컨테이너를 만들 수 있다.
    * 타입 안전 이종 컨테이너는 Class를 키로 쓰며, 이런 식으로 쓰이는 Class 객체를 타입 토큰이라 한다.
    */
}
