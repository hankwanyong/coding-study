package effectivejava.chapter12.item87;

import java.io.Serializable;
import java.util.Map.Entry;

public final class StringList implements Serializable{
	private int size = 0;
	private Entry head = null;
	
	private static class Entry implements Serializable{
		String data;
		Entry next;
		Entry previous;
	}

	//논리적으로 이 클래스는 일련의 문자열을 표현한다.
	//물리적으로는 문자열들을 이중 연결 리스트로 연결했다.
	//이 클래스에 기본 직렬화 형태를 사용하면 각 노드의 양방향 연결 정보를 포함해 모든 엔트리(Entry)를 철두철미하게 기록한다.
}
