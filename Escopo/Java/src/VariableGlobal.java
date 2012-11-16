
public class VariableGlobal extends Test {
	
	VariableGlobal(){
		setNome("Variable Global");
	}
	
	public void run() {
		int aux = 0;
		for (long k = 0; k < num_repeticoes; ++k) {
			//Global.a = true;
			if (Global.a) {
				aux += 1;
			}
		}
	}
}
