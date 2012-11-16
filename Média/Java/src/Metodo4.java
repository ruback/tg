
public class Metodo4 implements Metodo {

	@Override
	public int media(int x, int y) {
		return (x&y) + ((x^y)>>1); 
	}

}
