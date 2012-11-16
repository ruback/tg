
public class Triple extends Operation {
	boolean run(Metodo first, Metodo second){
		return run(first,first,second);
	}
	boolean run(Metodo first, Metodo second, Metodo third){
		return (first.run() && first.run()) || (first.run() && second.run()) || (first.run() && third.run()) || (second.run() && first.run()) || (second.run() && second.run()) || (second.run() && third.run()) || (third.run() && first.run()) || (third.run() && second.run()) || (third.run() && third.run());
	}
}
