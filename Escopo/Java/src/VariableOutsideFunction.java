
public class VariableOutsideFunction extends Test{
	
	VariableOutsideFunction(){
		setNome("Variable Outside Function");
	}
	
	public void run(boolean a) {
		int aux = 0;
		for (long k = 0; k < num_repeticoes; ++k) {
			//a = true;
			if (a) {
				aux += 1;
			}
		}
	}
}
