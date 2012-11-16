
public class ObjectOutsideFor extends Test {

	ObjectOutsideFor(){
		setNome("Object Outside For");
	}
	
	public void run() {
		int aux = 0;
		MyClass obj = new MyClass(true,12345);
		for (long k = 0; k < num_repeticoes; ++k) {
			//obj = new MyClass(true,12345);
			if (obj.a) {
				aux += 1;
			}
		}
	}
}
