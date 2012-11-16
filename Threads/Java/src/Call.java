import java.util.concurrent.Callable;

public class Call implements Callable<Integer> {
	private int j;
	
	public Call(int j) {
		this.j = j;
	}
	
	public Integer call() throws Exception {
		int count = 0;
		double x = j;
		while (x > 1) {
			x = x*0.999;
			++count;
		}
		return count;
	}

}
