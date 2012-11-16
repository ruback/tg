
public class ObjectInsideFor extends Test {

	ObjectInsideFor(){
		setNome("Object Inside For");
	}
	
	public void run() {
		int aux = 0;
		for (long k = 0; k < num_repeticoes; ++k) {
			MyClass obj = new MyClass(true,12345);
			if (obj.a) {
				aux += 1;
			}
		}
	}
}
