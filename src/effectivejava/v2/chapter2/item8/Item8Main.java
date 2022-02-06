package effectivejava.v2.chapter2.item8;

/*
 * 아이템8 finalizer와 cleaner 사용을 피하라
 * 
 * 자바에서 제공하는 객체 소멸자 : finalizer, cleaner
 * 두 객체 소멸자는 즉시 수행된다는 보장이 없다. 전적으로 가비지 컬렉터 알고리즘에 달렸으며, 이는 가비지 컬렉터 구현마다 천차만별이다.
 * 심지어 성능도 저하시킨다..
 * 그냥 쓰지말자 (아이템 67 : 최적화는 신중히 하라..)
 * 
 */
public class Item8Main {}
