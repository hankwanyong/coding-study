package effectivejava.chapter12.item89;

import java.util.Arrays;

public class Item89Main {

	/*
	 * 아이템 89 인스턴스 수를 통제해야 한다면 readResolve 보다는 열거 타입을 사용하라.
	 * 
	 *  아이템 3에서는 아래와 같은 싱글턴 패턴 예제를 보았다. 
	 *  public static final 필드를 사용하는 방식이다. 
	 *  생성자는 private 접근 지정자로 선언하여 외부로부터 감추고 INSTANCE를 초기화할 때 딱 한 번만 호출된다.
	 */
	public class Elvis {
	    public static final Elvis INSTANCE = new Elvis();
	    private Elvis() {}
	    
	    public void leaveTheBuiling(){}
	}
	/*
	 * 하지만 이 클래스는 Serializable을 구현하게 되는 순간 싱글턴이 아니게 된다. 
	 * 기본 직렬화를 쓰지 않거나 명시적인 readObject 메서드를 제공하더라도 소용이 없다. 
	 * 어떤 ReadObject 메서드를 사용하더라도 초기화될 때 만들어진 인스턴스와 다른 인스턴스를 반환하게 된다.
	 * 
	 * 이때 readResolve 메서드를 이용하면 readObject 메서드가 만든 인스턴스를 다른 것으로 대체할 수 있다. 이때 readObject 가 만들어낸 인스턴스는 가비지 컬렉션의 대상이 된다.
	 */
	
	private Object readResolve() {
		// 기존에 생성된 인스턴스를 반환한다.
		return INSTANCE;
	}
	
	/* 
	 * readResolve 를 인스턴스 통제 목적으로 사용한다면 객체 참조 타입 인스턴스 필드는 모두 transient 로 선언해야 한다.
	 * 만일 그렇지 않으면 역직렬화(Deserialization) 과정에서 역직렬화된 인스턴스를 가져올 수 있다. 즉, 싱글턴이 깨지게 된다.
	 * 
	 * 불변식을 지키기 위해 인스턴스를 통제해야 한다면 열거타입이 낫다.
	 */
	//코드 89-4 열거타입 싱글턴 - 전통적인 싱글턴보다 우수하다.
	public enum Elvis1 {
	    INSTANCE;
		private String[] favoriteSongs = {"Hound Dog", "Heartbreak Hotel"};
		public void printFavorites(){
			System.out.println(Arrays.toString(favoriteSongs));
		}
	}
	 
	 /**
	  * 핵심 정리
	  * 불변식을 지키기 위해 인스턴스를 통제해야 한다면 가능한 한 열거 타입을 사용하자.
	  * 여의치 않은 상황에서 직렬화와 인스턴스 통제가 모두 필요하다면 ReadResolve 메서드를 작성해 넣어야하고,
	  * 그 클래스에서 모든 참조 타입 인스턴스 필드를 transient로 선언해야 한다.
	  * 
	  */
	

}
