import java.util.Random;


public class Metodo {
	protected static int n = 100000;
	protected static int vetor[];
	
	public static void criaVetor() {
		vetor = new int[n];
		Random randomGenerator = new Random();
		for (int i = 0; i < n; ++i) {
			vetor[i] = randomGenerator.nextInt(n);
		}
	}
	
	public void run(int threads) {
	};
}
