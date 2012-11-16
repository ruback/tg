
public class Or_test extends Operation {
	boolean run(Metodo first, Metodo second){
		return first.run() || second.run();
	}
}
