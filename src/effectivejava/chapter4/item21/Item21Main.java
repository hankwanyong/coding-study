package effectivejava.chapter4.item21;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;


public interface Item21Main<E> extends Iterable<E>{

	/*
	 * Item21 인터페이스는 구현하는 쪽을 생각해 설계하라
	 * 
	 * 
	 * 자바 8 이전에는 기존에 정의된 인터페이스가 있다면 해당 인터페이스에 메서드를 추가하는 것은 불가능에 가까운 일이었다.
	 * 하나 자바 8 이후부터는 디폴트 메서드라는 기능이 생겨서 위에서 언급했던 불편함을 어느 정도 해소시켜주었다.
	 * 
	 * 대표적인 예로 List의 sort메서드가 있다 (List 클래스 476번째 줄 참고)
	 * 이와 같이 자바 라이브러리에 포함된 디폴트 메서드는 코드 품질이 높고 대부분의 상황에서 무리 없이 사용할 수 있다.
	 * 
	 * 하지만 인터페이스에 메서드를 추가하는 문제점이 완전히 해결된 것은 아니다.
	 * 두가지 문제점이 존재하는데 
	 * 1. 디폴트 메서드를 재정의하지 않은 모든 클래스에서 디폴트 메서드의 구현을 사용할 수 있다.(ex.Vertor, Stack과 같은맥락)
	 * 2. 모든 기존 구현체들과 매끄러운 연동을 보장할 수 없다.
	 * 
	 * 이유는 언급하였듯이 자바 8 이전 버전에서는 인터페이스에 메서드가 추가될 것이라는 상상은 할 수가 없었고 
	 * 자바 8 이후부터 디폴트 메서드가 생겼지만 해당 버전 이전에 만들어졌던 모든 내용을 커버하기는 힘들었기 때문이다.
	 * 
	 */

	//코드21-1 자바8의 Collection 인터페이스에 추가된 디폴트 메서드
//	collection 인터페이스 409번째 줄 참고
    default boolean removeIf(Predicate<? super E> filter) {
        Objects.requireNonNull(filter);
        boolean result = false;
        for (Iterator<E> it = iterator(); it.hasNext();) {
            if (filter.test(it.next())) {
            	it.remove();
                result = true;
            }
        }
        return result;
    }
    
    /*
     * "아파치의  synchronizedCollection 버전은 (컬렉션 대신) 클라이언트가 제공한 객체로 락을 거는 능력을 추가로 제공한다. 
     *  즉, 모든 메서드에서 주어진 락 객체로 동기화한 후 내부 컬렉션 객체에 기능을 위임하는 래퍼 클래스(Item 18)이다."
     *  
     *  이렇게 설명하는 이유는 synchronizedCollection이 removeIf를 재정의하고 있지 않기 떄문이다.
     *  기존의 removeIf는 동기화에 대해서 고려를 하지 않았기 때문에 멀티스레드 환경에서 removeIf를 호출한다면 동기화 관련 이슈가 발생할 수 있다.
     *  
     *  결론은 
     *  
     *  디폴트 메서드는 (컴파일에 성공하더라도) 기존 구현체에 런타임 오류를 일으킬 수 있다.
     *  
     *  기존 인터페이스에 디폴트 메서드로 새 메서드를 추가하는 일은 꼭 필요한 일이 아니라면 피해야 한다. 
     *  또한 추가하려는 디폴트 메서드가 기존 구현체들과 충돌하지 않을지 심사숙고해야 한다.
     *  
     *  따라서 디폴트 메서드라는 도구가 생겼더라도 인터페이스 설계 시 여전히 세심한 주의가 필요하다. 
     *  또한 새로운 인터페이스라면 릴리스 전 반드시 테스트를 거쳐야 한다. 
     *  인터페이스를 릴리스한 후라도 결함을 수정하는 게 가능한 경우도 있겠지만, 절대 그 가능성에 기대서는 안된다. 
     *  
     *   --릴리스 : 빌드 결과와 실행 환경 정보, 애플리케이션 설정 정보를 통합하여 만들어낸 고유한 결과물
     */
   
	
}
