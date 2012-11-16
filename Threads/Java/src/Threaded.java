import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Threaded extends Metodo {
	private static ExecutorService executorPool = null;
	private List<Future<Integer>> results;
	
	public void run(int threads) {
		List<Callable<Integer>> list = new ArrayList<Callable<Integer>>();
		for (int i = 0; i < n; ++i) {
			Callable<Integer> call = new Call(vetor[i]); 
			list.add(call);
		}
		executorPool = new ThreadPoolExecutor(threads, threads, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		try {
			results = executorPool.invokeAll(list);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}


