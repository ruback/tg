
public class Metodo3 implements Metodo {

	@Override
	public int media(int x, int y) {
		int mean = (x>>1) + (y>>1);
		if (((x^y) >= 0) && ((x&y&1) == 1)){	
			if (x>=0)
				return mean + 1;
			else
				return mean - 1;
		}
		return mean;
	}

}
