
public class True_long_function extends Metodo {
	public boolean run() {
		for (int i = 0; i < repetitions_long; ++i) {
			Global.a += 1;
		}
		if (Global.a > 1) {
			return true;
		}
		return false;
	}
}
