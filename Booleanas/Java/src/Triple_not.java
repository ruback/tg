
public class Triple_not extends Operation {
	boolean run(Metodo first, Metodo second){
		return run(first,first,second);
	}
	boolean run(Metodo first, Metodo second, Metodo third){
		return(first.run() || second.run() || third.run()) && ((first.run() || second.run() || third.run()));
	}
}
