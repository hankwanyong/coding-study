package effectivejava.v2.chapter3.item13;


public class HashTable implements Cloneable{
	private Entry[] buckets = new Entry[10];
	
	private static class Entry {
		final Object key;
		Object value;
		Entry next;
		
		Entry(Object key, Object value, Entry next){
			this.key = key;
			this.value = value;
			this.next = next;
		}
		
		//이 엔트리가 가리키는 연결 리스트를 재귀적으로 복사
		//아래 코드는 재귀 호출 때문에 리스트의 원소 수만큼 스택 프레임을 소비하여, 리스트가 길면 스택 오버플로를 일으킬 위험이 있다.
//		Entry deepCopy() {
//			return new Entry(key, value, next == null ? null : next.deepCopy());
//		}
		
		//반복자를 써서 순회하는 방향으로 수정한다.
		Entry deepCopy() {
			Entry result = new Entry(key, value, next);
			for(Entry p = result;p.next != null;p = p.next) {
				p.next = new Entry(p.next.key, p.next.value, p.next.next);
			}
			return result;
		}
		
	}
	
	//잘못된 clone 메서드
	//복제본은 자신만의 버킷 배열을 갖지만, 이 배열은 원본과 같은 연결 리스트를 참조하고있다.
	//이를 해결하려면 각 버킷을 구성하는 연결 리스트를 복사해야 한다.
//	@Override
//	public HashTable clone() {
//		try {
//			HashTable result = (HashTable) super.clone();
//			result.buckets = buckets.clone();
//			return result;
//		} catch (CloneNotSupportedException e) {
//			throw new AssertionError();
//		}
//	}
	
	//복잡한 가변상태를 갖는 클래스용 재귀적 clone 메서드
	@Override
	public HashTable clone() {
		try {
			HashTable result = (HashTable) super.clone();
			result.buckets = new Entry[buckets.length];
			for(int i = 0; i<buckets.length;i++) {
				if(buckets[i] != null) {
					result.buckets[i] = buckets[i].deepCopy();
				}
			}
			return result;
		} catch (CloneNotSupportedException e) {
			throw new AssertionError();
		}
	}
	
	public HashTable() {
		
	}
	
	//복사 생성자 - 메서드 안의 내용은 생략
	public HashTable(HashTable hashTable) {
		super();
	}
	
	//복사 팩터리 - 메서드 안의 내용은 생략
	public static HashTable newInstance(HashTable hashTable) {
		return hashTable;
	}
}
