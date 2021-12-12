package effectivejava.chapter9.item58;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Item58Main {

	
	/*
	 * 아이템 58 전통적인 for 문보다는 for-each 문을 사용하라
	 * 
	 * for-each문 : 향상된 for문 (enhanced for statement)
	 */
	
	enum Suit {CLUB, DIAMOND, HEART, SPADE}
	enum Rank {ACE, DEUCE, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, THEN, JACK, QUEEN, KING}
	
	static Collection<Suit> suits = Arrays.asList(Suit.values());
	static Collection<Rank> ranks = Arrays.asList(Rank.values());
	
	
	
	public static void main(String[] args) {

		// for-each문 사용법
		List<String> list = new ArrayList<>();
		for(String str : list) {
			// list의 원소를 str로 사용가능
		}
		
		// for-each문 사용 가능한 조건은 Iterable 인터페이스 상속
		// Iterable 인터페이스는 iterator(); 함수를 이용해 Iterator<T> 를 사용가능 하도록 해줌
		// Iterator는 반복문으로 hasNext(), next(), remove() 사용 가능
		// 자바에서는 기본적으로 Collection 인터페이스가 Iterable 인터페이스를 상속하고 있음.
		// for-each 반복문은 내부적으로 iterator()를 사용하고있음 > 성능은 차이가 없다 > 잘 정의된 for-each문을 사용하자
		
		class Card {
			Suit suit; 
			Rank rank;	
			
			Card(Suit suit, Rank rank) {
				this.suit = suit;
				this.rank = rank;
			}
		}
		
		// 버그!
		List<Card> deck = new ArrayList<>();
//		for(Iterator<Suit> i = suits.iterator(); i.hasNext();) {
//			for(Iterator<Rank> j = ranks.iterator(); j.hasNext();) {
////				deck.add(new Card(i.next(), j.next()));	// java.util.NoSuchElementException
//				// j당 i의 next가 하나씩 호출됨 > 버그
//			}
//		}

		
		for(Suit suit : suits) {
			for(Rank rank : ranks) {
				deck.add(new Card(suit, rank));
			}
		}
		
		System.out.println(deck.toString());
		
				
		/*
		 * for-each를 사용 불가능한 상황
		 * 1. 파괴적인 필터링 : 컬렉션을 순회하면서 선택된 원소를 제거해야 한다면 반복자의 remove 메서드를 호출해야 한다.
		 * 2. 변형 : 리스트나 배열을 순회하면서 그 원소의 값 일부 혹은 전체를 교체해야 한다면 리스트의 반복자나 배열의 인덱스를 사용 해야한다.
		 * 3. 병렬반복 ㅣ 여러 컬렉션을 병렬로 순회해야 한다면 각각의 반복자와 인덱스 변수를 사용해 엄격하고 명시적으로 제어해야 한다.
		 */
		
		// 위 같이 사용불가능한 상황이 아니라면 for-each를 사용하자
	}

}
