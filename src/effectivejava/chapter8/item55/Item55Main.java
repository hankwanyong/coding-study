package effectivejava.chapter8.item55;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Stream;


/**
 * 옵셔널 반환은 신중히 하라.
 * @author 박민영
 *
 */
public class Item55Main {
	
	/*
	 * 어떤 메서드에서 보통은 T를 반환해야 하지만 특정 조건에서는 아무것도 반환하지 않아야 할 때 T대신 Optional<T>를 반환할 수 있다.
	 * Optional<T>는 null이 아닌 T타입 참조를 하나 담거나, 혹은 아무것도 담지 않을 수 있다.
	 * 아무것도 담지 않은 옵셔널은 '비었다'고 말한다.
	 * 옵셔널은 원소를 최대 1개 가질 수 있는 '불변' 컬렉션이다.
	 * Optional<T>가 Collection<T>를 구현하지는 않았지만 원칙적으로 그렇다.
	 * 
	 * 아래는 주어진컬렉션에서 최댓값을 구하는 메서드다.
	 * */
	private static <E extends Comparable<E>> E max(Collection<E> c){
		if(c.isEmpty()) {
			throw new IllegalArgumentException("빈 컬렉션");
		}
		
		E result = null;
		for(E e : c) {
			if(result == null || e.compareTo(result)>0) {
				result = Objects.requireNonNull(e);
			}
		}
		return result;
	}
	
	/*
	 * 위 메서드는 빈 컬렉션을 건네면 예외를 던진다.
	 * Optional을 사용하면
	 * */
	private static <E extends Comparable<E>> Optional<E> max2(Collection<E> c){
		if(c.isEmpty()) {
			return Optional.empty();
		}
		
		E result = null;
		for(E e : c) {
			if(result == null || e.compareTo(result)>0) {
				result = Objects.requireNonNull(e);
			}
		}
		return Optional.of(result);
	}
	
	/*
	 * null값도 허용하는 옵셔널을 만들려면 Optional.ofNullable(value)를 사용하면 되지만,
	 * 옵셔널을 반환하는 메서드에서는 절대 null을 반환하지 말자.
	 * 옵셔널을 도입한 취지를 완전히 무시하는 행위다.
	 * 
	 * 스트림 종단 연산 중 상당수가 옵셔널을 반환한다.
	 * 앞의 max메서드를 스트림 버전으로 다시 작성한다면 Stream의 max연산이 우리에게 필요한 옵셔널을 생성해 줄 것이다.
	 * */
	private static <E extends Comparable<E>> Optional<E> max3(Collection<E> c){
		return c.stream().max(Comparator.naturalOrder());
	}
	
	
	/*
	 * 그렇다면 null을 반환한거나 예외를 던지는 대신 옵셔널 반환을 선택해야 하는 기준?
	 * 옵셔널은 검사 예외와 취지가 비슷하다.(Item71)
	 * 반환값이 없을 수도 있음을 API사용자에게 명확히 알려준다.
	 * */
	
	
	public static void main(String[] args) {
		List<String> words = new ArrayList<>();
		
		//옵셔널 활용1 : 기본값을 정해둘 수 있다.
		String lastWordInLexicon = max2(words).orElse("단어 없음...");
		
		//옵셔널 활용2 : 원하는 예외를 던질 수 있다.
		try {
			String lastWordInLexicon2 = max2(words).orElseThrow(Exception::new);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//옵셔널 활용3 : 항상 값이 채워져 있다고 가정하면
		String lastWordInLexicon3 = max2(words).get();
		
		//옵셔널 활용4 : 기본값을 설정하는 비용이 부담이 클 경우
		String lastWordInLexicon4 = max2(words).orElseGet(new Supplier<String>() {
			@Override
			public String get() {
				return "초기값";
			}
		});
		
		/*
		 * 위의 메서드로 처리하기 어려워 인다면 다른 메서드도 준비되어 있다.
		 * filter, map, flatMap, isPresent 등을 활용해보자.
		 * 
		 * isPresent는 안전밸브 역할의 매서드로, 옵셔널이 채워져있으면 true, 비어있으면 false를 반환한다.
		 * 이 메서드로는 원하는 모든 작업을 수행할 수 있지만 신중히 사용해야 한다.
		 * 실제로 isPresent를 쓴 코드 중 상당수는 앞서 언급한 메서드들로 대체할 수 있으며, 그렇게 하면 더 짧고 명확하고 용법에 맞는 코드가 된다.
		 * */
		System.out.println(max2(words).isPresent());
		
		/*
		 * 스트림을 사용한다면 옵셔널들을 Stream<Optional<T>>로 받아서, 그 중 채워진 옵셔널들에서 값을 뽑아 Stream<T>에 건네 담아 처리하는 경우가 드물지 않다.
		 * */
		
		List<Optional<String>> optionals = new ArrayList<>();
		Stream<Optional<String>> streamOfOptionals = optionals.stream();
		Stream<String> streamOfStrings = streamOfOptionals.filter(Optional::isPresent).map(Optional::get);
		/*
		 * 옵셔널에 값이 있다면 Optional::isPresent 그 값을 꺼내 Optional::get 스트림에 매핑한다.
		 * 자바 9에서는 Optional에 stream()메서드가 추가되었다.
		 * 이 메서드는 Optional을 stream으로 변환해주는 어댑터다.
		 * 옵셔널에 값이 있다면 그 값을 원소로 담은 스트림으로, 값이 없다면 빈 스트림으로 변환한다.
		 * 이를 Stream의 flatMap메서드와 조합하면 앞의 코드를 다음처럼 명료학게 바꿀 수 있다.
		 * */
		//streamOfOptionals.flatMap(Optional::stream);
		
		
		/*
		 * 반환값으로 옵셔널을 사용한다고 해서 무조건 득이 되는 건 아니다.
		 * 컬렉션, 스트림, 배열, 옵셔널같은 컨테이너 타입은 옵셔널로 감싸면 안된다.
		 * 빈 옵셔널을 반환하기 보다 빈 list를 반환하는게 낫다.
		 * 
		 * 옵셔널을 사용하는 기본 규칙
		 * 결과가 없을 수 있으며, 클라이언트가 이 상황을 특별하게 처리해야 할때 사용한다.
		 * 
		 * 박싱된 기본타입은 전용 옵셔널 클래스가 있다. (OptionalInt, OptionalLong, OptionalDouble)
		 * 박싱된 기본타입을 담은 옵셔널을 반환하는 일은 없도록 하자.
		 * 
		 * 다른 쓰임에 대해거는 논하지 않았는데, 대부분 적절치 않기 때문이다.
		 * 옵셔널을 컬렉션의 키, 값, 원소나 배열의 원소로 사용하는 게 적절한 상황은 거의 없다.
		 * 
		 * 어떤 클래스에 필수가 아닌 필드라면 필드 자체를 옵셔널로 선언하는것도 좋은 방법이다.
		 * */
		
		/*
		 * 값을 반환하지 못할 가능성이 있고, 호출할 때마다 반환값이 없을 가능성을 염두에 둬야 하는 메서드라면 옵셔널을 반환해야 할 상황일 수 있다.
		 * 하지만 옵셔널 반환에는 성능 저하가 뒤따르니, 성능에 민감한 메서드라면 null을 반환하거나 예외를 던지는 편이 나을 수 있다.
		 * 그리고 옵셔널을 반환값 이외의 용도로 쓰는 경우는 매우 드물다.
		 * */
		
	}
}

