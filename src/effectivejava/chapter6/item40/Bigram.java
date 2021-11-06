package effectivejava.chapter6.item40;

public class Bigram {
	private final char first;
	private final char second;
	
	public Bigram(char first, char second) {
		this.first = first;
		this.second = second;
	}

	//@Override
	public boolean equals(Bigram b) {
		return b.first == first && b.second == second;
	}

	@Override
	public int hashCode() {
		return 31 * first + second;
	}
	
	
}
