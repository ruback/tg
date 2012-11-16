import java.util.ArrayList;
import java.util.List;

public class Threadless extends Metodo {
	
	private List<Integer> results;
	
	public void run(int threads) {
		results = new ArrayList<Integer>();
		for (int i = 0; i < n; ++i) {
			results.add(calc(i));
		}
	}
	
	public int calc(int j) {
		int count = 0;
		double x = (double)vetor[j];
		while (x > 1) {
			//0.99
			x = x*0.999;
			++count;
		}
		return count;
	}
}
