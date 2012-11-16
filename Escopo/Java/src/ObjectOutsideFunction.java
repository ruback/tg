
public class ObjectOutsideFunction extends Test {

	ObjectOutsideFunction(){
		setNome("Object Outside Function");
	}
	
	public void run(MyClass obj) {
		int aux = 0;
		for (long k = 0; k < num_repeticoes; ++k) {
			//obj = new MyClass(true,12345);
			if (obj.a) {
				aux += 1;
			}
		}
	}
}
