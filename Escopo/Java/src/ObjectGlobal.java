
public class ObjectGlobal extends Test{
	
	ObjectGlobal(){
		setNome("Object Global");
	}
	
	public void run() {
		int aux = 0;
		for (long k = 0; k < num_repeticoes; ++k) {
			//Global.obj = new MyClass(true,12345);
			if (Global.obj.a) {
				aux += 1;
			}
		}
	}
}
