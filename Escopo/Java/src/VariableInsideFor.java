
public class VariableInsideFor extends Test {
	
	VariableInsideFor(){
		setNome("Variable Inside For");
	}
	
	public void run() {
		int aux = 0;
		for (long k = 0; k < num_repeticoes; ++k) {
			boolean a = true;
			if (a) {
				aux += 1;
			}
		}
	}
}
