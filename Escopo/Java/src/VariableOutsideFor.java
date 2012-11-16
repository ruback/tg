
public class VariableOutsideFor extends Test {
	
	VariableOutsideFor(){
		setNome("Variable Outside For");
	}
	
	public void run() {
		int aux = 0;
		boolean a = true;
		for (long k = 0; k < num_repeticoes; ++k) {
			//a = true;
			if (a) {
				aux += 1;
			}
		}
	}
}
